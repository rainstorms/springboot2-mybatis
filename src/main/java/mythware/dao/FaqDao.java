package mythware.dao;

import mythware.domain.Faq;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FaqDao {

    String selectWebsiteFaqPrefix = "SELECT  ID id ,QUESTION_CATEGORY questionCategory ,QUESTION question" +
            " ,ANSWER_CATEGORY answerCategory ,ANSWER answer ,STATE state ,CREATE_TIME createTime ,UPDATE_TIME updateTime " +
            "    FROM  website_faq  WHERE STATE = 1 ";

    @Select(selectWebsiteFaqPrefix + "AND ID = #{id} ")
    Faq findFaq(String id);

    @Select(selectWebsiteFaqPrefix + "AND QUESTION_CATEGORY = #{category} ORDER BY createTime")
    List<Faq> queryFaqByCategory(Integer category);

    @SelectProvider(type = MybatisInProvider.class, method = "queryFaqByCondition")
    List<Faq> queryFaqByCondition(Integer category, String searchValue);

    @Insert("INSERT INTO website_faq ( ID        , QUESTION_CATEGORY  , QUESTION    , ANSWER_CATEGORY  , STATE " +
            "                         , ANSWER   , CREATE_TIME        , UPDATE_TIME )" +
            "                  VALUES (#{id}     ,#{questionCategory} ,#{question}  ,#{answerCategory} ,1" +
            "                         ,#{answer} ,NOW()               ,NOW()        )")
    int addFaq(Faq faq);

    @Update("update website_faq " +
            "   SET QUESTION_CATEGORY = #{questionCategory}   " +
            "      ,QUESTION          = #{question}   " +
            "      ,ANSWER_CATEGORY   = #{answerCategory}   " +
            "      ,ANSWER            = #{answer}   " +
            "      ,UPDATE_TIME       = NOW()   " +
            " WHERE STATE             = 1" +
            "   AND ID                = #{id}")
    int updateFaq(Faq faq);

    @Update("update website_faq " +
            "   SET STATE        = #{newState}   " +
            "      ,UPDATE_TIME  = NOW()   " +
            " WHERE ID           = #{id} " +
            "   AND STATE        = #{oldState}")
    int changeFaqState(String id, Integer oldState, Integer newState);
}

