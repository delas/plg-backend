package plg.web.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import plg.model.Process;
import plg.web.utils.ProcessUtils;

public class Process4Web {

	private String id;
	private String name;
	private String serialization;
	private String serializationType;
	
	public Process4Web() { }
	
	public Process4Web(String id, String serialization) {
		this.id = id;
		this.serialization = serialization;
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
		return ProcessUtils.plg2process(serialization);
	}

	public String getSerializationType() {
		return serializationType;
	}
}
