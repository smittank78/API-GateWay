package api.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class Controller
{
	@GetMapping("/test")
	public String test() 
	{
		System.out.println("com called");
		return "com called";
	}
}