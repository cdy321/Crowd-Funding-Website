package cn.cdy.crowd.controller;

import cn.cdy.crowd.entity.TMenu;
import cn.cdy.crowd.service.TMenuService;
import cn.cdy.crowd.util.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TMenu)表控制层
 *
 * @author makejava
 * @since 2021-04-15 12:36:19
 */
@Controller
public class TMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TMenuService tMenuService;

    //寻找根节点
    @ResponseBody
    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<TMenu> getWholeTree(){
        List<TMenu> menuList = tMenuService.getAllMenu();
        TMenu root = null;
        Map<Integer,TMenu> map = new HashMap<>();
        for (TMenu tmenu : menuList) {
            Integer id = tmenu.getId();
            map.put(id,tmenu);
        }
        for (TMenu tmenu : menuList) {
            Integer pid = tmenu.getPid();
            if(pid == null){
                root = tmenu;
                continue;
            }
            TMenu father = map.get(pid);
            father.getChildren().add(tmenu);
        }
        return ResultEntity.successWithData(root);
    }


    @ResponseBody
    @RequestMapping("/menu/save.json")
    public ResultEntity<String> saveMenu(TMenu menu){
        tMenuService.insert(menu);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("menu/update.json")
    public ResultEntity<String> updateMenu(TMenu menu){
        tMenuService.update(menu);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("menu/remove.json")
    public ResultEntity<String> removeMenu(@RequestParam("id") Integer id){
        tMenuService.deleteById(id);
        return ResultEntity.successWithoutData();
    }
}
