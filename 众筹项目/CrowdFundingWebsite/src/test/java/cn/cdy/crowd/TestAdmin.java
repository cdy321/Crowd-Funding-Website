package cn.cdy.crowd;

import cn.cdy.crowd.entity.TAdmin;
import cn.cdy.crowd.entity.TRole;
import cn.cdy.crowd.service.TAdminService;
import cn.cdy.crowd.service.TRoleService;
import cn.cdy.crowd.util.CrowdUtil;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class TestAdmin {
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
        String source = "123456";
        String encoded = CrowdUtil.md5(source);
        System.out.println(encoded);
    }
    @Test
    public void test03(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TAdminService service = (TAdminService) ac.getBean("TAdminService");
        TAdmin admin = service.getAdmin("张三", "123456");
        System.out.println(admin);
    }
    @Test
    public void test04(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TAdminService service = (TAdminService) ac.getBean("TAdminService");
        PageInfo<TAdmin> info = service.getPageInfo("张三", 1, 5);
        System.out.println(info);
    }
    @Test
    public void test05(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TAdminService service = (TAdminService) ac.getBean("TAdminService");
        for (int i = 0; i < 100; i++) {
            service.insert(new TAdmin(i+6,"loginAcct"+i,"userPswd"+i,"email"+i,"date"+i,"user"+i));
        }
    }
    @Test
    public void test06(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TRoleService service = (TRoleService) ac.getBean("TRoleService");
        PageInfo<TRole> pageInfo = service.getPageInfo("1", 1, 5);
        System.out.println(pageInfo.getTotal());
    }
    @Test
    public void test07(){
        String password = "12345678";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        System.out.println(encode);
    }
}
