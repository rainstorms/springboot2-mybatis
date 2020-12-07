package mythware.spring;

import mythware.domain.TestDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@MapperScan("mythware")
@Configuration
public class Config implements WebMvcConfigurer {

    // 配置 数据库 源bean
    @Bean
    public DataSource dataSource() {
        return new TestDataSource();
    }

}
