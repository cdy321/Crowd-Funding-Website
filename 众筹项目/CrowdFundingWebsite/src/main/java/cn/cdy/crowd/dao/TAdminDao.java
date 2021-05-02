package cn.cdy.crowd.dao;

import cn.cdy.crowd.entity.TAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TAdmin)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-11 11:13:00
 */
public interface TAdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TAdmin queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TAdmin> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tAdmin 实例对象
     * @return 对象列表
     */
    List<TAdmin> queryAll(TAdmin tAdmin);

    /**
     * 新增数据
     *
     * @param tAdmin 实例对象
     * @return 影响行数
     */
    int insert(TAdmin tAdmin);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TAdmin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TAdmin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TAdmin> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TAdmin> entities);

    /**
     * 修改数据
     *
     * @param tAdmin 实例对象
     * @return 影响行数
     */
    int update(TAdmin tAdmin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<TAdmin> getAdminByLoginAcct(@Param("loginAcct") String loginAcct);


    List<TAdmin> getAdminByKeywords(@Param("Keyword") String Keyword);

    void deleteOldRelationship(@Param("adminId") Integer adminId);

    void insertNewRelationship(@Param("adminId") Integer adminId,@Param("roleIdList") List<Integer> roleIdList);

    List<TAdmin> selectAdminByLoginAcct(String loginAcct);
}

