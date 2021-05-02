package cn.cdy.crowd.service;

import cn.cdy.crowd.entity.TAuth;

import java.util.List;
import java.util.Map;

/**
 * (TAuth)表服务接口
 *
 * @author makejava
 * @since 2021-04-16 17:28:16
 */
public interface TAuthService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TAuth queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TAuth> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tAuth 实例对象
     * @return 实例对象
     */
    TAuth insert(TAuth tAuth);

    /**
     * 修改数据
     *
     * @param tAuth 实例对象
     * @return 实例对象
     */
    TAuth update(TAuth tAuth);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<TAuth> getAll();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAuthRelationship(Map<String,List<Integer>> map);

    List<String> getAssignedAuthNameByAdminId(Integer adminId);
}
