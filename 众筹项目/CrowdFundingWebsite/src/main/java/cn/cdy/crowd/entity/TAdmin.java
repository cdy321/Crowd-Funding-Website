package cn.cdy.crowd.entity;

import java.io.Serializable;

/**
 * (TAdmin)实体类
 *
 * @author makejava
 * @since 2021-04-11 11:12:59
 */
public class TAdmin implements Serializable {
    private static final long serialVersionUID = 918779058495071183L;

    private Integer id;

    private String loginAcct;

    private String userPswd;

    private String email;

    private String createTime;

    private String userName;

    public TAdmin() {
    }

    public TAdmin(Integer id, String loginAcct, String userPswd, String email, String createTime,String userName) {
        this.id = id;
        this.loginAcct = loginAcct;
        this.userPswd = userPswd;
        this.email = email;
        this.createTime = createTime;
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TAdmin{" +
                "id=" + id +
                ", loginAcct='" + loginAcct + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

