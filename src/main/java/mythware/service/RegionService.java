package mythware.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mythware.dao.RegionDao;
import mythware.domain.Page;
import mythware.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegionService {

    @Autowired RegionDao dao;

    @Transactional
    public int addRegion(Region region) {
        return dao.addRegion(region);
    }

    @Transactional
    public int updateRegion(Region region) {
        return dao.updateRegion(region);
    }

    @Transactional
    public int deleteRegion(String id) {
        return dao.deleteRegion(id);
    }

    public Region findRegion(String id) {
        return dao.findRegion(id);
    }

    public PageInfo<Region> queryRegions(Page page) {
        PageHelper.startPage(page);
        List<Region> regions = dao.queryRegions();
        return new PageInfo<>(regions);
    }
}
