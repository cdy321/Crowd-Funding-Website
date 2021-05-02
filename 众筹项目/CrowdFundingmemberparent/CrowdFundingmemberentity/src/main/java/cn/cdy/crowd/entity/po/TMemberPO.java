package cn.cdy.crowd.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * (TMember)实体类
 *
 * @author makejava
 * @since 2021-04-20 15:33:58
 */
public class TMemberPO implements Serializable {
    private static final long serialVersionUID = 161382818627770011L;

    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;
    /**
     * 实名认证状态 0 - 未实名认证， 1 - 实名认证申
     * 请中， 2 - 已实名认证
     */
    private Integer authstatus;
    /**
     * 0 - 个人， 1 - 企业
     */
    private Integer usertype;

    private String realname;

    private String cardnum;
    /**
     * 0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府
     */
    private Integer accttype;

    public TMemberPO() {
    }

    public TMemberPO(Integer id, String loginacct, String userpswd, String username, String email, Integer authstatus, Integer usertype, String realname, String cardnum, Integer accttype) {
        this.id = id;
        this.loginacct = loginacct;
        this.userpswd = userpswd;
        this.username = username;
        this.email = email;
        this.authstatus = authstatus;
        this.usertype = usertype;
        this.realname = realname;
        this.cardnum = cardnum;
        this.accttype = accttype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAuthstatus() {
        return authstatus;
    }

    public void setAuthstatus(Integer authstatus) {
        this.authstatus = authstatus;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public Integer getAccttype() {
        return accttype;
    }

    public void setAccttype(Integer accttype) {
        this.accttype = accttype;
    }

    @Override
    public String toString() {
        return "TMember{" +
                "id=" + id +
                ", loginacct='" + loginacct + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", authstatus=" + authstatus +
                ", usertype=" + usertype +
                ", realname='" + realname + '\'' +
                ", cardnum='" + cardnum + '\'' +
                ", accttype=" + accttype +
                '}';
    }
}
