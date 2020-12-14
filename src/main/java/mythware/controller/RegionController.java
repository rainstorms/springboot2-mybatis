package mythware.controller;

import com.github.pagehelper.PageInfo;
import mythware.domain.Page;
import mythware.domain.Region;
import mythware.dto.EditRegionDto;
import mythware.service.RegionService;
import mythware.vo.QueryRegionsVo;
import mythware.vo.RegionVo;
import mythware.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RegionController")
public class RegionController {

    @Autowired RegionService service;

    private static final int REGION_EMPTY = 511;
    private static final int NAME_EMPTY = 512;
    private static final int MOBILE_EMPTY = 513;
    private static final int WRONG_EMAIL = 514;

    /**
     * 添加和修改 region
     *
     * @param regionDto
     * @return
     */
    @PostMapping("/editRegion")
    public ResultVo editRegion(@RequestBody EditRegionDto regionDto) {
        // 校验参数
        ResultVo result = checkEditRegion(regionDto);
        if (null != result) return result;

        if (null == regionDto.getId()) { // 没有id 说明是新的region
            Region region = regionDto.toAddRegion();
            int i = service.addRegion(region);
            return i > 0 ? ResultVo.ok("添加成功") : ResultVo.fail("添加失败");
        }

        Region region = regionDto.toRegion();
        int i = service.updateRegion(region);
        return i > 0 ? ResultVo.ok("修改成功") : ResultVo.fail("修改失败");
    }

    private ResultVo checkEditRegion(EditRegionDto regionDto) {
        if (StringUtils.isBlank(regionDto.getRegion()))
            return ResultVo.fail(REGION_EMPTY, "region 不能为空");

        if (StringUtils.isBlank(regionDto.getName()))
            return ResultVo.fail(NAME_EMPTY, "name 不能为空");

        if (StringUtils.isBlank(regionDto.getMobile()))
            return ResultVo.fail(MOBILE_EMPTY, "mobile 不能为空");

        if (!regionDto.getEmail().endsWith("@mythware.com"))
            return ResultVo.fail(WRONG_EMAIL, "email 格式不对");

        return null;
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/findRegion/{id}")
    public RegionVo findRegion(@PathVariable("id") String id) {
        Region region = service.findRegion(id);
        return RegionVo.convert(region);
    }

    /**
     * 查询所有有效 region
     *
     * @return
     */
    @PostMapping("/queryRegions")
    public QueryRegionsVo queryRegions(@RequestBody Page page) {
        PageInfo<Region> regions = service.queryRegions(page);
        return QueryRegionsVo.convert(regions);
    }

    /**
     * 删除region
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteRegion/{id}")
    public ResultVo deleteRegion(@PathVariable("id") String id) {
        int i = service.deleteRegion(id);
        return i > 0 ? ResultVo.ok("删除成功") : ResultVo.fail("删除失败");
    }

}
