package mythware.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import mythware.domain.Plan;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlanListVo {
    private String id;  // 主键
    private String title; // 标题名称
    private String cover; // 封面
    private String introduction; // 简介

    public static List<PlanListVo> convert(List<Plan> plan) {
        if (CollectionUtils.isEmpty(plan)) return Lists.newArrayList();

        return plan.stream().map(PlanListVo::convert).collect(Collectors.toList());
    }

    public static PlanListVo convert(Plan plan) {
        PlanListVo planVo = new PlanListVo();
        BeanUtils.copyProperties(plan, planVo);
        return planVo;
    }
}
