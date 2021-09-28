package starter.springframework.data.druid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import starter.springframework.data.druid.properties.DruidDataSourceProperties;
import starter.springframework.data.druid.properties.DruidStatProperties;

@ConditionalOnClass({ DruidDataSourceProperties.class })

@EnableConfigurationProperties({ DruidDataSourceProperties.class, })

@ConditionalOnProperty(prefix = "spring.datasource.druid", name = "initiate-mode", havingValue = "multi", matchIfMissing = false)

public class DruidDataSourceConfig {

	@Autowired
	private DruidDataSourceProperties druidDataSourceProperties;

	@Autowired(required = false)
	private DruidStatProperties druidStatProperties;

	@Bean
	public DruidDataSourceProvider druidDataSourceProvider() {
		return new DruidDataSourceProvider(druidDataSourceProperties, druidStatProperties);
	}

}
