package cn.perhome.document.dto;

import cn.perhome.document.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormLoginDto {
    private String password;
    private String passport;

}
