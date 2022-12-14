package api.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller 
{

	@GetMapping("/a")
	public String a() {
		System.out.println("/ called");
		return "subject service up";
	}
	@GetMapping("/atest")
	public String name() {
		System.out.println("subject called");
		return "Subject Test";
	}
}