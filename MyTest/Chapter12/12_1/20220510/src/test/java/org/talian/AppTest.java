package org.talian;


import com.google.common.annotations.VisibleForTesting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {
    @Autowired
    JmsComponent jmsComponent;

//    @VisibleForTesting
    @Test
    public void contextLoads(){
        Message msg = new Message();
        msg.setContent("hello jms!");
        msg.setDate(new Date());
        jmsComponent.send(msg);
    }
}