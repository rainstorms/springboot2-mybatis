package mythware.controller;

import com.google.common.collect.Sets;
import mythware.domain.Banner;
import mythware.dto.EditBannerDto;
import mythware.dto.EnableBannerDto;
import mythware.dto.OrderBannerDto;
import mythware.dto.OrderBannersDto;
import mythware.service.BannerService;
import mythware.utils.DateTimes;
import mythware.vo.BannerVo;
import mythware.vo.QueryShowBannerVo;
import mythware.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/BannerController")
public class BannerController {

    @Autowired BannerService service;

    /**
     * 添加和修改banner
     *
     * @param bannerDto
     * @return
     */
    @PostMapping("/editBanner")
    public ResultVo editBanner(@RequestBody EditBannerDto bannerDto) {
        // 校验参数
        ResultVo result = checkEditBanner(bannerDto);
        if (null != result) return result;

        if (null == bannerDto.getId()) { // 没有id 说明是新的banner
            Banner banner = bannerDto.toAddBanner();
            int i = service.addBanner(banner);
            return i > 0 ? ResultVo.ok("添加成功") : ResultVo.fail(501, "添加失败");
        }

        Banner banner = bannerDto.toBanner();
        int i = service.updateBanner(banner);
        return i > 0 ? ResultVo.ok("修改成功") : ResultVo.fail(501, "修改失败");
    }

    private ResultVo checkEditBanner(EditBannerDto bannerDto) {
        int titleLength = StringUtils.trim(bannerDto.getTitle()).length();
        if (titleLength == 0)
            return ResultVo.fail(511, "title不能为空");

        if (titleLength > 30)
            return ResultVo.fail(512, "title不能超过30个字");

        ResultVo resultVo = checkImage(bannerDto.getImage2560());
        if (null != resultVo) return resultVo;

        ResultVo result1440 = checkImage(bannerDto.getImage1440());
        if (null != result1440) return result1440;

        ResultVo result1024 = checkImage(bannerDto.getImage1024());
        if (null != result1024) return result1024;

        ResultVo result768 = checkImage(bannerDto.getImage768());
        if (null != result768) return result768;

        ResultVo startTimeCheckResult = checkTime(bannerDto.getStartTime(), "banner生效的开始时间");
        if (null != startTimeCheckResult) return startTimeCheckResult;

        return checkTime(bannerDto.getEndTime(), "banner生效的结束时间");
    }

    private ResultVo checkImage(String imageBase64) {
        if (imageBase64 == null) return ResultVo.fail(513, "图片不能为空");

        String regex = "^data[:]image[/]([a-z])+;base64,[A-Za-z0-9+/]*";
        if (!imageBase64.matches(regex)) return ResultVo.fail(514, "图片格式不对");

        return null;
    }

    private ResultVo checkTime(String time, String timeName) {
        if (null == time) return null;

        LocalDateTime dateTime;
        try {
            dateTime = DateTimes.parseyyyyMMddHHmmssString(time);
        } catch (Exception e) {
            return ResultVo.fail(515, "传入的" + timeName + "日期格式不正确");
        }

        if (LocalDateTime.now().isAfter(dateTime))
            return ResultVo.fail(516, timeName + "不能在当前时间之前");

        return null;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findBanner/{id}")
    public BannerVo findBanner(@PathVariable("id") String id) {
        Banner banner = service.findBanner(id);
        return BannerVo.convert(banner);
    }

    /**
     * 查询所有的banner
     *
     * @return
     */
    @GetMapping("/queryBanners")
    public List<BannerVo> queryBanners() {
        List<Banner> banners = service.queryBanners();
        return BannerVo.convert(banners);
    }

    /**
     * 查询所有的banner
     *
     * @return
     */
    @GetMapping("/queryShowBanners")
    public List<QueryShowBannerVo> queryShowBanners() {
        List<Banner> banners = service.queryShowBanners();
        return QueryShowBannerVo.convert(banners);
    }

    /**
     * 删除banner
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteBanner/{id}")
    public ResultVo deleteBanner(@PathVariable("id") String id) {
        HashSet<Integer> oldState = Sets.newHashSet(Banner.BannerState.ENABLE.getCode(), Banner.BannerState.DISABLE.getCode());
        int i = service.changeBannerState(id, oldState, Banner.BannerState.DELETE.getCode());
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

    /**
     * 启用禁用banner
     *
     * @param bannerDto
     * @return
     */
    @PostMapping("/enableBanner")
    public ResultVo enableBanner(@RequestBody EnableBannerDto bannerDto) {
        Integer newState = bannerDto.getState();
        Integer oldState = Banner.BannerState.ENABLE.getCode();
        if (Banner.BannerState.ENABLE.getCode() == newState) { // 如果新状态是启用，那老状态就是禁用
            oldState = Banner.BannerState.DISABLE.getCode();
        }

        HashSet<Integer> oldStateSet = Sets.newHashSet(oldState);
        int i = service.changeBannerState(bannerDto.getId(), oldStateSet, newState);
        return i > 0 ? ResultVo.ok("成功") : ResultVo.fail("失败");
    }

    /**
     * banner 排序
     *
     * @param orderBannerDto
     * @return
     */
    @PostMapping("/orderBanners")
    public ResultVo orderBanners(@RequestBody OrderBannersDto orderBannerDto) {
        List<OrderBannerDto> orderBanners = orderBannerDto.getOrderBanners();
        if (CollectionUtils.isEmpty(orderBanners))
            return ResultVo.ok("排序成功");

        int i = service.orderBanners(OrderBannerDto.toOrderBanners(orderBanners));
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

}
