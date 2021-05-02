package cn.cdy.crowd.dao;

import cn.cdy.crowd.entity.po.AddressPO;

import java.util.List;

public interface AddressPODao {
    List<AddressPO> selectAddressByMemberId(Integer memberId);

    void insert(AddressPO addressPO);
}
