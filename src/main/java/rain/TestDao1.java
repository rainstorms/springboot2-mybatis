package rain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
//@Repository
@Component
public interface TestDao1 {

    @Select("SELECT sex FROM f_user WHERE user_id = #{id}")
    String selectUser(String id);
}
