package cn.cdy.crowd.controller;

import cn.cdy.crowd.api.MySqlRemoteService;
import cn.cdy.crowd.api.RedisRemoteService;

import cn.cdy.crowd.entity.po.TMemberPO;
import cn.cdy.crowd.entity.vo.TMemberLoginVO;
import cn.cdy.crowd.entity.vo.TMemberVO;
import cn.cdy.crowd.util.CrowdConstant;
import cn.cdy.crowd.util.CrowdUtil;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Controller
public class MemberController {
    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySqlRemoteService mySqlRemoteService;
    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum){
        String host = "https://smssend.shumaidata.com";
        String path = "/sms/send";
        String method = "POST";
        String appcode = "cb74ba74cd4e4c6fb6c2dcea9deb0bd2";
        String templateId = "M09DD535F4";
        ResultEntity<String> result = CrowdUtil.sendShortMessage(host,path,method,phoneNum,appcode,templateId);
        if(ResultEntity.SUCCESS.equals(result.getResult())){
            String code = result.getData();
            String key = CrowdConstant.REDIS_CODE_PREFIX+phoneNum;
            ResultEntity<String> saveCode = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code, 15, TimeUnit.DAYS);
            if(ResultEntity.SUCCESS.equals(saveCode.getResult())){
                return ResultEntity.successWithoutData();
            }else{
                return saveCode;
            }
        }else{
            return result;
        }
    }

    @RequestMapping("/auth/do/member/register")
    public String register(TMemberVO memberVO, ModelMap map){
        String phoneNum = memberVO.getPhoneNum();
        String key = CrowdConstant.REDIS_CODE_PREFIX+phoneNum;
        ResultEntity<String> resultEntity = redisRemoteService.getRedisStringValueByKeyRemote(key);
        String result = resultEntity.getResult();
        if(ResultEntity.FAILED.equals(result)){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,resultEntity.getMessage());
            return "member-reg";
        }
        String redisCode = resultEntity.getData();
        if(redisCode == null){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.CODE_NOT_EXIST);
            return "member-reg";
        }
        String formCode = memberVO.getCode();
        if(!Objects.equals(formCode,redisCode)){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_CODE_INVALID);
            return "member-reg";
        }
        //redisRemoteService.removeRedisKeyRemote(key);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userpswd = memberVO.getUserpswd();
        String encode = bCryptPasswordEncoder.encode(userpswd);
        memberVO.setUserpswd(encode);
        TMemberPO memberPO = new TMemberPO();
        BeanUtils.copyProperties(memberVO,memberPO);
        ResultEntity<String> saveResultEntity = mySqlRemoteService.saveMember(memberPO);
        if(ResultEntity.FAILED.equals(saveResultEntity.getResult())){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,saveResultEntity.getMessage());
            return "member-reg";
        }
        return "redirect:http://21t041718c.imwork.net/auth/member/to/login/page.html";
    }

    @RequestMapping("/auth/member/do/login")
    public String login(@RequestParam("loginacct") String loginacct,
                        @RequestParam("userpswd") String userpswd,
                        ModelMap map, HttpSession session){
        ResultEntity<TMemberPO> logininfo = mySqlRemoteService.getMemberPOByLoginAcctRemote(loginacct);
        if(ResultEntity.FAILED.equals(logininfo.getResult())){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,logininfo.getMessage());
            return "member-login";
        }
        TMemberPO memberPO = logininfo.getData();
        System.out.println(memberPO);
        if(memberPO == null){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        String password = memberPO.getUserpswd();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matchesRes = bCryptPasswordEncoder.matches(userpswd, password);
        if(!matchesRes){
            map.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        TMemberLoginVO tMemberLoginVO = new TMemberLoginVO(memberPO.getId(),memberPO.getUsername(),memberPO.getEmail());
        session.setAttribute(CrowdConstant.ATTR_LOGIN_MEMBER,tMemberLoginVO);
        return "redirect:http://21t041718c.imwork.net/auth/member/to/center/page.html";
    }

    @RequestMapping("/auth/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:http://21t041718c.imwork.net/";
    }
}
