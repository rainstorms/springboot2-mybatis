package mythware.vo;

import lombok.Data;
import mythware.domain.Plan;
import org.springframework.beans.BeanUtils;

@Data
public class PlanVo {
    private String id;  // 注解
    private String title; // 标题名称
    private String introduction; // 简介
    private String content; // 内容
    private String cover; // 封面
    private Integer category; // 类别 1中小学 2高中 3其它
    private Integer state; // 状态 详情见 PlanState

    public static PlanVo convert(Plan plan) {
        if (null == plan) return null;

        PlanVo planVo = new PlanVo();
        BeanUtils.copyProperties(plan, planVo);

        return planVo;
    }
}
