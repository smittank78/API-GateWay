package api.ServerEurekaApi1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.inject.spi.Message;

@Configuration
public class configuration_server {
	@Bean
	public Message name() {
		System.out.println("msg object created");
		return new Message("done");
	}
	
}
