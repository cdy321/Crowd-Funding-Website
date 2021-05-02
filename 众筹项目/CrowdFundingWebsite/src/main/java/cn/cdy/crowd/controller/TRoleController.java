package cn.cdy.crowd.controller;

import cn.cdy.crowd.entity.TRole;
import cn.cdy.crowd.service.TRoleService;
import cn.cdy.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TRole)表控制层
 *
 * @author makejava
 * @since 2021-04-14 15:05:14
 */
@Controller
public class TRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleService tRoleService;


//分页
    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<TRole>> getPageInfo(
            @RequestParam(value = "Keyword",defaultValue = "") String Keyword,
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        PageInfo<TRole> pageInfo = tRoleService.getPageInfo(Keyword, pageNum, pageSize);
        System.out.println(pageInfo.getList());
        return ResultEntity.successWithData(pageInfo);
    }



//保存
    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity<String> saveRole(TRole role){
        tRoleService.insert(role);
        return ResultEntity.successWithoutData();
    }

    //更新
    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity<String> updateRole(TRole role){
        tRoleService.update(role);
        return ResultEntity.successWithoutData();
    }


    //删除
    @ResponseBody
    @RequestMapping("/role/delete/by/role/id/array.json")
    public ResultEntity<String> removeRoleIdArray(@RequestBody List<Integer> roleIdList){
        tRoleService.deleteBatch(roleIdList);
        return ResultEntity.successWithoutData();
    }

}
