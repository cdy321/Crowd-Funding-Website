package cn.cdy.crowd.controller;

import cn.cdy.crowd.entity.TAuth;
import cn.cdy.crowd.entity.TRole;
import cn.cdy.crowd.service.TAdminService;
import cn.cdy.crowd.service.TAuthService;
import cn.cdy.crowd.service.TRoleService;
import cn.cdy.crowd.util.ResultEntity;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
//分配角色
@Controller
public class AssignRoleToAdmin {
    @Resource
    private TAdminService tAdminService;

    @Resource
    private TRoleService tRoleService;

    @Resource
    private TAuthService tAuthService;

    @RequestMapping("/assign/to/assign/role/page.html")
    public ModelAndView assignRolePage(@RequestParam("adminId") Integer adminId,
                                       ModelMap map){
        ModelAndView mv = new ModelAndView();
        System.out.println(adminId);
        List<TRole> AssignList = tRoleService.getAssignedRole(adminId);
        List<TRole> NoAssignList = tRoleService.getNoAssignRole(adminId);
        map.addAttribute("list1",AssignList);
        map.addAttribute("list2",NoAssignList);
        mv.setViewName("assign-role");
        return mv;
    }


    @RequestMapping("/assign/do/role/assign.html")
    public ModelAndView saveRelationship(@RequestParam("adminId") Integer adminId,
                                         @RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("Keyword") String Keyword,
                                         @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList){
        ModelAndView mv = new ModelAndView();
        tAdminService.saveAdminRoleRelationship(adminId,roleIdList);
        mv.setViewName("redirect:/admin/get/page.html?pageNum="+pageNum+"&Keyword="+Keyword);
        return mv;
    }

}
