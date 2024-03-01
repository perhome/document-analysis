package cn.perhome.document.mapper;

import cn.perhome.document.entity.User;
import cn.perhome.document.model.UserToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTokenMapper {
    int save(UserToken userToken);
    int update(UserToken userToken);
    UserToken findByToken(String token);
    List<UserToken> findAllValidTokenByUser(Integer userId);
    int saveAll(List<UserToken> UserTokens);
}
