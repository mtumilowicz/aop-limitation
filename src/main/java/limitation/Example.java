package limitation;

import org.springframework.stereotype.Component;

/**
 * Created by mtumilowicz on 2018-06-29.
 */
@Component
public class Example {

    @Print
    void first() {
        System.out.println("first");
    }

    @Print
    void second() {
        System.out.println("second");
    }
    
    @Print
    void all() {
        System.out.println("all");
        first();
        second();
    }
}
