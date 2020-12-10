package mythware.dao;

import mythware.domain.Banner;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface BannerDao {

    String selectWebsiteBannerPrefix = "SELECT ID id , TITLE title" +
            ", IMAGE2560 image2560 , IMAGE1440 image1440   ,IMAGE1024 image1024 ,IMAGE768 image768" +
            ", START_TIME startTime, END_TIME endTime      , SEQUENCE_NO sequenceNo" +
            ", STATE state         , CREATE_TIME createTime, UPDATE_TIME updateTime" +
            "    FROM  website_banner ";

    @Select(selectWebsiteBannerPrefix + " WHERE ID = #{id}")
    Banner findBanner(String id);

    @Select(selectWebsiteBannerPrefix + " WHERE STATE IN (1,2) ORDER BY state ,sequenceNo ,createTime")
    List<Banner> queryBanners();

    @Select(selectWebsiteBannerPrefix + " WHERE STATE = 1 ORDER BY sequenceNo ,createTime")
    List<Banner> queryEnableBanners();

    @Insert("INSERT INTO website_banner ( ID          , TITLE    , IMAGE2560   , IMAGE1440   , IMAGE1024   , IMAGE768       " +
            "                           , START_TIME  , END_TIME , SEQUENCE_NO , STATE       , CREATE_TIME , UPDATE_TIME )" +
            "                    VALUES (#{id}        ,#{title}  ,#{image2560} ,#{image1440} ,#{image1024} ,#{image768}    " +
            "                           ,#{startTime} ,#{endTime},#{sequenceNo},#{state}     ,NOW()        ,NOW()        )")
    int addBanner(Banner banner);

    @Update("update website_banner " +
            "   SET TITLE       = #{title}   " +
            "      ,IMAGE2560   = #{image2560}   " +
            "      ,IMAGE1440   = #{image1440}   " +
            "      ,IMAGE1024   = #{image1024}   " +
            "      ,IMAGE768    = #{image768}   " +
            "      ,START_TIME  = #{startTime}   " +
            "      ,END_TIME    = #{endTime}   " +
            "      ,UPDATE_TIME = NOW()   " +
            " WHERE ID          = #{id}")
    int updateBanner(Banner banner);

    @Update("update website_banner " +
            "   SET SEQUENCE_NO    = #{sequenceNo}   " +
            "      ,UPDATE_TIME    = NOW()   " +
            " WHERE ID             = #{id}")
    int updateBannerSequenceNo(Banner banner);

    @UpdateProvider(type = MybatisInProvider.class, method = "changeBannerState")
    int changeBannerState(String id, Set<Integer> oldStates, Integer newState);

}

