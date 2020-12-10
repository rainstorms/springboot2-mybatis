package mythware.service;

import mythware.dao.BannerDao;
import mythware.domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BannerService {

    @Autowired BannerDao dao;

    @Transactional
    public int addBanner(Banner banner) {
        return dao.addBanner(banner);
    }

    @Transactional
    public int updateBanner(Banner banner) {
        return dao.updateBanner(banner);
    }

    /**
     * 修改 banner state
     *
     * @param id
     * @param oldState 原来的state 状态管理：原状态不对不能修改
     * @param newState 要改成的新的state
     * @return
     */
    @Transactional
    public int changeBannerState(String id, Set<Integer> oldState, Integer newState) {
        return dao.changeBannerState(id, oldState, newState);
    }

    public Banner findBanner(String id) {
        return dao.findBanner(id);
    }

    public List<Banner> queryBanners() {
        return dao.queryBanners();
    }

    public List<Banner> queryShowBanners() {
        List<Banner> banners = dao.queryEnableBanners();
        return banners.stream().filter(banner ->
                {
                    LocalDateTime startTime = banner.getStartTime();
                    LocalDateTime endTime = banner.getEndTime();
                    LocalDateTime now = LocalDateTime.now();
                    return // 1.没有设置生效时间的，一直有效的
                            (null == startTime && null == endTime)
                                    ||// 2.设置生效时间的，开始时间在现在之前，结束时间在现在之后
                                    (null != startTime && startTime.isBefore(now)
                                            && null != endTime && endTime.isAfter(now))
                                    ||// 3.设置生效开始时间，没设置结束时间，比如明天生效，之后一直有效
                                    (null != startTime && startTime.isBefore(now)
                                            && null == endTime)
                                    ||// 4.设置生效结束时间，没设置开始时间，比如明天失效，之前一直有效
                                    (null == startTime && endTime.isAfter(now));
                }
        ).collect(Collectors.toList());
    }

    @Transactional
    public int orderBanners(List<Banner> orderBanners) {
        int i = 0;
        for (Banner orderBanner : orderBanners) {
            int j = dao.updateBannerSequenceNo(orderBanner);
            i += j;
        }
        return i;
    }
}
