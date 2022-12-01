package fallback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class apiCall
{
	@Autowired
	@LoadBalanced
	private RestTemplate client;
	@HystrixCommand(fallbackMethod = "getFallbackSubject")
	public String getSubjectOnly() {
		return client.getForObject("http://subject/test",String.class);	
	}
	@HystrixCommand(fallbackMethod = "getFallbackCompany")
	public String getCompanyOnly() {
		return client.getForObject("http://company/service/test",String.class);	
	}
	public String getFallbackSubject() {
		return "fallback method of getSubject";
	}
	public String getFallbackCompany() {
		return "fallback method of getCompany";
	}
}

