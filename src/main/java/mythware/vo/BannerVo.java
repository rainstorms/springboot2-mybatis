package mythware.vo;

import com.google.common.collect.Lists;
import lombok.Data;
import mythware.domain.Banner;
import mythware.utils.DateTimes;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BannerVo {
    private String id;
    private String title;
    private String image768;
    private String startTime;
    private String endTime;
    private int sequenceNo;
    private int state;

    public static BannerVo convert(Banner banner) {
        if (null == banner) return null;

        BannerVo bannerVo = new BannerVo();
        BeanUtils.copyProperties(banner, bannerVo);

        LocalDateTime startTime = banner.getStartTime();
        if (null != startTime)
            bannerVo.setStartTime(DateTimes.parseDateTime2yyyyMMddHHmmss(startTime));

        LocalDateTime endTime = banner.getEndTime();
        if (null != endTime)
            bannerVo.setEndTime(DateTimes.parseDateTime2yyyyMMddHHmmss(endTime));

        return bannerVo;
    }

    public static List<BannerVo> convert(List<Banner> banner) {
        if (CollectionUtils.isEmpty(banner)) return Lists.newArrayList();

        return banner.stream().map(BannerVo::convert).collect(Collectors.toList());
    }
}
