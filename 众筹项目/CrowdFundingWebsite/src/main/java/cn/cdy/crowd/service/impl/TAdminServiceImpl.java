package cn.cdy.crowd.service.impl;

import cn.cdy.crowd.dao.TAdminDao;
import cn.cdy.crowd.entity.TAdmin;
import cn.cdy.crowd.exception.LoginFailedException;
import cn.cdy.crowd.service.TAdminService;
import cn.cdy.crowd.util.CrowdConstant;
import cn.cdy.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * (TAdmin)表服务实现类
 *
 * @author makejava
 * @since 2021-04-11 11:13:01
 */
@Service("tAdminService")
public class TAdminServiceImpl implements TAdminService {
    @Resource
    private TAdminDao tAdminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TAdmin queryById(Integer id) {
        return this.tAdminDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TAdmin> queryAllByLimit(int offset, int limit) {
        return this.tAdminDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tAdmin 实例对象
     * @return 实例对象
     */
    @Override
    public TAdmin insert(TAdmin tAdmin) {
        this.tAdminDao.insert(tAdmin);
        return tAdmin;
    }

    /**
     * 修改数据
     *
     * @param tAdmin 实例对象
     * @return 实例对象
     */
    @Override
    public TAdmin update(TAdmin tAdmin) {
        this.tAdminDao.update(tAdmin);
        return this.queryById(tAdmin.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tAdminDao.deleteById(id) > 0;
    }

    @Override
    public TAdmin getAdmin(String loginAcct,String userPswd){
        List<TAdmin> adminList = this.tAdminDao.getAdminByLoginAcct(loginAcct);
        if(adminList == null || adminList.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if(adminList.size() > 1){
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        TAdmin admin = adminList.get(0);
        if(admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        String userPswdDB = admin.getUserPswd();
        System.out.println(userPswdDB);
        String userPswdForm = CrowdUtil.md5(userPswd);
        System.out.println(userPswdForm);
        if(!Objects.equals(userPswdDB,userPswdForm)){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }

    @Override
    public PageInfo<TAdmin> getPageInfo(String Keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TAdmin> list = tAdminDao.getAdminByKeywords(Keyword);
        return new PageInfo<>(list);
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        tAdminDao.deleteOldRelationship(adminId);
        if(roleIdList != null && roleIdList.size() > 0){
            tAdminDao.insertNewRelationship(adminId,roleIdList);
        }
    }

    @Override
    public TAdmin getAdminByLoginAcct(String loginAcct) {
        List<TAdmin> adminList = tAdminDao.selectAdminByLoginAcct(loginAcct);
        return adminList.get(0);
    }
}
