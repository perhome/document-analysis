package cn.perhome.document.entity;

import cn.perhome.document.security.TokenType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Token extends Model<Token> {
    public Integer id;
    public Integer userId;
    public String token;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    public boolean revoked;
    public boolean expired;
    @JsonFormat(pattern = "yy-MM-dd HH:mm", timezone="GMT+8")
    private java.util.Date created;
}
