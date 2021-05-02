package cn.cdy.crowd.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * (TMenu)实体类
 *
 * @author makejava
 * @since 2021-04-15 12:30:05
 */
public class TMenu implements Serializable {
    private static final long serialVersionUID = 530200558349420315L;

    private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private String icon;

    private List<TMenu> children = new ArrayList<>();

    private boolean open = true;

    public TMenu() {
    }

    public TMenu(Integer id, Integer pid, String name, String url, String icon, List<TMenu> children, boolean open) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.children = children;
        this.open = open;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<TMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TMenu> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "TMenu{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                ", open=" + open +
                '}';
    }
}
