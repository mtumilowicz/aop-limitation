package limitation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mtumilowicz on 2018-06-29.
 */
@Aspect
@Configuration
public class PrintAspect {
    @Around("@annotation(Print)")
    public void xxx(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Using AOP - before method");
        joinPoint.proceed();
        System.out.println("Using AOP - after method");
        System.out.println();
    }
}
