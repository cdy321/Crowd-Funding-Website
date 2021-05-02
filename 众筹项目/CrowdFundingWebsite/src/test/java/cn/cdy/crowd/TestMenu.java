package cn.cdy.crowd;

import cn.cdy.crowd.entity.TMenu;
import cn.cdy.crowd.service.TMenuService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMenu {
    @Test
    public void test01(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TMenuService service = (TMenuService) ac.getBean("TMenuService");
        List<TMenu> list = service.getAllMenu();
        System.out.println(list);
    }
}
