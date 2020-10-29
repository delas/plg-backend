package plg.web.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.annotation.JsonIgnore;

import plg.io.exporter.PLGExporter;
import plg.io.importer.PLGImporter;
import plg.io.importer.PLGImporterNoPython;
import plg.model.Process;

public class Process4Web {

	private String id;
	private String name;
	private String serialization;
	private String serializationType;
	
	public Process4Web() { }
	
	public Process4Web(String id, Process process) throws IOException {
		this.id = id;
		this.serialization = serialize(process);
		this.name = id.substring(id.length() - 3);
		this.serializationType = "plg";
	}

	public String getId() {
		return id;
	}
	
	public String getSerialization() {
		return serialization;
	}
	
	public String getName() {
		return name;
	}
	
	@JsonIgnore
	public Process getPlgProcess() throws IOException {
		return deserialize(serialization);
	}

	public String getSerializationType() {
		return serializationType;
	}
	
	private String serialize(Process process) throws IOException {
		PLGExporter e = new PLGExporter();
		String model = "";
		File f = File.createTempFile("model", "plg");
		e.exportModel(process, f.getAbsolutePath());
		model = new String(Files.readAllBytes(f.toPath()));
		f.delete();
		return model;
	}
	
	private Process deserialize(String serialization) throws IOException {
		File fPlg = File.createTempFile("model", "plg");
		Files.write(fPlg.toPath(), serialization.getBytes());
		PLGImporter i = new PLGImporterNoPython();
		Process p = i.importModel(fPlg.getAbsolutePath());
		fPlg.delete();
		return p;
	}
}
