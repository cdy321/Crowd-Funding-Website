package cn.cdy.crowd.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectItemPicPODao {
    void insertPathList(@Param("projectId") Integer projectId,
                        @Param("detailPicturePathList") List<String> detailPicturePathList);

}
