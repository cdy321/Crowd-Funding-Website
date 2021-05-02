package cn.cdy.crowd.entity;

import java.io.Serializable;

/**
 * (TAuth)实体类
 *
 * @author makejava
 * @since 2021-04-16 17:28:16
 */
public class TAuth implements Serializable {
    private static final long serialVersionUID = 721196874276930884L;

    private Integer id;

    private String name;

    private String title;

    private Integer categoryId;

    public TAuth(Integer id, String name, String title, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.categoryId = categoryId;
    }

    public TAuth() {

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "TAuth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
