package cn.cdy.crowd.service.impl;

import cn.cdy.crowd.dao.TMemberPODao;
import cn.cdy.crowd.entity.po.TMemberPO;
import cn.cdy.crowd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private TMemberPODao tMemberPODao;

    @Override
    public TMemberPO getMemberByLoginAcct(String loginacct) {
        List<TMemberPO> list = tMemberPODao.selectMemberByLoginAcct(loginacct);
        if(list == null || list.size() == 0){
            return null;
        }
        return list.get(0);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class,readOnly = false)
    @Override
    public void saveMember(TMemberPO memberPO) {
        tMemberPODao.insertMember(memberPO);
    }
}
