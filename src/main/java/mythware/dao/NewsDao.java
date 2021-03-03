package mythware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mythware.domain.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NewsDao extends BaseMapper<News> {

}

