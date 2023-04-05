package com.prajjwal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendGridConfig {

	@Value("${testemail.api.key}")
	private String appkey;
	
	@Bean
	SendGrid getSendGrid() {
		return new SendGrid(appkey);
	}
	
}
