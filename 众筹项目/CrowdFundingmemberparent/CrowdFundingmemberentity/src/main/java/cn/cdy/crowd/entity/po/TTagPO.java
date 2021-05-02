package cn.cdy.crowd.entity.po;

import java.io.Serializable;

/**
 * (TTag)实体类
 *
 * @author makejava
 * @since 2021-04-22 17:17:26
 */
public class TTagPO implements Serializable {
    private static final long serialVersionUID = 944668120119096271L;

    private Integer id;

    private Integer pid;

    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
