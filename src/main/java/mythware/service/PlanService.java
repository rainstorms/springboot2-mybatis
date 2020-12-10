package mythware.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mythware.dao.PlanDao;
import mythware.domain.Page;
import mythware.domain.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class PlanService {

    @Autowired PlanDao dao;

    @Transactional
    public int addPlan(Plan plan) {
        return dao.addPlan(plan);
    }

    @Transactional
    public int updatePlan(Plan plan) {
        return dao.updatePlan(plan);
    }

    /**
     * 修改 plan state
     *
     * @param id
     * @param oldState 原来的state 状态管理：原状态不对不能修改
     * @param newState 要改成的新的state
     * @return
     */
    @Transactional
    public int changePlanState(String id, Set<Integer> oldState, Integer newState) {
        return dao.changePlanState(id, oldState, newState);
    }

    public Plan findPlan(String id) {
        return dao.findPlan(id);
    }

    public PageInfo<Plan> queryPlanByCondition(Integer state, Integer category, Page page) {
        PageHelper.startPage(page);
        List<Plan> plans = dao.queryPlanByCondition(state, category);
        return new PageInfo<>(plans);
    }
}
