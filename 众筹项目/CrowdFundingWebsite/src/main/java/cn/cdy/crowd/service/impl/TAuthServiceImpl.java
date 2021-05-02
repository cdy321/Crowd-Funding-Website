package cn.cdy.crowd.service.impl;

import cn.cdy.crowd.dao.TAuthDao;
import cn.cdy.crowd.entity.TAuth;
import cn.cdy.crowd.service.TAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (TAuth)表服务实现类
 *
 * @author makejava
 * @since 2021-04-16 17:28:16
 */
@Service("tAuthService")
public class TAuthServiceImpl implements TAuthService {
    @Resource
    private TAuthDao tAuthDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TAuth queryById(Integer id) {
        return this.tAuthDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TAuth> queryAllByLimit(int offset, int limit) {
        return this.tAuthDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tAuth 实例对象
     * @return 实例对象
     */
    @Override
    public TAuth insert(TAuth tAuth) {
        this.tAuthDao.insert(tAuth);
        return tAuth;
    }

    /**
     * 修改数据
     *
     * @param tAuth 实例对象
     * @return 实例对象
     */
    @Override
    public TAuth update(TAuth tAuth) {
        this.tAuthDao.update(tAuth);
        return this.queryById(tAuth.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tAuthDao.deleteById(id) > 0;
    }

    @Override
    public List<TAuth> getAll() {
        return tAuthDao.queryAll();
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return tAuthDao.getAuthIdByRoleId(roleId);
    }

    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        tAuthDao.deleteOldRelationship(roleId);
        List<Integer> authIdList = map.get("authIdArray");
        if(authIdList != null && authIdList.size() > 0){
            tAuthDao.insertNewRelationship(roleId,authIdList);
        }
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {
        return tAuthDao.selectAssignedAuthNameByAdminId(adminId);
    }
}
