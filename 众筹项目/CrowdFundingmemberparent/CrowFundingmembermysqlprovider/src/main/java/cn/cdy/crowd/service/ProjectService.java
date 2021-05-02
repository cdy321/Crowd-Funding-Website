package cn.cdy.crowd.service;

import cn.cdy.crowd.entity.po.TProjectPO;
import cn.cdy.crowd.entity.vo.DetailProjectVO;
import cn.cdy.crowd.entity.vo.PortalTypeVO;
import cn.cdy.crowd.entity.vo.TProjectVO;

import java.util.List;

public interface ProjectService {
    void saveProject(TProjectVO projectVO, Integer memberId);
    List<PortalTypeVO> getPortalTypeVO();
    DetailProjectVO getDetailProjectVO(Integer projectId);
}
