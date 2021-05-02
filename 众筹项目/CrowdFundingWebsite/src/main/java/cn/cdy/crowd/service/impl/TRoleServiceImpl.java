package cn.cdy.crowd.service.impl;

import cn.cdy.crowd.dao.TRoleDao;
import cn.cdy.crowd.entity.TRole;
import cn.cdy.crowd.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * (TRole)表服务实现类
 *
 * @author makejava
 * @since 2021-04-14 15:06:25
 */
@Service("tRoleService")
public class TRoleServiceImpl implements TRoleService {
    @Resource
    private TRoleDao tRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TRole queryById(Integer id) {
        return this.tRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TRole> queryAllByLimit(int offset, int limit) {
        return this.tRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    @Override
    public TRole insert(TRole tRole) {
        this.tRoleDao.insert(tRole);
        return tRole;
    }

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    @Override
    public TRole update(TRole tRole) {
        this.tRoleDao.update(tRole);
        return this.queryById(tRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tRoleDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<TRole> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TRole> roleList = tRoleDao.getRoleByKeywords(keyword);
        return new PageInfo<>(roleList);
    }

    @Override
    public void deleteBatch(List<Integer> idList) {
        for (Integer id : idList) {
            tRoleDao.deleteById(id);
        }
    }

    @Override
    public List<TRole> getAssignedRole(Integer adminId) {
        return tRoleDao.getAssignByAdminId(adminId);
    }

    @Override
    public List<TRole> getNoAssignRole(Integer adminId) {
        return tRoleDao.getNoAssignByAdminId(adminId);
    }
}
