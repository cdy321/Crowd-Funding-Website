package cn.cdy.crowd;

import cn.cdy.crowd.entity.TAdmin;
import cn.cdy.crowd.service.TAdminService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestRole {
    @Test
    public void test01(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TAdminService service = (TAdminService) ac.getBean("TAdminService");
        List<TAdmin> tAdmins = service.queryAllByLimit(0, 3);
        System.out.println(tAdmins);
    }
    @Test
    public void test02(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
