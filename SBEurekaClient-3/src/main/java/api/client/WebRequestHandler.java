package api.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class WebRequestHandler
{
	@GetMapping("/")
	public String test() 
	{
		System.out.println("company called");
		return "company called";
	}
}