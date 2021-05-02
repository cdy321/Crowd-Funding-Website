package cn.cdy.crowd.dao;

import cn.cdy.crowd.entity.po.TReturnPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnPODao {
    void insertReturnPOBatch(@Param("projectId") Integer projectId,
                             @Param("returnPOList") List<TReturnPO> returnPOList);
}
