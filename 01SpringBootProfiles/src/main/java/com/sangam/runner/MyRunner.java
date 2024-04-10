package com.sangam.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.sangam.service.GenericService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MyRunner implements CommandLineRunner {

	private final GenericService service;

	@Override
	public void run(String... args) throws Exception {
		service.executeTask();
	}

}
