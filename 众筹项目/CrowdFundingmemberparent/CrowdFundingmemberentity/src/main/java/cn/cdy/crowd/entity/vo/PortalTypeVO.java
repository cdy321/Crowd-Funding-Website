package cn.cdy.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PortalTypeVO {
    private Integer id;
    private String name;
    private String remark;
    private List<PortalProjectVO> portalProjectVOList;

}
