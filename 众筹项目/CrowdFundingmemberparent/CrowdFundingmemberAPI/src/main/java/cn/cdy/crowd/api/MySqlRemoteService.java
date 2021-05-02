package cn.cdy.crowd.api;

import cn.cdy.crowd.entity.po.TMemberPO;
import cn.cdy.crowd.entity.vo.*;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("cncdy-crowd-mysql")
public interface MySqlRemoteService {
    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<TMemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);

    @RequestMapping("/save/member/remote")
    ResultEntity<String> saveMember(@RequestBody TMemberPO memberPO);

    @RequestMapping("/save/project/vo/remote")
    ResultEntity<String> saveProjectRemote(@RequestBody TProjectVO projectVO,
                                           @RequestParam("memberId") Integer memberId);


    @RequestMapping("/get/portal/type/project/data/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectRemote();

    @RequestMapping("/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId")
                                                                          Integer projectId);

    @RequestMapping("/get/order/project/vo/remote")
    ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId")
                                                                 Integer projectId,
                                                         @RequestParam("returnId") Integer returnId);
    @RequestMapping("/get/Address/vo/Remote")
    ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId);

    @RequestMapping("/save/Address/vo/Remote")
    ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO);

    @RequestMapping("/save/order/remote")
    ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO);

}
