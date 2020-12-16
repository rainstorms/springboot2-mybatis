package mythware.controller;

import com.github.pagehelper.PageInfo;
import mythware.domain.Plan;
import mythware.dto.EditPlanDto;
import mythware.dto.QueryPlansByConditionDto;
import mythware.dto.QueryShowPlansDto;
import mythware.service.PlanService;
import mythware.vo.PlanVo;
import mythware.vo.QueryPlansByConditionVo;
import mythware.vo.QueryShowPlansVo;
import mythware.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/PlanController")
public class PlanController {

    @Autowired PlanService service;

    private static final int TITLE_EMPTY = 511;
    private static final int TITLE_SIZE_OVER_30 = 512;
    private static final int INTRODUCTION_EMPTY = 513;
    private static final int CONTENT_EMPTY = 514;
    private static final int WRONG_STATE = 515;

    /**
     * 添加和修改plan
     *
     * @param planDto
     * @return
     */
    @PostMapping("/editPlan")
    public ResultVo editPlan(@RequestBody EditPlanDto planDto) {
        // 校验参数
        ResultVo result = checkEditPlan(planDto);
        if (null != result) return result;

        if (null == planDto.getId()) { // 没有id 说明是新的plan
            Plan plan = planDto.toAddPlan();
            int i = service.addPlan(plan);
            return i > 0 ? ResultVo.ok("添加成功") : ResultVo.fail("添加失败");
        }

        Plan plan = planDto.toPlan();
        int i = service.updatePlan(plan);
        return i > 0 ? ResultVo.ok("修改成功") : ResultVo.fail("修改失败");
    }

    private ResultVo checkEditPlan(EditPlanDto planDto) {
        if (StringUtils.isBlank(planDto.getIntroduction()))
            return ResultVo.fail(INTRODUCTION_EMPTY, "introduction不能为空");

        int titleLength = StringUtils.trim(planDto.getTitle()).length();
        if (titleLength == 0)
            return ResultVo.fail(TITLE_EMPTY, "title不能为空");

        if (titleLength > 30)
            return ResultVo.fail(TITLE_SIZE_OVER_30, "title不能超过30个字");

        if (StringUtils.isBlank(planDto.getContent()))
            return ResultVo.fail(CONTENT_EMPTY, "content不能为空");

        int state = planDto.getState();
        if (!Plan.getValidStates().contains(state))
            return ResultVo.fail(WRONG_STATE, "state 值不正确");

        return null;
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findPlan/{id}")
    public PlanVo findPlan(@PathVariable("id") String id) {
        Plan plan = service.findPlan(id);
        return PlanVo.convert(plan);
    }

    /**
     * 按状态和分类 条件查询 plan
     *
     * @return
     */
    @PostMapping("/queryPlansByCondition")
    public QueryPlansByConditionVo queryPlanByCondition(@RequestBody QueryPlansByConditionDto dto) {
        Integer state = dto.getState();
        HashSet<Integer> validStates = Plan.getValidStates();
        if (!validStates.contains(state))
            throw new RuntimeException("state 值不正确");

        PageInfo<Plan> plans = service.queryPlanPageByCondition(state, dto.getCategory(), dto.getPage());
        return QueryPlansByConditionVo.convert(plans);
    }

    /**
     * 首页 展示查询接口
     *
     * @param queryShowPlansDto
     * @return
     */
    @PostMapping("/queryShowPlans")
    public QueryShowPlansVo queryShowPlans(@RequestBody QueryShowPlansDto queryShowPlansDto) {
        PageInfo<Plan> showPlans = service.queryPlanPageByCondition(Plan.PlanState.PUBLISHED.getCode()
                , queryShowPlansDto.getCategory(), queryShowPlansDto.getPage());
        return QueryShowPlansVo.convert(showPlans);
    }

    /**
     * 删除plan
     *
     * @param id
     * @return
     */
    @GetMapping("/deletePlan/{id}")
    public ResultVo deletePlan(@PathVariable("id") String id) {
        HashSet<Integer> validStates = Plan.getValidStates();
        int i = service.changePlanState(id, validStates, Plan.PlanState.DELETE.getCode());
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

}
