package cn.cdy.crowd.controller;

import cn.cdy.crowd.entity.TAdmin;
import cn.cdy.crowd.exception.LoginAcctAlreadyInUseException;
import cn.cdy.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import cn.cdy.crowd.service.TAdminService;
import cn.cdy.crowd.util.CrowdConstant;
import cn.cdy.crowd.util.CrowdUtil;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (TAdmin)表控制层
 *
 * @author makejava
 * @since 2021-04-11 11:13:02
 */
@Controller
public class TAdminController {
    /**
     * 服务对象
     */
    @Resource
    private TAdminService tAdminService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping("/test/ssm.html")
    @ResponseBody
    public ModelAndView testSSM(){
        ModelAndView mv = new ModelAndView();
        List<TAdmin> list = tAdminService.queryAllByLimit(0,10);
        mv.addObject("list",list);
        mv.setViewName("target");
        return mv;
    }

    @RequestMapping("/test/ajax.html")
    public ModelAndView testAjax(String name,String password){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.addObject("password",password);
        mv.setViewName("result");
        return mv;
    }
    //登录业务
    @RequestMapping("admin/do/login.html")
    public ModelAndView doLogin(@RequestParam("loginAcct") String loginAcct,
                                @RequestParam("userPswd") String userPswd,
                                HttpSession session){
        System.out.println(loginAcct);
        System.out.println(userPswd);
        TAdmin admin = tAdminService.getAdmin(loginAcct, userPswd);
        System.out.println(admin);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/admin/to/main/page.html");
        return mv;
    }
    //退出
    @RequestMapping(value = "security/do/logout.html")
    public ModelAndView doLogout(HttpSession session){
        ModelAndView mv = new ModelAndView();
        session.invalidate();
        mv.setViewName("redirect:/admin/to/login/page.html");
        return mv;
    }
    //分页
    @RequestMapping(value = "/admin/get/page.html")
    public ModelAndView getPageInfo(@RequestParam(value = "Keyword",defaultValue = "") String Keyword,
                                    @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                    ModelMap map){
        ModelAndView mv = new ModelAndView();
        PageInfo<TAdmin> pageInfo = tAdminService.getPageInfo(Keyword, pageNum, pageSize);
        map.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);
        mv.setViewName("admin-page");
        return mv;
    }

    //删除
    @RequestMapping(value = "/admin/remove/{adminId}/{pageNum}/{Keyword}.html")
    public ModelAndView remove(@PathVariable("adminId") Integer id,
                               @PathVariable("pageNum") Integer pageNum,
                               @PathVariable("Keyword") String Keyword){
        ModelAndView mv = new ModelAndView();
        tAdminService.deleteById(id);
        mv.setViewName("redirect:/admin/get/page.html?pageNum="+pageNum+"&Keyword="+Keyword);
        return mv;
    }

    //新增管理员
    @PreAuthorize("hasAuthority('user:save')")
    @RequestMapping(value = "/admin/save.html")
    public ModelAndView save(TAdmin admin){
        ModelAndView mv = new ModelAndView();
        String userPswd = admin.getUserPswd();
        //userPswd = CrowdUtil.md5(userPswd);
        userPswd = bCryptPasswordEncoder.encode(userPswd);
        admin.setUserPswd(userPswd);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        admin.setCreateTime(createTime);
        try {
              tAdminService.insert(admin);
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
        }
        mv.setViewName("redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE);
        return mv;
    }
    //查询修改信息
    @RequestMapping(value = "/admin/to/edit/page.html")
    public ModelAndView updateAim(
            @RequestParam("adminId") Integer id,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("Keyword") String Keyword,
            ModelMap map
    ){
        ModelAndView mv = new ModelAndView();
        TAdmin admin = tAdminService.queryById(id);
        map.addAttribute("admin",admin);
        mv.setViewName("admin-update");
        return mv;
    }
    //更新信息
    @RequestMapping("/admin/update.html")
    public ModelAndView update(TAdmin admin,
                               @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("Keyword") String Keyword){
        ModelAndView mv = new ModelAndView();
        try{
            tAdminService.update(admin);
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
        }
        mv.setViewName("redirect:/admin/get/page.html?pageNum="+pageNum+"&Keyword="+Keyword);
        return mv;
    }


    @ResponseBody
    @RequestMapping("send/array.html")
    public ModelAndView test01(@RequestBody List<Integer> array){
        ModelAndView mv = new ModelAndView();
        for (Integer num : array) {
            System.out.println(num);
        }
        mv.setViewName("target");
        return mv;
    }
    @RequestMapping("admin1/do/login.html")
    public ModelAndView test02(){
        ModelAndView mv = new ModelAndView();
        System.out.println(10/0);
        mv.setViewName("system-error");
        return mv;
    }
}
