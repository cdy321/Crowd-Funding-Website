package cn.cdy.crowd.controller;

import cn.cdy.crowd.entity.vo.AddressVO;
import cn.cdy.crowd.entity.vo.OrderProjectVO;
import cn.cdy.crowd.entity.vo.OrderVO;
import cn.cdy.crowd.service.OrderService;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderProviderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/get/order/project/vo/remote")
    public ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId")
                                                                 Integer projectId,
                                                                @RequestParam("returnId") Integer returnId){
        try{
            OrderProjectVO orderProjectVO = orderService.getOrderProjectVO(projectId,returnId);
            return ResultEntity.successWithData(orderProjectVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/get/Address/vo/Remote")
    public ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId){
        try {
            List<AddressVO> addressVOList = orderService.getAddressVOList(memberId);
            return ResultEntity.successWithData(addressVOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/save/Address/vo/Remote")
    public ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO){
        try {
            orderService.saveAddress(addressVO);
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/save/order/remote")
    public ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO){
        try {
            orderService.saveOrder(orderVO);
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

}
