/**
 * 
 */
package com.support.finance.configuration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.support.finance.controller.HomeController;


/**
 * @author vishnu
 *
 */
@Configuration
@EnableScheduling
public class SchedulerConfiguration {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	//private @Autowired JavaMailSender javaMailSender;
	/*@Scheduled(fixedDelay = 3000)
	public void scheduleemailforstudentsresults() {
		logger.debug("starting to sending an email");
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
		try {
   		   mailMsg.setFrom("mail.universityerp@gmail.com");
		   mailMsg.setTo("raju.bhai20@gmail.com");
		   mailMsg.setSubject("Test mail sent by sanjay");
		   mailMsg.setText("Hello Sir! I am just testing load in localhost");
		   javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error( "Error occurred by sk sanjay code :: ", e );
		}
		catch (Exception e) {
			logger.error( "Error occurred by sk sanjay code :: ", e );
		}
   	   		logger.debug("successfully sent the email");
	}*/
}
