package cn.perhome.snapha.config.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;

@Data
@Configuration
@PropertySource("classpath:/constant/sys.properties")
@ConfigurationProperties(prefix = "sys.workspace-abbr")
public class WorkspaceAbbrConstant implements Serializable {
    public String FARMLAND;
    public String LIVESTOCK;
}
