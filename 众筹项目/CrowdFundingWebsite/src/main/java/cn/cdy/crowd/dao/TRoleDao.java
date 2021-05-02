package cn.cdy.crowd.dao;

import cn.cdy.crowd.entity.TRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TRole)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-14 15:03:19
 */
public interface TRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TRole queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tRole 实例对象
     * @return 对象列表
     */
    List<TRole> queryAll(TRole tRole);

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 影响行数
     */
    int insert(TRole tRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TRole> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TRole> entities);

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 影响行数
     */
    int update(TRole tRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<TRole> getRoleByKeywords(@Param("Keyword") String Keyword);

    List<TRole> getAssignByAdminId(Integer adminId);

    List<TRole> getNoAssignByAdminId(Integer adminId);

}

