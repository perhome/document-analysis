package cn.perhome.document.mapper;

import cn.perhome.document.entity.Token;
import cn.perhome.document.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper extends BaseMapper<Token> {
}
