package cn.cdy.crowd.controller;

import cn.cdy.crowd.entity.TAuth;
import cn.cdy.crowd.service.TAuthService;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (TAuth)表控制层
 *
 * @author makejava
 * @since 2021-04-16 17:28:17
 */
@RestController
public class TAuthController {
    /**
     * 服务对象
     */
    @Resource
    private TAuthService tAuthService;

    @ResponseBody
    @RequestMapping("assgin/get/all/auth.json")
    public ResultEntity<List<TAuth>> getAllAuth(){
        List<TAuth> authList = tAuthService.getAll();
        return ResultEntity.successWithData(authList);
    }

    @ResponseBody
    @RequestMapping("assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(@RequestParam("roleId") Integer roleId){
        List<Integer> authIdList = tAuthService.getAssignedAuthIdByRoleId(roleId);
        return ResultEntity.successWithData(authIdList);
    }

    @ResponseBody
    @RequestMapping("assign/do/role/assign/auth.json")
    public ResultEntity<String> saveRoleAuthRelationship(@RequestBody Map<String,List<Integer>> map){
        tAuthService.saveRoleAuthRelationship(map);
        return ResultEntity.successWithoutData();
    }
}
