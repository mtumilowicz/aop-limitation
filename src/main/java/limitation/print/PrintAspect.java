package limitation.print;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mtumilowicz on 2018-06-29.
 */
@Aspect
@Configuration
public class PrintAspect {
    @Before("@annotation(Print)")
    public void before() {
        System.out.println("Using AOP - before method");
    }

    @After("@annotation(Print)")
    public void after() {
        System.out.println("Using AOP - after method");
        System.out.println();
    }
}
