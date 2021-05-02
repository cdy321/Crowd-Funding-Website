package cn.cdy.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TMemberLaunchInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 简单介绍
    private String descriptionSimple;
    // 详细介绍
    private String descriptionDetail;
    // 联系电话
    private String phoneNum;
    // 客服电话
    private String serviceNum;
}
