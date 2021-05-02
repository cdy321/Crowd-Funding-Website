package cn.cdy.crowd.entity.po;

import java.io.Serializable;

/**
 * (TProject)实体类
 *
 * @author makejava
 * @since 2021-04-22 17:19:31
 */
public class TProjectPO implements Serializable {
    private static final long serialVersionUID = 134887401212233148L;

    private Integer id;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目描述
     */
    private String projectDescription;
    /**
     * 筹集金额
     */
    private Integer money;
    /**
     * 筹集天数
     */
    private Integer day;
    /**
     * 0-即将开始，1-众筹中，2-众筹成功，3-众筹失败
     */
    private Integer status;
    /**
     * 项目发起时间
     */
    private String deploydate;
    /**
     * 已筹集到的金额
     */
    private Long supportmoney;
    /**
     * 支持人数
     */
    private Integer supporter;
    /**
     * 百分比完成度
     */
    private Integer completion;
    /**
     * 发起人的会员 id
     */
    private Integer memberid;
    /**
     * 项目创建时间
     */
    private String createdate;
    /**
     * 关注人数
     */
    private Integer follower;
    /**
     * 头图路径
     */
    private String headerPicturePath;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeploydate() {
        return deploydate;
    }

    public void setDeploydate(String deploydate) {
        this.deploydate = deploydate;
    }

    public Long getSupportmoney() {
        return supportmoney;
    }

    public void setSupportmoney(Long supportmoney) {
        this.supportmoney = supportmoney;
    }

    public Integer getSupporter() {
        return supporter;
    }

    public void setSupporter(Integer supporter) {
        this.supporter = supporter;
    }

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public String getHeaderPicturePath() {
        return headerPicturePath;
    }

    public void setHeaderPicturePath(String headerPicturePath) {
        this.headerPicturePath = headerPicturePath;
    }

}
