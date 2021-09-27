package starter.springframework.data.druid.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperties {

	private int initialSize;

	private int minIdle;

	private int maxActive = 100;

	private long maxWait;

	private long timeBetweenEvictionRunsMillis;

	private long minEvictableIdleTimeMillis = -1;

	private long maxEvictableIdleTimeMillis = -1;

	private String validationQuery;

	private boolean testWhileIdle;

	private boolean testOnBorrow;

	private boolean testOnReturn;

}
