package limitation.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mtumilowicz on 2018-06-29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleTest {

    @Autowired
    Example example;
    
    @Test
    public void printOverFirst() {
        example.first();
    }
    
    @Test
    public void printOverSecond() {
        example.second();
    }
    
    @Test
    public void printOverAll() {
        example.all();
    }
}