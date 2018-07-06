# aop-limitation
The main goal of this project is to show basic limitation of aspect oriented 
programming.  

_Reference_: [Spring AOP documentation](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop)  
_Reference_: [Implementing AOP using Spring Boot](http://www.baeldung.com/spring-aop-annotation)
_Reference_: [AOP Proxies](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-understanding-aop-proxies)
#preface
`AOP` is used in the `Spring Framework` to:
* Provide declarative enterprise services - the most important is declarative 
transaction management.
* Allow users to implement custom aspects, complementing their use of OOP with AOP.

# concepts
* **Aspect**: a modularization of a concern that cuts across multiple classes. 
Transaction management is a good example of a crosscutting concern in 
enterprise Java applications.  
In Spring AOP, aspects are implemented using regular classes annotated 
with the `@Aspect` annotation.

* **Join point**: always represents a method execution.

* **Advice**: action taken by an aspect at a particular join point. Spring 
    models an advice as an interceptor, maintaining a chain of interceptors 
    around the join point.
    Types:
    * **@Before**: Advice that executes before a join point, 
    but which does not have the ability to prevent execution flow proceeding 
    to the join point (unless it throws an exception).
    
    * **@AfterReturning**: Advice to be executed after a join point completes 
    normally: for example, if a method returns without throwing an exception.
    
    * **@AfterThrowing**: Advice to be executed if a method exits by throwing 
    an exception.
    
    * **@After**: Advice to be executed regardless of the means by which a 
    join point exits (normal or exceptional return).
    
    * **@Around**: Advice that surrounds a join point such as a method invocation. 
    This is the most powerful kind of advice. Around advice can perform custom 
    behavior before and after the method invocation. 
    It is also responsible for choosing whether to proceed to the join point or 
    to shortcut the advised method execution by returning its own return value 
    or throwing an exception.
    
    * **Note that** using the most specific advice type provides a simpler programming 
    model with less potential for errors.  
    For example, you do not need to invoke the `proceed()` method on the 
    `JoinPoint` used for around advice, and hence cannot fail to invoke it.

* **Proxy** - an object created by the AOP framework in order to implement the 
aspect contracts. In `Spring AOP` we have two types of proxies:
    * standard `JDK` dynamic proxies (defaults) for interfaces.
    * `CGLIB` proxies for classes rather than interfaces.

* **Pointcut** - a predicate that matches join points. Advice is associated 
with a pointcut expression and runs at any join point matched by the pointcut 
(for example, the execution of a method with a certain name). 
You can think of a pointcut as matching the execution of methods on Spring beans.

* **Weaving**: linking aspects with other application types or objects to create 
an advised object. `Spring AOP` performs weaving at runtime.

# manual
* Declaring an aspect:
any bean defined in your application context with the @Aspect annotation will be 
automatically detected by Spring.

* Declaring a pointcut: 
A pointcut declaration has two parts: 
    * a signature comprising a name and any parameters
    * pointcut expression that determines exactly which method 
    executions we are interested in.
