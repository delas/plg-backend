package plg.web.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/system/")
@CrossOrigin
public class SystemController {

	@GetMapping("/ping")
	public @ResponseBody String ping() {
		return "pong";
	}
}
