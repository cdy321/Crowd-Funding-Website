package cn.cdy.crowd.dao;

import cn.cdy.crowd.entity.TMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TMenu)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-15 12:36:19
 */
public interface TMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TMenu queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TMenu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);



    List<TMenu> queryAll();

    /**
     * 新增数据
     *
     * @param tMenu 实例对象
     * @return 影响行数
     */
    int insert(TMenu tMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TMenu> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TMenu> entities);

    /**
     * 修改数据
     *
     * @param tMenu 实例对象
     * @return 影响行数
     */
    int update(TMenu tMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

