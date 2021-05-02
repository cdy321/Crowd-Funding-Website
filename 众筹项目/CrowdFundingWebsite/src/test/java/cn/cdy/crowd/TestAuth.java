package cn.cdy.crowd;

import cn.cdy.crowd.entity.TAuth;
import cn.cdy.crowd.service.TAuthService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestAuth {
    @Test
    public void test01(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TAuthService service = (TAuthService) ac.getBean("TAuthService");
        List<TAuth> list = service.getAll();
        System.out.println(list);
    }
}
