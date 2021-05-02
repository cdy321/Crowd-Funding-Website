package cn.cdy.crowd.entity;

import java.io.Serializable;

/**
 * (TRole)实体类
 *
 * @author makejava
 * @since 2021-04-14 15:02:35
 */
public class TRole implements Serializable {
    private static final long serialVersionUID = 178711180582343708L;

    private Integer id;

    private String name;

    public TRole(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TRole() {
    }

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

    @Override
    public String toString() {
        return "TRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
