package mythware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mythware.domain.Region;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegionDao extends BaseMapper<Region> {

    String selectWebsiteRegionPrefix = "SELECT  ID id ,REGION region ,NAME name" +
            " ,MOBILE mobile ,EMAIL email ,POSITION position ,STATE state ,CREATE_TIME createTime ,UPDATE_TIME updateTime " +
            "    FROM  website_region ";

    @Select(selectWebsiteRegionPrefix + " WHERE STATE = 1 AND ID = #{id}")
    Region findRegion(String id);

    @Select(selectWebsiteRegionPrefix + " WHERE STATE = 1 ORDER BY createTime")
    List<Region> queryRegions();

    @Insert("INSERT INTO website_region ( ID      , REGION     , NAME        , MOBILE   , STATE " +
            "                           , EMAIL   , POSITION   , CREATE_TIME , UPDATE_TIME )" +
            "                    VALUES (#{id}    ,#{region}   ,#{name}      ,#{mobile} , 1" +
            "                           ,#{email} ,#{position} ,NOW()        ,NOW()        )")
    int addRegion(Region region);

    @Update("update website_region " +
            "   SET REGION      = #{region}   " +
            "      ,NAME        = #{name}   " +
            "      ,MOBILE      = #{mobile}   " +
            "      ,EMAIL       = #{email}   " +
            "      ,POSITION    = #{position}   " +
            "      ,UPDATE_TIME = NOW()   " +
            " WHERE STATE       = 1 " +
            "   AND ID          = #{id}")
    int updateRegion(Region region);

    @Update("update website_region " +
            "   SET STATE       = 0   " +
            "      ,UPDATE_TIME = NOW()   " +
            " WHERE STATE       = 1 " +
            "   AND ID          = #{id}")
    int deleteRegion(String id);


}

