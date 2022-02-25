//package org.sang;
//
//import junit.framework.TestCase;
//public class Test01ApplicationTest extends TestCase {
//
//}



package org.sang;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)

//@SpringBootTest

@SpringBootTest(classes = Test01Application.class)
//@ContextConfiguration(classes = Test01Application.class)

public class Test01ApplicationTest extends TestCase {
    @Test
    public void contextLoads(){

    }
}