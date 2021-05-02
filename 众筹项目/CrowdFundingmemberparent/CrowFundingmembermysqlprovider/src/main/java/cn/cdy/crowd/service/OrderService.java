package cn.cdy.crowd.service;

import cn.cdy.crowd.entity.vo.AddressVO;
import cn.cdy.crowd.entity.vo.OrderProjectVO;
import cn.cdy.crowd.entity.vo.OrderVO;

import java.util.List;

public interface OrderService {

    OrderProjectVO getOrderProjectVO(Integer projectId,Integer returnId);

    List<AddressVO> getAddressVOList(Integer memberId);


    void saveAddress(AddressVO addressVO);

    void saveOrder(OrderVO orderVO);

}
