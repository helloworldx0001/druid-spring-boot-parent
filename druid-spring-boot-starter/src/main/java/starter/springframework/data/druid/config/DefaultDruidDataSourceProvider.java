package starter.springframework.data.druid.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@ConditionalOnProperty(prefix = "spring.datasource.druid", name = "initiate-mode", havingValue = "simple", matchIfMissing = true)

public class DefaultDruidDataSourceProvider {

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    @Primary
    @ConditionalOnMissingBean(value = DataSource.class)
    public DataSource druidDataSource() {
   		return new DruidDataSource();
    }

}
