package cn.cdy.crowd.dao;

import cn.cdy.crowd.entity.po.TMemberPO;

import java.util.List;

public interface TMemberPODao {
    void insertMember(TMemberPO tMemberPO);
    List<TMemberPO> selectMemberByLoginAcct(String loginacct);
}
