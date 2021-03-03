package mythware.dto;

import lombok.Data;
import mythware.domain.Plan;
import mythware.utils.Id;
import org.springframework.beans.BeanUtils;

@Data
public class EditPlanDto {
    private String id;  // 主键
    private String title; // 标题名称
    private String introduction; // 简介
    private String cover; // 封面
    private String content; // 内容
    private Integer category; // 分类 1高等院校 2中小学 3其它
    private Integer state; // 状态 详情见 PlanState

    public Plan toAddPlan() {
        Plan plan = toPlan();

        plan.setId(Id.next());
        return plan;
    }

    public Plan toPlan() {
        Plan plan = new Plan();
        BeanUtils.copyProperties(this, plan);
        return plan;
    }
}
