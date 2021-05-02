package cn.cdy.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TMemberLoginVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String email;
}
