package mythware.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import mythware.domain.Banner;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QueryShowBannerVo {
    private String id;  // 注解
    private String title; // 标题名称
    private String image2560; // 2560 px的图
    private int sequenceNo; // 展示序号

    public static List<QueryShowBannerVo> convert(List<Banner> banners) {
        if (CollectionUtils.isEmpty(banners)) return Lists.newArrayList();

        return banners.stream().map(QueryShowBannerVo::convert)
                .sorted(Comparator.comparing(QueryShowBannerVo::getSequenceNo))
                .collect(Collectors.toList());
    }

    public static QueryShowBannerVo convert(Banner banner) {
        QueryShowBannerVo showBannerVo = new QueryShowBannerVo();
        BeanUtils.copyProperties(banner, showBannerVo);

        return showBannerVo;
    }
}
