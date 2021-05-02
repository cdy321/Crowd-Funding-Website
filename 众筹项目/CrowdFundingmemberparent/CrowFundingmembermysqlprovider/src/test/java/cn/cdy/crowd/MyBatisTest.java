package cn.cdy.crowd;

import cn.cdy.crowd.dao.*;
import cn.cdy.crowd.entity.po.*;
import cn.cdy.crowd.entity.vo.DetailProjectVO;
import cn.cdy.crowd.entity.vo.PortalProjectVO;
import cn.cdy.crowd.entity.vo.PortalTypeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TMemberPODao tMemberPODao;
    @Autowired
    private ProjectPODao projectPODao;
    @Autowired
    private MemberLaunchInfoPODao memberLaunchInfoPODao;
    @Autowired
    private MemberConfirmInfoPODao memberConfirmInfoPODao;
    @Autowired
    private ReturnPODao returnPODao;
    @Autowired
    private ProjectItemPicPODao projectItemPicPODao;
    @Autowired
    private AddressPODao addressPODao;
    @Autowired
    private OrderPODao orderPODao;
    @Autowired
    private OrderProjectPODao orderProjectPODao;
    @Test
    public void test01(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String source = "123123";
        String encode = passwordEncoder.encode(source);
        TMemberPO memberPO = new TMemberPO(null, "jack", encode, " 杰 克 ", "jack@qq.com", 1, 1, "杰克", "123123", 2);
        tMemberPODao.insertMember(memberPO);
    }
    @Test
    public void test02(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);//$2a$10$77T/.rLfZfOd3fiqo9qX1OgUwF0YKU/JxBjCwTis0J01v3pjQ6s.K
    }
    @Test
    public void test03(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean res = bCryptPasswordEncoder.matches("123456", "$2a$10$77T/.rLfZfOd3fiqo9qX1OgUwF0YKU/JxBjCwTis0J01v3pjQ6s.K");
        System.out.println(res);
    }
    @Test
    public void test04(){
        TProjectPO projectPO = new TProjectPO();
        projectPO.setStatus(0);
        projectPO.setCreatedate("1月1日");
        projectPO.setMemberid(1);
        projectPO.setDay(10);
        projectPO.setMoney(100);
        projectPO.setCompletion(12);
        projectPO.setDeploydate("1月1日");
        projectPO.setFollower(11);
        projectPO.setHeaderPicturePath("http://www.com");
        projectPODao.insertSelective(projectPO);
    }
    @Test
    public void test05(){
        TMemberLaunchInfoPO memberLaunchInfoPO = new TMemberLaunchInfoPO();
        memberLaunchInfoPO.setMemberid(1);
        memberLaunchInfoPO.setDescriptionDetail("111");
        memberLaunchInfoPO.setDescriptionSimple("111");
        memberLaunchInfoPO.setPhoneNum("21222");
        memberLaunchInfoPO.setServiceNum("11111");
        memberLaunchInfoPODao.insert(memberLaunchInfoPO);
    }
    @Test
    public void test06(){
        TMemberConfirmInfoPO memberConfirmInfoPO = new TMemberConfirmInfoPO();
        memberConfirmInfoPO.setMemberid(1);
        memberConfirmInfoPO.setCardnum("12345");
        memberConfirmInfoPO.setPaynum("1111");
        memberConfirmInfoPODao.insert(memberConfirmInfoPO);
    }
    @Test
    public void test07(){
        int projectId = 1;
        List<TReturnPO> list = new ArrayList<>();
        TReturnPO returnPO = new TReturnPO();
        returnPO.setCount(10);
        returnPO.setDescribPicPath("1111");
        list.add(returnPO);
        returnPODao.insertReturnPOBatch(projectId,list);
    }
    @Test
    public void test08(){
        int projectId = 1;
        List<String> list = new ArrayList<>();
        String detailPath = "123456";
        list.add(detailPath);
        projectItemPicPODao.insertPathList(projectId,list);
    }
    @Test
    public void test09(){
        List<PortalTypeVO> portalTypeVOSList = projectPODao.selectPortalVOList();
        for (PortalTypeVO portalTypeVO : portalTypeVOSList) {
            String name = portalTypeVO.getName();
            String remake = portalTypeVO.getRemark();
            List<PortalProjectVO> portalProjectVOList = portalTypeVO.getPortalProjectVOList();
            for (PortalProjectVO portalProjectVO : portalProjectVOList) {
                System.out.println(portalProjectVO);
            }
        }
    }
    @Test
    public void test10(){
        Integer projectId = 17;
        DetailProjectVO detailProjectVO = projectPODao.selectDetailProjectVO(projectId);
        System.out.println(detailProjectVO);
    }
    @Test
    public void test11(){
        AddressPO addressPO = new AddressPO();
        addressPO.setAddress("hb");
        addressPO.setMemberId(5);
        addressPO.setReceiveName("tom");
        addressPO.setPhoneNum("12345");
        addressPODao.insert(addressPO);
    }
    @Test
    public void test12(){
        List<AddressPO> addressPOList = addressPODao.selectAddressByMemberId(5);
        System.out.println(addressPOList);
    }
    @Test
    public void test13(){

    }
    @Test
    public void test14(){
        OrderProjectPO orderProjectPO = new OrderProjectPO();
        orderProjectPO.setOrderId(1);
        orderProjectPO.setFreight(10);
        orderProjectPO.setLaunchName("qw");
        orderProjectPO.setProjectName("11");
        orderProjectPO.setReturnContent("12");
        orderProjectPO.setReturnCount(12);
        orderProjectPO.setSupportPrice(100);
        orderProjectPODao.insert(orderProjectPO);
    }
}
