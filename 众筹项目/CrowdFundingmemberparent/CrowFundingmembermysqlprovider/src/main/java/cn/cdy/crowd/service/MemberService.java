package cn.cdy.crowd.service;

import cn.cdy.crowd.entity.po.TMemberPO;

public interface MemberService {
    TMemberPO getMemberByLoginAcct(String loginacct);
    void saveMember(TMemberPO memberPO);
}
