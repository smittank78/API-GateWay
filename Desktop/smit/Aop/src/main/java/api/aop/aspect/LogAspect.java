package api.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect 
{
	/*
	 *
	 * create custome annotation
	 * annotate request handler methods with that annotation
	 * providepath of that annotation in Point-cut
	 * @Pointcut -> provide fully-qulified path for which methods/annotation
	 * 				you want to provide advice
	 * Advice -> 
	 * 			@Before: before method execution		
	 * 			@After : after method execution
	 * 			@Around: instead of method this will run. 
	 * 					 you can resume method calling by ProceedingJoinPoint.proceed()
	 * 			@AfterReturning: runs only after normal execution of method
	 * 			@AfterThrowing: runs if method throws any exception
	 * 
	 */
	//@Pointcut("execution(* api.aop.controller.Handler.*())") // methods path
	//@Before(value = "execution(* api.aop.controller.EmployeeService.*(..)) and args(name,Id)")

	@Pointcut("@annotation(api.aop.anotation.Log)") //annotation path
	public void logger() {
	}
	@Before(value = "logger()")
	public void loggerBefore() {
		System.out.println("@Before");
	}
	@After(value = "logger()")
	public void loggerAfter() {
		System.out.println("@After");
	}
	@Around(value = "logger()")
	public void loggerAround(ProceedingJoinPoint joinPoint) throws Throwable 
	{
		System.out.println("@Around : " + joinPoint.getSignature());
		joinPoint.proceed();
	}
	@AfterThrowing(value = "logger()")
	public void loggerException() throws Throwable 
	{
		System.out.println("@AfterThrowing : Exception Handling ");
		try {
		throw new Exception();
		}
		catch (Exception e) {
			System.out.println("Exception handled in method");
		}
	}
}