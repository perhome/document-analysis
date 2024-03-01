package cn.perhome.document.controller.api.v1.auth;

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

@Tag(name = "用户认证")
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Value("${document.user.password.suffix-key}")
    private String suffixKey;

    @Operation(summary = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResultDto> register(@RequestBody FormRegisterDto formRegisterDto) {
        ResponseResultDto responseResultDto;
        String passport = formRegisterDto.getPassport();
        String password = formRegisterDto.getPassword();

        User user = userMapper.getByPassport(passport);
        if (user != null) {
            responseResultDto = ResponseResultDto.failed(403,"用户账户已存在", formRegisterDto);
            return new ResponseEntity<>(responseResultDto, HttpStatus.OK);
        }

        String hexPassword = DigestUtils.md5DigestAsHex(
                password.concat(this.suffixKey).getBytes(StandardCharsets.UTF_8));

        user = new User();
        user.setName(passport);
        user.setUsn(passport);
        user.setPassword(hexPassword);
        int result = this.userMapper.insert(user);
        responseResultDto = ResponseResultDto.success(result);

        return new ResponseEntity<>(responseResultDto, HttpStatus.OK);
    }

    @Operation(summary = "登陆认证")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8;")
    @ResponseBody
    public ResponseEntity<ResponseResultDto> login(@RequestBody FormLoginDto formLoginDto) {
        ResponseResultDto responseResultDto;
        String passport = formLoginDto.getPassport();
        String password = formLoginDto.getPassword();

        User user = this.userMapper.getByPassport(passport);
        if (user == null) {
            responseResultDto = ResponseResultDto.failed(403,"用户登陆账户不存在", formLoginDto);
            return new ResponseEntity<>(responseResultDto, HttpStatus.OK);
        }

        String hexPassword = DigestUtils.md5DigestAsHex(
                password.concat(this.suffixKey).getBytes(StandardCharsets.UTF_8));

        if (user.getPassword() == null || !user.getPassword().equals(hexPassword)) {
            responseResultDto = ResponseResultDto.failed(403,"用户密码错误", formLoginDto);
            return new ResponseEntity<>(responseResultDto, HttpStatus.OK);
        }

        var authUser = AuthUser.builder()
                .passport(user.getUsn())
                .id(user.getUid())
                .role(user.getRole())
                .build();
        var jwtToken = jwtService.generateToken(authUser);
        var refreshToken = jwtService.generateRefreshToken(authUser);
        authenticationService.saveUserToken(authUser, jwtToken);
        var authenticationResponse = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken).build();
        responseResultDto = ResponseResultDto.success(authenticationResponse);
        return new ResponseEntity<>(responseResultDto, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    @ResponseBody
    public ResponseEntity<ResponseResultDto> refreshToken(
            HttpServletRequest request
    ) throws IOException {
        var ret = authenticationService.refreshToken(request);
        ResponseResultDto responseResultDto;
        if (ret == null) {
            responseResultDto = ResponseResultDto.failed(403, "jwt refresh token expired");
        }
        else {
            responseResultDto = ResponseResultDto.success(ret);
        }
        return new ResponseEntity<>(responseResultDto, HttpStatus.OK);
    }

}