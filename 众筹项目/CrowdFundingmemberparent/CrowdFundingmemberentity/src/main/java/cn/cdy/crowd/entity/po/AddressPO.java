package cn.cdy.crowd.entity.po;

import java.io.Serializable;

/**
 * (TAddress)实体类
 *
 * @author makejava
 * @since 2021-04-25 21:11:43
 */
public class AddressPO implements Serializable {
    private static final long serialVersionUID = -83270455835677991L;

    private Integer id;

    private String receiveName;

    private String phoneNum;

    private String address;

    private Integer memberId;

    public AddressPO() {
    }

    public AddressPO(Integer id, String receiveName, String phoneNum, String address, Integer memberId) {
        this.id = id;
        this.receiveName = receiveName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.memberId = memberId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "AddressPO{" +
                "id=" + id +
                ", receiveName='" + receiveName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}
