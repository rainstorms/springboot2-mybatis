package mythware.dao;

import mythware.domain.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface NewsDao {

    String selectWebsiteNewsPrefix = "SELECT  ID id ,TITLE title ,INTRODUCTION introduction" +
            " ,CONTENT content ,SHOW_TIME showTime ,STATE state ,CREATE_TIME createTime ,UPDATE_TIME updateTime " +
            "    FROM  website_news ";

    @Select(selectWebsiteNewsPrefix + " WHERE STATE != 0 and ID = #{id}")
    News findNews(String id);

    @Select(selectWebsiteNewsPrefix + " WHERE STATE = #{state} ORDER BY createTime")
    List<News> queryNewsesByState(Integer state);

    @Insert("INSERT INTO website_news ( ID          , TITLE   , INTRODUCTION    , CONTENT " +
            "                         , SHOW_TIME   , STATE   , CREATE_TIME     , UPDATE_TIME )" +
            "                  VALUES (#{id}        ,#{title} ,#{introduction}  ,#{content}" +
            "                         ,#{showTime}  ,#{state} ,NOW()            ,NOW()        )")
    int addNews(News news);

    @Update("update website_news " +
            "   SET TITLE        = #{title}   " +
            "      ,INTRODUCTION = #{introduction}   " +
            "      ,CONTENT      = #{content}   " +
            "      ,SHOW_TIME    = #{showTime}   " +
            "      ,STATE        = #{state}   " +
            "      ,UPDATE_TIME  = NOW()   " +
            " WHERE STATE       != 0" +
            "   AND ID           = #{id}")
    int updateNews(News news);

    @UpdateProvider(type = MybatisInProvider.class, method = "changeNewsState")
    int changeNewsState(String id, Set<Integer> oldStates, Integer newState);


}

