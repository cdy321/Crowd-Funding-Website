package cn.cdy.crowd.entity.po;

import java.io.Serializable;

/**
 * (TType)实体类
 *
 * @author makejava
 * @since 2021-04-22 17:14:39
 */
public class TTypePO implements Serializable {
    private static final long serialVersionUID = 831531600391863329L;

    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类介绍
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
