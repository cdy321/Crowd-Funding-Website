package cn.cdy.crowd.dao;

import cn.cdy.crowd.entity.po.TProjectPO;
import cn.cdy.crowd.entity.vo.DetailProjectVO;
import cn.cdy.crowd.entity.vo.PortalTypeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPODao {
    void insertSelective(TProjectPO projectPO);
    void insertTypeRelationship(@Param("projectId") Integer projectId,
                                @Param("typeIdList") List<Integer> typeIdList);

    void insertTagRelationship(@Param("projectId") Integer projectId,
                               @Param("tagIdList") List<Integer> tagIdList);
    List<PortalTypeVO> selectPortalVOList();

    DetailProjectVO selectDetailProjectVO(Integer projectId);
}
