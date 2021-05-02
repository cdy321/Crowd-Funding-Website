package cn.cdy.crowd.service.impl;

import cn.cdy.crowd.dao.TMenuDao;
import cn.cdy.crowd.entity.TMenu;
import cn.cdy.crowd.service.TMenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * (TMenu)表服务实现类
 *
 * @author makejava
 * @since 2021-04-15 12:36:19
 */
@Service("tMenuService")
public class TMenuServiceImpl implements TMenuService {
    @Resource
    private TMenuDao tMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TMenu queryById(Integer id) {
        return this.tMenuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TMenu> queryAllByLimit(int offset, int limit) {
        return this.tMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TMenu insert(TMenu tMenu) {
        this.tMenuDao.insert(tMenu);
        return tMenu;
    }

    /**
     * 修改数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TMenu update(TMenu tMenu) {
        this.tMenuDao.update(tMenu);
        return this.queryById(tMenu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tMenuDao.deleteById(id) > 0;
    }

    @Override
    public List<TMenu> getAllMenu() {
        return this.tMenuDao.queryAll();
    }
}
