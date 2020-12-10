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
    private int category; // 分类 1高等院校 2中小学 3其它
    private int state; // 状态 详情见 PlanState

    public Plan toAddPlan() {
        Plan banner = toPlan();

        banner.setId(Id.next());
        return banner;
    }

    public Plan toPlan() {
        Plan banner = new Plan();
        BeanUtils.copyProperties(this, banner);
        return banner;
    }
}
