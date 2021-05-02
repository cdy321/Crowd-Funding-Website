package cn.cdy.crowd.service;

import cn.cdy.crowd.entity.TAdmin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (TAdmin)表服务接口
 *
 * @author makejava
 * @since 2021-04-11 11:13:01
 */
public interface TAdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TAdmin queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TAdmin> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tAdmin 实例对象
     * @return 实例对象
     */
    TAdmin insert(TAdmin tAdmin);

    /**
     * 修改数据
     *
     * @param tAdmin 实例对象
     * @return 实例对象
     */
    TAdmin update(TAdmin tAdmin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    TAdmin getAdmin(String loginAcct,String userPswd);

    PageInfo<TAdmin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    void saveAdminRoleRelationship(Integer adminId,List<Integer> roleIdList);

    TAdmin getAdminByLoginAcct(String loginAcct);
}
