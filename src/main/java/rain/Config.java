package rain;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rain.domain.TestDataSource;

import javax.sql.DataSource;

@MapperScan("rain")
@Configuration
public class Config {

    @Bean
    public DataSource dataSource(){
        return new TestDataSource();
    }

}
