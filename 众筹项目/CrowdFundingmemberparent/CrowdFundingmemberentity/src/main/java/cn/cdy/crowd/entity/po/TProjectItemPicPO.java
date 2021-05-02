package cn.cdy.crowd.entity.po;

import java.io.Serializable;

/**
 * (TProjectItemPic)实体类
 *
 * @author makejava
 * @since 2021-04-22 17:20:29
 */
public class TProjectItemPicPO implements Serializable {
    private static final long serialVersionUID = 370293779823187018L;

    private Integer id;

    private Integer projectid;

    private String itemPicPath;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getItemPicPath() {
        return itemPicPath;
    }

    public void setItemPicPath(String itemPicPath) {
        this.itemPicPath = itemPicPath;
    }

}
