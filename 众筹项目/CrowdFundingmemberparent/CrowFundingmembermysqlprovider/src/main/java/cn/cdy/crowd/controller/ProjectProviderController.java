package cn.cdy.crowd.controller;


import cn.cdy.crowd.entity.vo.DetailProjectVO;
import cn.cdy.crowd.entity.vo.PortalTypeVO;
import cn.cdy.crowd.entity.vo.TProjectVO;
import cn.cdy.crowd.service.ProjectService;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TProject)表控制层
 *
 * @author makejava
 * @since 2021-04-22 17:28:56
 */
@RestController
public class ProjectProviderController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/save/project/vo/remote")
    public ResultEntity<String> saveProjectRemote(@RequestBody TProjectVO projectVO,
                                                  @RequestParam("memberId") Integer memberId){
        try{
            projectService.saveProject(projectVO,memberId);
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/get/portal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectRemote(){
        try{
            List<PortalTypeVO> portalTypeVOList = projectService.getPortalTypeVO();
            return ResultEntity.successWithData(portalTypeVOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }

    @RequestMapping("/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId")
                                                                          Integer projectId) {
        try {
            DetailProjectVO detailProjectVO = projectService.getDetailProjectVO(projectId);
            return ResultEntity.successWithData(detailProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
    }
}
