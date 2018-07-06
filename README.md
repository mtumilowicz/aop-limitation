# aop-limitation
AOP is used in the Spring Framework to…​

…​ provide declarative enterprise services - the most important is declarative transaction management.

…​ allow users to implement custom aspects, complementing their use of OOP with AOP.

AOP concepts:
Aspect: a modularization of a concern that cuts across multiple classes. 
Transaction management is a good example of a crosscutting concern in enterprise Java applications. 
In Spring AOP, aspects are implemented using regular classes annotated with the @Aspect annotation (the @AspectJ style).

Join point: a point during the execution of a program - in Spring AOP, a join point always represents a method execution.

Advice: action taken by an aspect at a particular join point. Different types of advice include "around", "before" and "after" advice.

Before advice @Before: Advice that executes before a join point, but which does not have the ability to prevent execution 
flow proceeding to the join point (unless it throws an exception).

After returning advice @AfterReturning: Advice to be executed after a join point completes normally: for example, 
if a method returns without throwing an exception.

After throwing advice @AfterThrowing: Advice to be executed if a method exits by throwing an exception.

After (finally) advice @After: Advice to be executed regardless of the means by which a join point exits (normal or exceptional return).

Around advice @Around: Advice that surrounds a join point such as a method invocation. 
This is the most powerful kind of advice. Around advice can perform custom behavior before and after the method invocation. 
It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by 
returning its own return value or throwing an exception.

Using the most specific advice type provides a simpler programming model with less potential for errors. 
For example, you do not need to invoke the proceed() method on the JoinPoint used for around advice, 
and hence cannot fail to invoke it.

Spring models an advice as an interceptor, maintaining a chain of interceptors around the join point.

Spring AOP currently supports only method execution join points

PROXY
* standard JDK dynamic proxies (defaults) - interface  are proxied
* CGLIB proxies - classes rather than interfaces

https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-understanding-aop-proxies

Declaring an aspect
any bean defined in your application context with the @Aspect annotation will be 
automatically detected by Spring.

Declaring a pointcut
you can think of a pointcut as matching the execution of methods on Spring beans. 
A pointcut declaration has two parts: a signature comprising a name and any parameters, and a pointcut expression 
that determines exactly which method executions we are interested in.



https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop
