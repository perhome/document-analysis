package cn.perhome.document.entity;


import cn.perhome.document.security.Role;
import cn.perhome.document.security.TokenType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "user")
public class User extends Model<User> {
    @TableId(type= IdType.AUTO)
    private Integer   uid;
    private String    usn;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role   role = Role.USER;;
    private String password;
    private Integer status;
    private Integer deleted;
    @JsonFormat(pattern = "yy-MM-dd HH:mm", timezone="GMT+8")
    private java.util.Date created;
}
