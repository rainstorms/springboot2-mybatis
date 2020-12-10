package mythware.dao;

import mythware.domain.Plan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface PlanDao {

    String selectWebsitePlanPrefix = "SELECT  ID id ,TITLE title ,INTRODUCTION introduction" +
            " ,CONTENT content ,COVER cover ,CATEGORY category ,STATE state ,CREATE_TIME createTime ,UPDATE_TIME updateTime " +
            "    FROM  website_plan ";

    @Select(selectWebsitePlanPrefix + " WHERE ID = #{id}")
    Plan findPlan(String id);

    @Select(selectWebsitePlanPrefix + " WHERE STATE = #{1} AND CATEGORY = #{1} ORDER BY createTime")
    List<Plan> queryPlanByCondition(Integer state, Integer category);

    @Insert("INSERT INTO website_plan ( ID          , TITLE   , INTRODUCTION    , CONTENT   , STATE " +
            "                         , CATEGORY    , COVER   , CREATE_TIME     , UPDATE_TIME )" +
            "                  VALUES (#{id}        ,#{title} ,#{introduction}  ,#{content} ,#{state}" +
            "                         ,#{category}  ,#{cover} ,NOW()            ,NOW()        )")
    int addPlan(Plan news);

    @Update("update website_plan " +
            "   SET TITLE        = #{title}   " +
            "      ,INTRODUCTION = #{introduction}   " +
            "      ,CONTENT      = #{content}   " +
            "      ,CATEGORY     = #{category}   " +
            "      ,COVER        = #{cover}   " +
            "      ,STATE        = #{state}   " +
            "      ,UPDATE_TIME  = NOW()   " +
            " WHERE ID           = #{id}")
    int updatePlan(Plan news);

    @UpdateProvider(type = MybatisInProvider.class, method = "changePlanState")
    int changePlanState(String id, Set<Integer> oldStates, Integer newState);


}

