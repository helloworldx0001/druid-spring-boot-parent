package starter.springframework.data.druid.config;

import com.alibaba.druid.pool.DruidDataSource;

import starter.springframework.data.druid.properties.DruidDataSourceProperties;
import starter.springframework.data.druid.properties.DruidStatProperties;

public class DruidDataSourceProvider {

	private DruidDataSourceProperties druidDataSourceProperties;

	private DruidStatProperties druidStatProperties;

	public DruidDataSourceProvider(DruidDataSourceProperties druidDataSourceProperties,
			DruidStatProperties druidStatProperties) {
		this.druidDataSourceProperties = druidDataSourceProperties;
		this.druidStatProperties = druidStatProperties;
	}

	public DruidDataSource dataSource(DruidDataSource druidDataSource) {

		druidDataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
		druidDataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
		druidDataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
		// @Deprecated
		// druidDataSource.setMaxIdle(druidDataSourceProperties.getMaxIdle());

		druidDataSource.setMaxWait(druidDataSourceProperties.getMaxWait());

		long timeBetweenEvictionRunsMillis = druidDataSourceProperties.getTimeBetweenEvictionRunsMillis();
		if (timeBetweenEvictionRunsMillis != -1) {
			druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		}

		long minEvictableIdleTimeMillis = druidDataSourceProperties.getMinEvictableIdleTimeMillis();
		if (minEvictableIdleTimeMillis != -1) {
			druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		}

		long maxEvictableIdleTimeMillis = druidDataSourceProperties.getMaxEvictableIdleTimeMillis();
		if (maxEvictableIdleTimeMillis != -1) {
			druidDataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
		}

		druidDataSource.setValidationQuery(druidDataSourceProperties.getValidationQuery());

		druidDataSource.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());

		druidDataSource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());

		druidDataSource.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());

		druidDataSource.setPoolPreparedStatements(druidDataSourceProperties.isPoolPreparedStatements());

		// druidDataSource.setMaxOpenPreparedStatements(druidDataSourceProperties.getMaxOpenPreparedStatements());

		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(
				druidDataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());

		if (druidStatProperties != null) {
			druidDataSource.setUseGlobalDataSourceStat(druidStatProperties.isUseGlobalDataSourceStat());

			// druidDataSource.setTimeBetweenLogStatsMillis(druidStatProperties.getTimeBetweenLogStatsMillis());

			// druidDataSource.setConnectionProperties(druidStatProperties.getConnectionProperties());

			// druidDataSource.setFilters(druidStatProperties.getFilters());

		}

		return druidDataSource;
	}

}
