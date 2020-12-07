package mythware.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import mythware.domain.User;

import java.util.Map;

@Repository
@Mapper
public interface TestDao {

    @Select("SELECT USER_NAME USERNAME FROM F_USER WHERE USER_ID = #{id}")
    User selectUser(String id);

    @Select("SELECT GROUP_X USERNAME FROM TF_ITVAN_GROUP_EXIST WHERE GROUP_ID = #{id}")
    String selectGroupx(String id);

    @Insert("INSERT INTO `TF_ITVAN_GROUP_EXIST` (`GROUP_ID`,`ITV_AN`,`GROUP_X`)" +
            "        VALUES(#{groupid},'1','1')")
    void addTest1(String groupid);

    @Select("SELECT count(*) from $1$")
    long countTable(String table);

    @Insert("INSERT INTO $toTable$ (GROUP_ID,$column$)" +
            " (SELECT #groupId#,$column$ FROM $fromTable$ limit #index#,#rows#);")
    void addImportFromTemp(Map<String, String> dynamics,
                           Map<String, Object> params);
}
