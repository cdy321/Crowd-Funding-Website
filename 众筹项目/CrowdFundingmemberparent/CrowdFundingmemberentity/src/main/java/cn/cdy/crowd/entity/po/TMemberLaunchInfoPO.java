package cn.cdy.crowd.entity.po;

import java.io.Serializable;

/**
 * (TMemberLaunchInfo)实体类
 *
 * @author makejava
 * @since 2021-04-22 17:21:21
 */
public class TMemberLaunchInfoPO implements Serializable {
    private static final long serialVersionUID = -13213128634846604L;

    private Integer id;
    /**
     * 会员 id
     */
    private Integer memberid;
    /**
     * 简单介绍
     */
    private String descriptionSimple;
    /**
     * 详细介绍
     */
    private String descriptionDetail;
    /**
     * 联系电话
     */
    private String phoneNum;
    /**
     * 客服电话
     */
    private String serviceNum;
    public TMemberLaunchInfoPO() {
    }

    @Override
    public String toString() {
        return "TMemberLaunchInfoPO{" +
                "id=" + id +
                ", memberid=" + memberid +
                ", descriptionSimple='" + descriptionSimple + '\'' +
                ", descriptionDetail='" + descriptionDetail + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", serviceNum='" + serviceNum + '\'' +
                '}';
    }

    public TMemberLaunchInfoPO(Integer id, Integer memberid, String descriptionSimple, String descriptionDetail, String phoneNum, String serviceNum) {
        this.id = id;
        this.memberid = memberid;
        this.descriptionSimple = descriptionSimple;
        this.descriptionDetail = descriptionDetail;
        this.phoneNum = phoneNum;
        this.serviceNum = serviceNum;
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

    public String getDescriptionSimple() {
        return descriptionSimple;
    }

    public void setDescriptionSimple(String descriptionSimple) {
        this.descriptionSimple = descriptionSimple;
    }

    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }


}
