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

import com.alibaba.druid.pool.DruidDataSource;

import plus.springframework.data.druid.config.DruidDataSourceProvider;
import plus.springframework.data.druid.wrapper.DruidDataSourceFilterWrapper;

@Configuration
@ConditionalOnProperty(prefix = "spring.slave.datasource", name = "enabled", havingValue = "true", matchIfMissing = false)
public class SlaveDruidDataSourceConfig {

	@Bean(value = "slaveDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.slave.datasource")
	public DataSourceProperties dataSourceProperties() {
		System.out.println("slave: DataSourceProperties");
		return new DataSourceProperties();
	}

	@ConditionalOnBean({ DruidDataSourceProvider.class })
	@Bean(value = "slaveDataSource")
	public DataSource slaveDataSource(
			@Qualifier(value = "slaveDataSourceProperties") DataSourceProperties druidProperties,
			DruidDataSourceProvider druidDataSourceConfig) {

		DruidDataSource druidDataSource = new DruidDataSourceFilterWrapper(druidProperties);
		System.out.println("Slave DruidDataSource: " + druidDataSource);
		druidDataSourceConfig.dataSource(druidDataSource);

		try {
			druidDataSource.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return druidDataSource;
	}

}
