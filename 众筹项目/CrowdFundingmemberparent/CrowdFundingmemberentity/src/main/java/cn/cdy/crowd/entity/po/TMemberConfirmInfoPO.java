package cn.cdy.crowd.entity.po;

import java.io.Serializable;

/**
 * (TMemberConfirmInfo)实体类
 *
 * @author makejava
 * @since 2021-04-22 17:22:47
 */
public class TMemberConfirmInfoPO implements Serializable {
    private static final long serialVersionUID = -66828356027576745L;


    private Integer id;
    /**
     * 会员 id
     */
    private Integer memberid;
    /**
     * 易付宝企业账号
     */
    private String paynum;
    /**
     * 法人身份证号
     */
    private String cardnum;
    public TMemberConfirmInfoPO(Integer id, Integer memberid, String paynum, String cardnum) {
        this.id = id;
        this.memberid = memberid;
        this.paynum = paynum;
        this.cardnum = cardnum;
    }

    public TMemberConfirmInfoPO() {
    }

    @Override
    public String toString() {
        return "TMemberConfirmInfoPO{" +
                "id=" + id +
                ", memberid=" + memberid +
                ", paynum='" + paynum + '\'' +
                ", cardnum='" + cardnum + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getPaynum() {
        return paynum;
    }

    public void setPaynum(String paynum) {
        this.paynum = paynum;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

}
