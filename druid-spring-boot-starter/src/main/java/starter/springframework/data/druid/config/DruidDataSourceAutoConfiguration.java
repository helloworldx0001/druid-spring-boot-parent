package starter.springframework.data.druid.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.druid.pool.DruidDataSource;

import starter.springframework.data.druid.properties.DruidStatProperties;
import starter.springframework.data.druid.stat.DruidFilterConfiguration;
import starter.springframework.data.druid.stat.DruidSpringAopConfiguration;
import starter.springframework.data.druid.stat.DruidStatViewServletConfiguration;
import starter.springframework.data.druid.stat.DruidWebStatFilterConfiguration;

@Configuration
@ConditionalOnClass({ DataSource.class, DruidDataSource.class })

@EnableConfigurationProperties({ DruidStatProperties.class })

@Import({ DruidSpringAopConfiguration.class, DruidStatViewServletConfiguration.class,
		DruidWebStatFilterConfiguration.class, DruidFilterConfiguration.class, DruidDataSourceConfig.class,
		DefaultDruidDataSourceConfig.class

})

public class DruidDataSourceAutoConfiguration {

}
