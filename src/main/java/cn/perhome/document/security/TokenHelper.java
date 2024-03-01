package cn.perhome.document.security;

import cn.perhome.document.entity.Token;
import cn.perhome.document.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.perhome.document.mapper.UserTokenMapper;

import java.util.List;
import java.util.Optional;

@Component
public class TokenHelper {

    private final UserTokenMapper userTokenMapper;

    @Autowired
    public TokenHelper(UserTokenMapper userTokenMapper) {
        this.userTokenMapper = userTokenMapper;
    }

    List<Token> findAllValidTokenByUser(Integer id) {
        return null;
    };
    Optional<UserToken> findByToken(String token) {
        var userToken = userTokenMapper.findByToken(token);
        return Optional.ofNullable(userToken);
    };
}
