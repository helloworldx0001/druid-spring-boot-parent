package starter.springframework.data.druid.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

import plus.springframework.data.druid.config.DruidDataSourceProvider;
import plus.springframework.data.druid.wrapper.DruidDataSourceFilterWrapper;

@Configuration
@ConditionalOnProperty(prefix = "spring.primary.datasource", name = "enabled", havingValue = "true", matchIfMissing = false)
public class PrimaryDataSourceConnConfig {

	@Bean(value = "primaryDataSourceProperties")
	@Primary
	@ConfigurationProperties(prefix = "spring.primary.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@ConditionalOnBean({ DruidDataSourceProvider.class })
	@Bean(value = "dataSource")
	public DataSource dataSource(@Qualifier(value = "primaryDataSourceProperties") DataSourceProperties druidProperties,
			DruidDataSourceProvider druidDataSourceConfig) {

		DruidDataSource druidDataSource = new DruidDataSourceFilterWrapper(druidProperties);
		druidDataSourceConfig.dataSource(druidDataSource);

		try {
			druidDataSource.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return druidDataSource;
	}

}
