package api.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import fallback.apiCall;

@RestController
@RequestMapping("/student")
public class WebRequestHandler 
{
//	private WebClient webClient = WebClient.create();
	
	@Autowired
	@LoadBalanced
	private RestTemplate client;
//	WebClient client = WebClient.create();
	@Autowired
	apiCall call;
	
	@GetMapping("/")
	public String a() {
		System.out.println("/student called");
		return "student service up";
	}
	/*
	 * for one rest-api call
	 */
	@HystrixCommand(fallbackMethod = "getFallbackSubject",
					commandProperties = {
							@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "200"),
							@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "6"),
							@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
							@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "50000"),
						}
				)
	@GetMapping("/getsubject")
	public String getSubject() {
		System.out.println("student to subject called");
		//Mono<String> subject = client.get().uri("/subject/test").retrieve().bodyToMono(String.class);
		String response = client.getForObject("http://subject/test",String.class);	
		System.out.println("response : " + response);
		return response;
	}
	public String getFallbackSubject() {
		return "fallback method of getSubject";
	}

	@GetMapping("/getcompany")
	public String getCompany() {
		System.out.println("student to company called");
		//Mono<String> subject = client.get().uri("/subject/test").retrieve().bodyToMono(String.class);
		String response = client.getForObject("http://company/service/test",String.class);	
		System.out.println("response : " + response);
		return response;
	}
	/*
	 * for rest-api-call to >1 microservices
	 * because you can create fall-back for this method because 
	 * if one service down and another is up then it calls fall-back so its not good.
	 * creating saperate method for rest-api calls 
	 * and writing saperate fall-back for all rest-api-call methods
	 * so if 1 service down then fall-back is for only that service not all
	 */
	@GetMapping("/getall")
	public String getAll() {
		System.out.println("student to both called");
		//Mono<String> subject = client.get().uri("/subject/test").retrieve().bodyToMono(String.class);
		String  responseFromSubject = call.getSubjectOnly();
		String responseFromCompany = call.getCompanyOnly();	
		System.out.println("response : received");
		return responseFromSubject +" -- " + responseFromCompany;
	}

}