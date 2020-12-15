package mythware.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static mythware.dao.FaqDao.selectWebsiteFaqPrefix;

public class MybatisInProvider {

    public String changeBannerState(String id, Set<Integer> oldStates, Integer newState) {
        List<String> oldStatesStr = oldStates.stream().map(String::valueOf).collect(Collectors.toList());
        String collect = String.join(",", oldStatesStr);
        return " UPDATE website_banner" +
                "   SET STATE = " + newState +
                " WHERE ID =  " + id +
                "   AND STATE IN (" + collect +
                "       )";
    }

    public String changeNewsState(String id, Set<Integer> oldStates, Integer newState) {
        List<String> oldStatesStr = oldStates.stream().map(String::valueOf).collect(Collectors.toList());
        String collect = String.join(",", oldStatesStr);
        return " UPDATE website_news" +
                "   SET STATE = " + newState +
                " WHERE ID =  " + id +
                "   AND STATE IN (" + collect +
                "       )";
    }

    public String changePlanState(String id, Set<Integer> oldStates, Integer newState) {
        List<String> oldStatesStr = oldStates.stream().map(String::valueOf).collect(Collectors.toList());
        String collect = String.join(",", oldStatesStr);
        return " UPDATE website_plan" +
                "   SET STATE = " + newState +
                " WHERE ID =  " + id +
                "   AND STATE IN (" + collect +
                "       )";
    }

    public String queryFaqByCondition(Integer category, String searchValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(selectWebsiteFaqPrefix);
        if (0 != category)
            stringBuilder.append(" AND QUESTION_CATEGORY = ").append(category);

        if (StringUtils.isNotBlank(searchValue))
            stringBuilder.append(" AND QUESTION like '%").append(searchValue).append("%'");

        return stringBuilder.append(" ORDER BY createTime").toString();
    }


}
