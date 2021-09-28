package starter.springframework.data.druid.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.pool.DruidAbstractDataSource;

import lombok.Data;

@Data

@ConfigurationProperties(prefix = "spring.datasource.druid")

public class DruidDataSourceProperties {

	private int initialSize = DruidAbstractDataSource.DEFAULT_INITIAL_SIZE;
	private int minIdle = DruidAbstractDataSource.DEFAULT_MIN_IDLE;
	// @Deprecated
	// private int maxIdle = DruidAbstractDataSource.DEFAULT_MAX_IDLE;
	private int maxActive = DruidAbstractDataSource.DEFAULT_MAX_ACTIVE_SIZE;
	private long maxWait = DruidAbstractDataSource.DEFAULT_MAX_WAIT;
	private long timeBetweenEvictionRunsMillis = -1;
	private long minEvictableIdleTimeMillis = -1;
	private long maxEvictableIdleTimeMillis = -1;
	private String validationQuery = null;
	private boolean testWhileIdle = true;
	private boolean testOnBorrow = false;
	private boolean testOnReturn = false;
	private boolean poolPreparedStatements = false;
	private int maxPoolPreparedStatementPerConnectionSize = 10;

}
