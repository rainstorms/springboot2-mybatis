package mythware.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import mythware.domain.Banner;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BannerVo {
    private String id;
    private String title;
    private String image768;
    private Long startTime;
    private Long endTime;
    private Integer sequenceNo;
    private Integer state;

    public static BannerVo convert(Banner banner) {
        if (null == banner) return null;

        BannerVo bannerVo = new BannerVo();
        BeanUtils.copyProperties(banner, bannerVo);
        return bannerVo;
    }

    public static List<BannerVo> convert(List<Banner> banner) {
        if (CollectionUtils.isEmpty(banner)) return Lists.newArrayList();

        return banner.stream().map(BannerVo::convert).collect(Collectors.toList());
    }
}
