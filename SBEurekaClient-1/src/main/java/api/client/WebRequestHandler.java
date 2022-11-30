package api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/student")
public class WebRequestHandler 
{
//	private WebClient webClient = WebClient.create();
	
	@Autowired
	@LoadBalanced
	private RestTemplate client;
//	WebClient client = WebClient.create();
	
	@GetMapping("/")
	public String a() {
		System.out.println("/student called");
		return "student service up";
	}
	
	@GetMapping("/getsubject")
	public String getSubject() {
		System.out.println("student to subject called");
		//Mono<String> subject = client.get().uri("/subject/test").retrieve().bodyToMono(String.class);
		String response = client.getForObject("http://subject/test",String.class);	
		System.out.println("response : " + response);
		return response;
	}

	@GetMapping("/getcompany")
	public String getCompany() {
		System.out.println("student to company called");
		//Mono<String> subject = client.get().uri("/subject/test").retrieve().bodyToMono(String.class);
		String response = client.getForObject("http://company/service/test",String.class);	
		System.out.println("response : " + response);
		return response;
	}
}