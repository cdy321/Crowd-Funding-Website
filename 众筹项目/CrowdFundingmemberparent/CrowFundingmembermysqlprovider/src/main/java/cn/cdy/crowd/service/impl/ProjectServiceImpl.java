package cn.cdy.crowd.service.impl;

import cn.cdy.crowd.dao.*;
import cn.cdy.crowd.entity.po.TMemberConfirmInfoPO;
import cn.cdy.crowd.entity.po.TMemberLaunchInfoPO;
import cn.cdy.crowd.entity.po.TProjectPO;
import cn.cdy.crowd.entity.po.TReturnPO;
import cn.cdy.crowd.entity.vo.*;
import cn.cdy.crowd.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectPODao projectPODao;
    @Autowired
    private ProjectItemPicPODao projectItemPicPODao;
    @Autowired
    private MemberLaunchInfoPODao memberLaunchInfoPODao;
    @Autowired
    private MemberConfirmInfoPODao memberConfirmInfoPODao;
    @Autowired
    private ReturnPODao returnPODao;

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public void saveProject(TProjectVO projectVO, Integer memberId) {
        // 一、保存ProjectPO对象
        // 1.初始化一个ProjectPO
        TProjectPO projectPO = new TProjectPO();

        // 2.利用工具方法，给ProjectPO赋值
        BeanUtils.copyProperties(projectVO,projectPO);

        // 3.给projectPO设置memberId
        projectPO.setMemberid(memberId);

        // 4.给projectPO设置项目创建时间
        String createDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        projectPO.setCreatedate(createDate);

        // 5.设置status=0，表示项目即将开始
        projectPO.setStatus(0);

        // 6.向数据库保存ProjectPO
        // 为了在ProjectPO得到自增的主键，
        // 在mapper的xml文件中对应的insert标签增加了useGeneratedKeys="true" keyProperty="id"的配置
        projectPODao.insertSelective(projectPO);

        // 得到projectId
        Integer projectId = projectPO.getId();


        // 二、保存项目、分类关联关系信息
        // 1.得到TypeList
        List<Integer> typeIdList = projectVO.getTypeIdList();
        // 2.执行保存操作
        projectPODao.insertTypeRelationship(projectId, typeIdList);

        // 三、保存项目、标签关联关系信息
        // 1. 得到TagIdList
        List<Integer> tagIdList = projectVO.getTagIdList();
        // 2.执行保存操作
        projectPODao.insertTagRelationship(projectId, tagIdList);

        // 四、保存项目中详情图片路径信息
        // 1.得到detailPicturePathList
        List<String> detailPicturePathList = projectVO.getDetailPicturePathList();

        // 2.执行保存操作
        projectItemPicPODao.insertPathList(projectId, detailPicturePathList);

        // 五、保存项目发起人信息
        // 1.得到发起人信息
        TMemberLaunchInfoVO memberLauchInfoVO = projectVO.getMemberLaunchInfoVO();
        // 2.初始化MemberLaunchInfoPO
        TMemberLaunchInfoPO memberLaunchInfoPO = new TMemberLaunchInfoPO();

        // 3.给MemberLaunchInfoPO赋值
        BeanUtils.copyProperties(memberLauchInfoVO,memberLaunchInfoPO);

        // 4.设置MemberLaunchInfoPO的memberId
        memberLaunchInfoPO.setMemberid(memberId);

        // 5.保存发起人信息
        memberLaunchInfoPODao.insert(memberLaunchInfoPO);

        // 六、保存项目回报信息
        // 1.得到项目汇报信息的List
        List<TReturnVO> returnVOList = projectVO.getReturnVOList();

        // 2.初始化一个ReturnPO的list
        List<TReturnPO> returnPOList = new ArrayList<>();

        // 3.遍历给ReturnPO赋值 并存入List
        for (TReturnVO returnVO : returnVOList){
            TReturnPO returnPO = new TReturnPO();
            BeanUtils.copyProperties(returnVO,returnPO);
            returnPOList.add(returnPO);
        }
        // 4.将returnPOList存入数据库
        returnPODao.insertReturnPOBatch(projectId,returnPOList);

        // 七、保存项目确认信息
        // 1.得到MemberConfirmInfoVO
        TMemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();

        // 2.初始化MemberConfirmInfoPO对象
        TMemberConfirmInfoPO memberConfirmInfoPO = new TMemberConfirmInfoPO();

        // 3.给MemberConfirmInfoPO赋值
        BeanUtils.copyProperties(memberConfirmInfoVO,memberConfirmInfoPO);

        // 4.给MemberConfirmInfoPO设置memberId
        memberConfirmInfoPO.setMemberid(memberId);

        // 将MemberConfirmInfoPO存入数据库
        memberConfirmInfoPODao.insert(memberConfirmInfoPO);
    }

    @Override
    public List<PortalTypeVO> getPortalTypeVO() {
        return projectPODao.selectPortalVOList();
    }

    @Override
    public DetailProjectVO getDetailProjectVO(Integer projectId) {
        DetailProjectVO detailProjectVO = projectPODao.selectDetailProjectVO(projectId);
        Integer status = detailProjectVO.getStatus();
        switch (status) {
            case 0:
                detailProjectVO.setStatusText("审核中");
                break;
            case 1:
                detailProjectVO.setStatusText("众筹中");
                break;
            case 2:
                detailProjectVO.setStatusText("众筹成功");
                break;
            case 3:
                detailProjectVO.setStatusText("已关闭");
                break;
            default:
                break;
        }
        // 3.根据 deployeDate 计算 lastDay
        // 2020-10-15
        String deployDate = detailProjectVO.getDeployDate();
        // 获取当前日期
        Date currentDay = new Date();
        // 把众筹日期解析成 Date 类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date deployDay = format.parse(deployDate);
            // 获取当前当前日期的时间戳
            long currentTimeStamp = currentDay.getTime();
              // 获取众筹日期的时间戳
            long deployTimeStamp = deployDay.getTime();
               //          两个时间戳相减计算当前已经过去的时间
            long pastDays = (currentTimeStamp - deployTimeStamp) / 1000 / 60 / 60 / 24;
            Integer totalDays = detailProjectVO.getDay();
            // 使用总的众筹天数减去已经过去的天数得到剩余天数
            Integer lastDay = (int) (totalDays - pastDays);
            detailProjectVO.setLastDay(lastDay);
        }catch (Exception e){
            e.printStackTrace();
        }
        return detailProjectVO;
    }
}
