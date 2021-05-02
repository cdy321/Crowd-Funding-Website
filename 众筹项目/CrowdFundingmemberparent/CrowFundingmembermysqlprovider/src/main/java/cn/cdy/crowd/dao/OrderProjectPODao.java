package cn.cdy.crowd.dao;


import cn.cdy.crowd.entity.po.OrderProjectPO;
import cn.cdy.crowd.entity.vo.OrderProjectVO;

public interface OrderProjectPODao {
    OrderProjectVO selectOrderProjectVO(Integer returnId);
    void insert(OrderProjectPO orderProjectPO);
}
