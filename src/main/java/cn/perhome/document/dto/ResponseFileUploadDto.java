package cn.perhome.document.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseFileUploadDto {
    private String urlHost;
    private String urlHttp;
}