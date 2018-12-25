package rain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rain.domain.TestDataSource;

import javax.sql.DataSource;

@Configuration
public class config {

    @Bean
    public DataSource dataSource(){
        return new TestDataSource();
    }
}
