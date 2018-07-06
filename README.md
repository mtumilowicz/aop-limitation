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
* Allow users to implement custom aspects, complementing their use 
of `OOP` with `AOP`.

# concepts
* **Aspect**: a modularization of a concern that cuts across multiple classes. 
Transaction management is a good example of a crosscutting concern in 
enterprise Java applications.  
In `Spring AOP`, aspects are implemented using regular classes annotated 
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
    
    * **@Around**: Advice that surrounds a join point. 
    This is the most powerful kind of advice. Around advice can perform custom 
    behavior before and after the method invocation. 
    It is also responsible for choosing whether to proceed to the join point or 
    to shortcut the advised method execution by returning its own return value 
    or throwing an exception.
    
    * **Note that** using the most specific advice type provides a simpler programming 
    model with less potential for errors.  
    For example, you do not need to invoke the `proceed()` method on the 
    `JoinPoint` used for around advice, and hence cannot fail to invoke it.

* **Proxy** - an object created in order to implement the aspect contracts. 
In `Spring AOP` we have two types of proxies:
    * standard `JDK` dynamic proxies (defaults) for interfaces.
    * `CGLIB` proxies for classes rather than interfaces.

* **Pointcut** - a predicate that matches join points. Advice is associated 
with a pointcut expression and runs at any join point matched by the pointcut 
(for example, the execution of a method with a certain name). 
You can think of a pointcut as matching the execution of methods on Spring beans.  
Whatever you specify inside the advice is a pointcut expression and can be 
extracted out into a separate method using `@Pointcut` annotation for better 
understanding and modularity.  
    We have many types of pointcuts:
    * **execution** - for matching method execution join points, 
    this is the primary pointcut designator you will use when working with 
    `Spring AOP`.
    
    * **within** - join points within certain types 
    
    * **this** - join points where the bean reference 
    (`Spring AOP` proxy) is an instance of the given type
    
    * **target** - join points where the target object 
    (application object being proxied) is an instance of the given type
    
    * **args** - join points where the arguments are 
    instances of the given types
    
    * **@target** - join points where the class of the 
    executing object has an annotation of the given type
    
    * **@args** - join points where the runtime type of the 
    actual arguments passed have annotations of the given type(s)
    
    * **@within** - join points within types that have the 
    given annotation (the execution of methods declared in types with the 
    given annotation)
    
    * **@annotation** - join points where the subject of the 
    join point (method being executed in `Spring AOP`) has the given annotation

* **Weaving**: linking aspects with other application types or objects to create 
an advised object. `Spring AOP` performs weaving at runtime.

# manual
* **Declaring an aspect**  
Any bean defined in your application context with the `@Aspect` annotation will be 
automatically detected by Spring.
    ```
    @Aspect
    public class PrintAspect
    ```

* **Declaring a pointcut & advice**  
A pointcut declaration has two parts: 
    * A signature comprising a name and any parameters.
    * Pointcut expression that determines exactly which method 
    executions we are interested in.
    ```
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {}

    @Pointcut("within(controller.*)")
    public void controller() {}

    @Around("requestMapping() && myController()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
      ...............
   }      
    ```
# project description
Due to the proxy-based nature of `Spring’s AOP` framework, calls within the 
target object are by definition not intercepted. For `JDK` proxies, only 
public interface method calls on the proxy can be intercepted. With `CGLIB`, 
public and protected method calls on the proxy will be intercepted, and even 
package-visible methods if necessary. However, common interactions through 
proxies should always be designed through public signatures.

# project structure
* **Print** - annotation
* **PrintAspect** - aspect that wraps `@Before` and `@After` 
annotation `@Print`
    ```
    @Before("@annotation(Print)")
    public void before() {
        System.out.println("Using AOP - before method");
    }    
    ```
    ```
    @After("@annotation(Print)")
    public void after() {
        System.out.println("Using AOP - after method");
        System.out.println();
    }    
    ```
* **Example** - `@Component` that tests `PrintAspect`, we have three methods:
    ```
    @Print
    void first() {
        System.out.println("first");
    }    
    ```
    ```
    @Print
    void second() {
        System.out.println("second");
    }    
    ```
    ```
    @Print
    void all() {
        System.out.println("all");
        first();
        second();
    }    
    ```
    and we ask a question - what will be printed to console 
    when we invoke methods:
* **ExampleTest**
    We inject `Example` (note that injected is proxy)
    ```
    @Autowired
    Example example;
    ```
    * test of the `all()` method
        ```
        @Test
        public void printOverAll() {
            example.all();
        }    
        ```
        produces:
        ```
        Using AOP - before method
        all
        first
        second
        Using AOP - after method
        ```
        which could be counter-intuitive at a glance, but note that
        in `all()` method we refer to `first()` and `second()` that
        are invoked not on the proxy but on the target.