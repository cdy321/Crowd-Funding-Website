package cn.cdy.crowd.controller;

import cn.cdy.crowd.entity.po.TMemberPO;
import cn.cdy.crowd.service.MemberService;
import cn.cdy.crowd.util.CrowdConstant;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberProviderController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    public ResultEntity<TMemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct) {
        try {
            TMemberPO memberPO = memberService.getMemberByLoginAcct(loginacct);
            return ResultEntity.successWithData(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody TMemberPO memberPO) {
        try {
            memberService.saveMember(memberPO);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            if(e instanceof DuplicateKeyException){
                return ResultEntity.fail(CrowdConstant.MESSAGE_LOGIN_ALREADY_IN_USE);
            }
            return ResultEntity.fail(e.getMessage());
        }
    }
}
