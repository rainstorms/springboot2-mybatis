package mythware.spring;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import mythware.utils.DateTimes;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class AutoFillHandler implements MetaObjectHandler {

    private long timestamp() {
        return DateTimes.currentTimeSeconds();
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        Long timestamp = this.timestamp();
        this.setFieldValByName("createTime", timestamp, metaObject);
        this.setFieldValByName("updateTime", timestamp, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long timestamp = this.timestamp();
        this.setFieldValByName("updateTime", timestamp, metaObject);
    }
}
