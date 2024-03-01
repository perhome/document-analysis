package cn.perhome.snapha.config.constant;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class UserConstant implements Serializable {


    public static final String ROLE_SUPPER ="SUPPER";
    public static final String ROLE_MANAGER ="MANAGER";
    public static final String ROLE_LIVESTOCK ="LIVESTOCK";
    public static final String ROLE_FARMLAND="FARMLAND";

}
