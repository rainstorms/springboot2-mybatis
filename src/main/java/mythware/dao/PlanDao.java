package mythware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mythware.domain.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PlanDao extends BaseMapper<Plan> {

}

