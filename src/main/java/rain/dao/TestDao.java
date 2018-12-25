package rain.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import rain.domain.User;

@Mapper
//@Repository
public interface TestDao {

    @Select("SELECT user_name userName FROM f_user WHERE user_id = #{id}")
    User selectUser(String id);
}
