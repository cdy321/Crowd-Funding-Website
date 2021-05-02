package cn.cdy.crowd.dao;

import cn.cdy.crowd.entity.TAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TAuth)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-16 17:28:16
 */
public interface TAuthDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TAuth queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TAuth> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);



    List<TAuth> queryAll();

    /**
     * 新增数据
     *
     * @param tAuth 实例对象
     * @return 影响行数
     */
    int insert(TAuth tAuth);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TAuth> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TAuth> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TAuth> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TAuth> entities);

    /**
     * 修改数据
     *
     * @param tAuth 实例对象
     * @return 影响行数
     */
    int update(TAuth tAuth);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<Integer> getAuthIdByRoleId(@Param("roleId") Integer roleId);

    void deleteOldRelationship(@Param("roleId") Integer roleId);

    void insertNewRelationship(@Param("roleId") Integer roleId,@Param("authIdList") List<Integer> authIdList);

    List<String> selectAssignedAuthNameByAdminId(Integer adminId);

}

