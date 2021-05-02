package cn.cdy.crowd;

import cn.cdy.crowd.entity.TRole;
import cn.cdy.crowd.service.TRoleService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestInner {
    @Test
    public void test01(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TRoleService service = (TRoleService) ac.getBean("TRoleService");
        List<TRole> list = service.getNoAssignRole(1);
        System.out.println(list);
    }
}
