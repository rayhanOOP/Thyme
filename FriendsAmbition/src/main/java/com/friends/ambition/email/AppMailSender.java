package com.friends.ambition.email;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author pavan.solapure
 *
 */
@Component
public class AppMailSender implements ApplicationRunner {

	@Autowired
	EmailService emailService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		sendHtmltMail();
		sendTextMail();

	}

	private void sendTextMail() {

		String from = "pavan@localhost";
		String to = "solapure@localhost";
		String subject = "Java Mail with Spring Boot - Plain Text";

		EmailTemplate template = new EmailTemplate("hello-world-plain.txt");

		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("user", "Pavan");
		replacements.put("today", String.valueOf(new Date()));

		String message = template.getTemplate(replacements);

		Email email = new Email(from, to, subject, message);

		emailService.send(email);
	}

	private void sendHtmltMail() {

		
		String from = "pavan@localhost";
		String to = "solapure@localhost";
		String subject = "Java Mail with Spring Boot";

		EmailTemplate template = new EmailTemplate("email.html");

		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("user", "Pavan");
		replacements.put("today", String.valueOf(new Date()));

		String message = template.getTemplate(replacements);

		Email email = new Email(from, to, subject, message);
		email.setHtml(true);
		emailService.send(email);
	}

}
