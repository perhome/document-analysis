package cn.perhome.document.model;


import cn.perhome.document.security.TokenType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserToken implements Serializable {
    private Integer   id;
    private String    token;
    private Integer   userId;
    @Enumerated(EnumType.STRING)
    public  TokenType tokenType = TokenType.BEARER;
    private boolean   revoked;
    private boolean   expired;
    @JsonFormat(pattern = "yy-MM-dd HH:mm", timezone="GMT+8")
    private java.util.Date created;
}

