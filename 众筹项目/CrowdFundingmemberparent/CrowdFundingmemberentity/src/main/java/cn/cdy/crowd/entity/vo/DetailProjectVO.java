package cn.cdy.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailProjectVO {
    private Integer projectId;
    private String projectName;
    private String projectDesc;
    private Integer followerCount;
    private Integer status;
    private String statusText;
    private Integer money;
    private Integer day;
    private Integer supportMoney;
    private Integer percentage;
    private String deployDate;
    private Integer lastDay;
    private Integer supporterCount;
    private String headerPicturePath;
    private List<String> detailPicturePathList;
    private List<DetailReturnVO> detailReturnVOList;
}
