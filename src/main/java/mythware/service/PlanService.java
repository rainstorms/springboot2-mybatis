package mythware.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mythware.dao.PlanDao;
import mythware.domain.PageModel;
import mythware.domain.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanService {

    @Autowired PlanDao dao;

    @Transactional
    public int addPlan(Plan plan) {
        return dao.insert(plan);
    }

    @Transactional
    public int updatePlan(Plan plan) {
        return dao.updateById(plan);
    }

    public Plan findPlan(String id) {
        return dao.selectById(id);
    }

    public IPage<Plan> queryPlanPageByCondition(Integer state, Integer category, PageModel pageParams) {
        Page<Plan> objectPage = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper<Plan> w = Wrappers.lambdaQuery();
        w.orderByAsc(Plan::getCreateTime);
        w.eq(Plan::getState, state);
        w.eq(Plan::getCategory, category);
        return dao.selectPage(objectPage, w);
    }

    @Transactional
    public int deletePlan(String id) {
        return dao.deleteById(id);
    }
}
