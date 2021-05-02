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
public class AddressVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String receiveName;

    private String phoneNum;

    private String address;

    private Integer memberId;
}
