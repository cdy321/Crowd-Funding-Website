package cn.cdy.crowd.controller;

import cn.cdy.crowd.api.MySqlRemoteService;
import cn.cdy.crowd.entity.vo.AddressVO;
import cn.cdy.crowd.entity.vo.OrderProjectVO;
import cn.cdy.crowd.entity.vo.TMemberLoginVO;
import cn.cdy.crowd.util.CrowdConstant;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private MySqlRemoteService mySqlRemoteService;

    @RequestMapping("/confirm/return/info/{projectId}/{returnId}")
    public String showreturnConfirmInfo(@PathVariable("projectId") Integer projectId,
                                        @PathVariable("returnId") Integer returnId,
                                        HttpSession session){
        ResultEntity<OrderProjectVO> resultEntity = mySqlRemoteService.getOrderProjectVORemote(projectId, returnId);
        if(ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            OrderProjectVO orderProjectVO = resultEntity.getData();
            session.setAttribute("orderProjectVO", orderProjectVO);
        }
        return "confirm-return";
    }

    @RequestMapping("/confirm/order/{returnCount}")
    public String showConfirmOrderInfo(
            @PathVariable("returnCount") Integer returnCount, HttpSession session) {
          // 1.把接收到的回报数量合并到 Session 域
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");
        orderProjectVO.setReturnCount(returnCount);
        session.setAttribute("orderProjectVO", orderProjectVO);
        // 2.获取当前已登录用户的 id
        TMemberLoginVO memberLoginVO = (TMemberLoginVO)
                session.getAttribute(CrowdConstant.ATTR_LOGIN_MEMBER);
        Integer memberId = memberLoginVO.getId();
      // 3.查询目前的收货地址数据
        ResultEntity<List<AddressVO>> resultEntity = mySqlRemoteService.getAddressVORemote(memberId);
        if(ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            List<AddressVO> list = resultEntity.getData();
            System.out.println(list);
            session.setAttribute("addressVOList", list);
        }
        return "confirm-order";
    }
    @RequestMapping("/save/address")
    public String saveAddress(AddressVO addressVO, HttpSession session){
        ResultEntity<String> resultEntity = mySqlRemoteService.saveAddressRemote(addressVO);
        OrderProjectVO orderProjectVO = (OrderProjectVO)session.getAttribute("orderProjectVO");
        Integer returnCount = orderProjectVO.getReturnCount();
        return "redirect:http://21t041718c.imwork.net/order/confirm/order/"+returnCount;
    }
}
