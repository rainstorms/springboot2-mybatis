package mythware.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mythware.dao.RegionDao;
import mythware.domain.PageModel;
import mythware.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionService {

    @Autowired RegionDao dao;

    @Transactional
    public int addRegion(Region region) {
        return dao.insert(region);
    }

    @Transactional
    public int updateRegion(Region region) {
        return dao.updateById(region);
    }

    @Transactional
    public int deleteRegion(String id) {
        return dao.deleteById(id);
    }

    public Region findRegion(String id) {
        return dao.selectById(id);
    }

    public IPage<Region> queryRegions(PageModel pageParams) {
        Page<Region> objectPage = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper<Region> w = Wrappers.lambdaQuery();
        w.orderByAsc(Region::getCreateTime);
        return dao.selectPage(objectPage, w);
    }
}
