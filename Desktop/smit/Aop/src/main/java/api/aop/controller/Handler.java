package api.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import api.aop.anotation.Log;

@RestController
public class Handler 
{	
	@GetMapping("/1")
	@Log //Joint-Point
	public void call1() throws Exception 
	{	
		method();
	}

	public void method() throws Exception {
		System.out.println("method");
		throw new Exception();	
	}
	@GetMapping("/2")
	@Log
	public void call2() throws Exception 
	{	
		System.out.println("method");
	}
}