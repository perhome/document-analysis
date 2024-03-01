package cn.perhome.document.controller.api.v1;


import cn.perhome.document.dto.FormLoginDto;
import cn.perhome.document.dto.FormRegisterDto;
import cn.perhome.document.dto.ResponseResultDto;

import cn.perhome.document.entity.User;
import cn.perhome.document.mapper.UserMapper;
import cn.perhome.document.security.AuthUser;
import cn.perhome.document.security.AuthenticationResponse;
import cn.perhome.document.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import cn.perhome.document.security.JwtService;
import java.util.*;

@Tag(name = "文件上传")
@Slf4j
@RestController
@RequestMapping("/api/v1/upload")
@RequiredArgsConstructor
public class UploadController {


    @Operation(summary = "默认上传方式")
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResultDto> upload() {
        var data = new HashMap<String, Object>();
        data.put("info", "这是一条认证过的信息");
        return new ResponseEntity<>(ResponseResultDto.success(data), HttpStatus.OK);
    }

    @Operation(summary = "已上传文档")
    @RequestMapping(value = "/processed", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResultDto> processed() {
        var data = new HashMap<String, Object>();
        data.put("info", "这是一条认证过的信息");
        return new ResponseEntity<>(ResponseResultDto.success(data), HttpStatus.OK);
    }
}
