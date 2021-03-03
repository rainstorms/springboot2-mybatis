package mythware.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mythware.domain.Banner;
import mythware.dto.*;
import mythware.service.BannerService;
import mythware.utils.DateTimes;
import mythware.vo.BannerVo;
import mythware.vo.QueryShowBannerVo;
import mythware.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Api(tags = {"首页 Banner"})
@RestController
@RequestMapping("/BannerController")
public class BannerController {

    @Autowired BannerService service;

    private static final int TITLE_EMPTY = 511;
    private static final int TITLE_SIZE_OVER_30 = 512;
    private static final int IMAGE_EMPTY = 513;
    private static final int WRONG_IMAGE_FORMAT = 514;
    private static final int WRONG_TIME = 516;

    @ApiOperation("添加和修改")
    @ApiOperationSupport(order = 1)
    @PostMapping("/editBanner")
    public ResultVo editBanner(@RequestBody EditBannerDto bannerDto) {
        // 校验参数
        ResultVo result = checkEditBanner(bannerDto);
        if (null != result) return result;

        if (null == bannerDto.getId()) { // 没有id 说明是新的banner
            Banner banner = bannerDto.toAddBanner();
            int i = service.addBanner(banner);
            return i > 0 ? ResultVo.ok("添加成功") : ResultVo.fail("添加失败");
        }

        Banner banner = bannerDto.toBanner();
        int i = service.updateBanner(banner);
        return i > 0 ? ResultVo.ok("修改成功") : ResultVo.fail("修改失败");
    }

    private ResultVo checkEditBanner(EditBannerDto bannerDto) {
        int titleLength = StringUtils.trim(bannerDto.getTitle()).length();
        if (titleLength == 0)
            return ResultVo.fail(TITLE_EMPTY, "title不能为空");

        if (titleLength > 30)
            return ResultVo.fail(TITLE_SIZE_OVER_30, "title不能超过30个字");

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
        if (imageBase64 == null) return ResultVo.fail(IMAGE_EMPTY, "图片不能为空");

        String regex = "^data[:]image[/]([a-z])+;base64,[A-Za-z0-9+/]*";
        if (!imageBase64.matches(regex)) return ResultVo.fail(WRONG_IMAGE_FORMAT, "图片格式不对");

        return null;
    }

    private ResultVo checkTime(Long time, String timeName) {
        if (null == time) return null;

        Long now = DateTimes.currentTimeSeconds();
        if (now >= time)
            return ResultVo.fail(WRONG_TIME, timeName + "不能在当前时间之前");

        return null;
    }

    @ApiOperation("详情")
    @ApiOperationSupport(order = 2)
    @GetMapping("/findBanner/{id}")
    public BannerVo findBanner(@PathVariable("id") String id) {
        Banner banner = service.findBanner(id);
        return BannerVo.convert(banner);
    }

    @ApiOperation("查询 - 所有")
    @ApiOperationSupport(order = 3)
    @GetMapping("/queryBanners")
    public List<BannerVo> queryBanners() {
        List<Banner> banners = service.queryBanners();
        return BannerVo.convert(banners);
    }

    @ApiOperation("查询 - 用于展示的")
    @ApiOperationSupport(order = 4)
    @GetMapping("/queryShowBanners")
    public List<QueryShowBannerVo> queryShowBanners() {
        List<Banner> banners = service.queryShowBanners();
        return QueryShowBannerVo.convert(banners);
    }

    @ApiOperation("删除")
    @ApiOperationSupport(order = 5)
    @PostMapping("/deleteBanner")
    public ResultVo deleteBanner(@RequestBody IdDto idDto) {
        int i = service.deleteBanner(idDto.getId());
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

    @ApiOperation("启用禁用")
    @ApiOperationSupport(order = 6)
    @PostMapping("/enableBanner")
    public ResultVo enableBanner(@RequestBody EnableBannerDto bannerDto) {
        Integer newState = bannerDto.getState();
        Integer oldState = Banner.BannerState.ENABLE.getCode();
        if (Banner.BannerState.ENABLE.getCode().equals(newState)) { // 如果新状态是启用，那老状态就是禁用
            oldState = Banner.BannerState.DISABLE.getCode();
        }

        HashSet<Integer> oldStateSet = Sets.newHashSet(oldState);
        int i = service.changeBannerState(bannerDto.getId(), oldStateSet, newState);
        return i > 0 ? ResultVo.ok("成功") : ResultVo.fail("失败");
    }

    @ApiOperation("排序")
    @ApiOperationSupport(order = 7)
    @PostMapping("/orderBanners")
    public ResultVo orderBanners(@RequestBody OrderBannersDto orderBannerDto) {
        List<OrderBannerDto> orderBanners = orderBannerDto.getOrderBanners();
        if (CollectionUtils.isEmpty(orderBanners))
            return ResultVo.ok("排序成功");

        int i = service.orderBanners(OrderBannerDto.toOrderBanners(orderBanners));
        return i > 0 ? ResultVo.ok("排序成功") : ResultVo.fail("排序失败");
    }

}
