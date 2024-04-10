package com.sangam.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.sangam.service.GenericService;

@Component
@Profile("default")
public class DefaultServiceImpl implements GenericService{


	@Value("${my.profile.code}")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "ProdServiceImpl [code=" + code + "]";
	}

	@Override
	public void executeTask() {
		System.out.println("From Default Service Implementation");
		System.out.println("Code is :: "+code);

	}

}
