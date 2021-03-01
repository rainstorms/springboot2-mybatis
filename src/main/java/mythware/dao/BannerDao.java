package mythware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mythware.domain.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BannerDao extends BaseMapper<Banner> {

}

