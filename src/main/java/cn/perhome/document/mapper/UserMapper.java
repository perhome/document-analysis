package cn.perhome.document.mapper;

import cn.perhome.document.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getByPassport(String passport);
}