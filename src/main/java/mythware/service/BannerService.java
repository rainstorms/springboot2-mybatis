package mythware.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import mythware.dao.BannerDao;
import mythware.domain.Banner;
import mythware.utils.DateTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BannerService {

    @Autowired BannerDao dao;

    @Transactional
    public int addBanner(Banner banner) {
        return dao.insert(banner);
    }

    @Transactional
    public int updateBanner(Banner banner) {
        return dao.updateById(banner);
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
        LambdaUpdateWrapper<Banner> wrapper = Wrappers.lambdaUpdate();
        wrapper.set(Banner::getState, newState);
        wrapper.eq(Banner::getState, oldState);
        wrapper.eq(Banner::getId, id);
        return dao.update(null, wrapper);
    }

    @Transactional
    public int deleteBanner(String id) {
        return dao.deleteById(id);
    }

    public Banner findBanner(String id) {
        return dao.selectById(id);
    }

    public List<Banner> queryBanners() {
        LambdaQueryWrapper<Banner> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByAsc(Banner::getState, Banner::getSequenceNo, Banner::getCreateTime);
        return dao.selectList(wrapper);
    }

    public List<Banner> queryShowBanners() {
        LambdaQueryWrapper<Banner> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Banner::getState, 1);
        wrapper.orderByAsc(Banner::getSequenceNo, Banner::getCreateTime);
        List<Banner> banners = dao.selectList(wrapper);
        return banners.stream().filter(banner ->
                {
                    Long startTime = banner.getStartTime();
                    Long endTime = banner.getEndTime();
                    Long now = DateTimes.currentTimeSeconds();
                    return // 1.没有设置生效时间的，一直有效的
                            (null == startTime && null == endTime)
                                    ||// 2.设置生效时间的，开始时间在现在之前，结束时间在现在之后
                                    (null != startTime && startTime <= now
                                            && null != endTime && endTime >= now)
                                    ||// 3.设置生效开始时间，没设置结束时间，比如明天生效，之后一直有效
                                    (null != startTime && startTime <= now
                                            && null == endTime)
                                    ||// 4.设置生效结束时间，没设置开始时间，比如明天失效，之前一直有效
                                    (null == startTime && endTime >= now);
                }
        ).collect(Collectors.toList());
    }

    @Transactional
    public int orderBanners(List<Banner> orderBanners) {
        int i = 0;
        for (Banner orderBanner : orderBanners) {
            int j = dao.updateById(orderBanner);
            i += j;
        }
        return i;
    }
}
