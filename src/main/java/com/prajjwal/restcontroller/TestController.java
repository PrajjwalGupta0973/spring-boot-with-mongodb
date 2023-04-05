package com.prajjwal.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

	@Autowired
	private Environment env;

	@Autowired
	private ConfigurableEnvironment configurableEnviroment;

	@Value("${myprop}")
	private String myProp;

	@PostMapping("/jwt")
	public void receiveToken(@RequestBody Object token) {
		System.out.println(token);

	}

	@GetMapping("/getenv")
	public Environment getEnvironment() {
		System.out.println(myProp);
		return env;
	}
}
