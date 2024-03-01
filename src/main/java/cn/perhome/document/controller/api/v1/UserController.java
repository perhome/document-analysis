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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import cn.perhome.document.security.JwtService;
import java.util.*;

@Tag(name = "认证用户")
@Slf4j
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Operation(summary = "认证注销登陆信息")
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json;charset=utf-8;")
    @ResponseBody
    public ResponseEntity<ResponseResultDto> logout(Authentication authentication) {
        Map<String, Object > ret = new HashMap<>();
        ResponseResultDto responseResultDto;
        var authUser = (AuthUser)authentication.getPrincipal();
        log.info("用户 {}({}) 退出了系统", authUser.getId(), authUser.getUsername());
        responseResultDto = ResponseResultDto.success(ret);
        return new ResponseEntity<>(responseResultDto, HttpStatus.OK);
    }
}