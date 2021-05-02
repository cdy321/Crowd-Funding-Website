package cn.cdy.crowd.entity.po;

import java.io.Serializable;

/**
 * (TReturn)实体类
 *
 * @author makejava
 * @since 2021-04-22 17:22:04
 */
public class TReturnPO implements Serializable {
    private static final long serialVersionUID = -11768925278536232L;

    private Integer id;

    private Integer projectid;
    /**
     * 0 - 实物回报， 1 虚拟物品回报
     */
    private Integer type;
    /**
     * 支持金额
     */
    private Integer supportmoney;
    /**
     * 回报内容
     */
    private String content;
    /**
     * 回报产品限额，“0”为不限回报数量
     */
    private Integer count;
    /**
     * 是否设置单笔限购
     */
    private Integer signalpurchase;
    /**
     * 具体限购数量
     */
    private Integer purchase;
    /**
     * 运费，“0”为包邮
     */
    private Integer freight;
    /**
     * 0 - 不开发票， 1 - 开发票
     */
    private Integer invoice;
    /**
     * 项目结束后多少天向支持者发送回报
     */
    private Integer returndate;
    /**
     * 说明图片路径
     */
    private String describPicPath;

    public TReturnPO() {
    }

    @Override
    public String toString() {
        return "TReturnPO{" +
                "id=" + id +
                ", projectid=" + projectid +
                ", type=" + type +
                ", supportmoney=" + supportmoney +
                ", content='" + content + '\'' +
                ", count=" + count +
                ", signalpurchase=" + signalpurchase +
                ", purchase=" + purchase +
                ", freight=" + freight +
                ", invoice=" + invoice +
                ", returndate=" + returndate +
                ", describPicPath='" + describPicPath + '\'' +
                '}';
    }

    public TReturnPO(Integer id, Integer projectid, Integer type, Integer supportmoney, String content, Integer count, Integer signalpurchase, Integer purchase, Integer freight, Integer invoice, Integer returndate, String describPicPath) {
        this.id = id;
        this.projectid = projectid;
        this.type = type;
        this.supportmoney = supportmoney;
        this.content = content;
        this.count = count;
        this.signalpurchase = signalpurchase;
        this.purchase = purchase;
        this.freight = freight;
        this.invoice = invoice;
        this.returndate = returndate;
        this.describPicPath = describPicPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSupportmoney() {
        return supportmoney;
    }

    public void setSupportmoney(Integer supportmoney) {
        this.supportmoney = supportmoney;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSignalpurchase() {
        return signalpurchase;
    }

    public void setSignalpurchase(Integer signalpurchase) {
        this.signalpurchase = signalpurchase;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Integer getReturndate() {
        return returndate;
    }

    public void setReturndate(Integer returndate) {
        this.returndate = returndate;
    }

    public String getDescribPicPath() {
        return describPicPath;
    }

    public void setDescribPicPath(String describPicPath) {
        this.describPicPath = describPicPath;
    }

}
