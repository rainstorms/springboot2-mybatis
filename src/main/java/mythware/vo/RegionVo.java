package mythware.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import mythware.domain.Region;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RegionVo {
    private String id;  // 主键
    private String region; // 区域
    private String name; // 姓名
    private String mobile; // 移动电话
    private String email; // 邮箱
    private String position; // 职位

    public static RegionVo convert(Region region) {
        if (null == region) return null;

        RegionVo regionVo = new RegionVo();
        BeanUtils.copyProperties(region, regionVo);

        return regionVo;
    }

    public static List<RegionVo> convert(List<Region> region) {
        if (CollectionUtils.isEmpty(region)) return Lists.newArrayList();

        return region.stream().map(RegionVo::convert).collect(Collectors.toList());
    }
}
