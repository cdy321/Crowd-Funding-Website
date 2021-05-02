package cn.cdy.crowd.service.impl;

import cn.cdy.crowd.dao.AddressPODao;
import cn.cdy.crowd.dao.OrderPODao;
import cn.cdy.crowd.dao.OrderProjectPODao;
import cn.cdy.crowd.entity.po.AddressPO;
import cn.cdy.crowd.entity.po.OrderPO;
import cn.cdy.crowd.entity.po.OrderProjectPO;
import cn.cdy.crowd.entity.vo.AddressVO;
import cn.cdy.crowd.entity.vo.OrderProjectVO;
import cn.cdy.crowd.entity.vo.OrderVO;
import cn.cdy.crowd.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderProjectPODao orderProjectPODao;
    @Autowired
    private OrderPODao orderPODao;
    @Autowired
    private AddressPODao addressPODao;

    @Override
    public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {
        OrderProjectVO orderProjectVO = orderProjectPODao.selectOrderProjectVO(returnId);
        return orderProjectVO;
    }

    @Override
    public List<AddressVO> getAddressVOList(Integer memberId) {
        List<AddressPO> addressPOList = addressPODao.selectAddressByMemberId(memberId);
        List<AddressVO> addressVOList = new ArrayList<>();
        for (AddressPO addressPO : addressPOList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO,addressVO);
            addressVOList.add(addressVO);
        }
        return addressVOList;
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public void saveAddress(AddressVO addressVO) {
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO,addressPO);
        addressPODao.insert(addressPO);
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public void saveOrder(OrderVO orderVO) {
        OrderPO orderPO = new OrderPO();
        BeanUtils.copyProperties(orderVO,orderPO);
        OrderProjectPO orderProjectPO = new OrderProjectPO();
        BeanUtils.copyProperties(orderVO.getOrderProjectVO(),orderProjectPO);
        orderPODao.insert(orderPO);
        Integer id = orderPO.getId();
        orderProjectPO.setOrderId(id);
        orderProjectPODao.insert(orderProjectPO);

    }
}
