package cn.cdy.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TMemberVO {
    private String loginacct;
    private String userpswd;
    private String username;
    private String email;
    private String phoneNum;
    private String code;

}
