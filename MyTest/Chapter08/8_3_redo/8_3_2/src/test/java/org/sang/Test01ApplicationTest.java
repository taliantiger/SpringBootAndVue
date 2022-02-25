//package org.sang;
//
//import junit.framework.TestCase;
//public class Test01ApplicationTest extends TestCase {
//
//}



package org.sang;

import ch.qos.logback.core.boolex.Matcher;
import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sang.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)

//@SpringBootTest

@SpringBootTest(classes = Test01Application.class)
//@ContextConfiguration(classes = Test01Application.class)

public class Test01ApplicationTest extends TestCase {
    @Autowired
    HelloService helloService;

    @Test
    public void contextLoads(){
        String hello = helloService.sayHello("Michael");
        Assert.assertThat(hello, Matchers.is("Hello Michael !"));

    }
}