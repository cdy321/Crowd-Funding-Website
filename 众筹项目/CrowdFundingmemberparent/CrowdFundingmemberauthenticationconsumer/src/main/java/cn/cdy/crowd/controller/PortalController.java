package cn.cdy.crowd.controller;

import cn.cdy.crowd.api.MySqlRemoteService;
import cn.cdy.crowd.entity.vo.PortalTypeVO;
import cn.cdy.crowd.util.CrowdConstant;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PortalController {
    @Autowired
    private MySqlRemoteService mySqlRemoteService;
    @RequestMapping("/")
    public String showPortalPage(Model model){
        ResultEntity<List<PortalTypeVO>> portalTypeProjectRemoteResultEntity = mySqlRemoteService.getPortalTypeProjectRemote();
        String result = portalTypeProjectRemoteResultEntity.getResult();
        if(ResultEntity.SUCCESS.equals(result)){
            List<PortalTypeVO> list = portalTypeProjectRemoteResultEntity.getData();
            model.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA,list);
        }
        return "portal";
    }
}
