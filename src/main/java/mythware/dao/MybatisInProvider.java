package mythware.dao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


}
