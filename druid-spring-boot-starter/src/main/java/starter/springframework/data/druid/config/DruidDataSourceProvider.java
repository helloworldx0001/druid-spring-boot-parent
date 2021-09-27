package starter.springframework.data.druid.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.alibaba.druid.pool.DruidDataSource;

import starter.springframework.data.druid.properties.DruidDataSourceProperties;

@ConditionalOnClass({ DruidDataSourceProperties.class })
@EnableConfigurationProperties({ DruidDataSourceProperties.class })
@ConditionalOnProperty(prefix = "spring.datasource.druid", name = "initiate-mode", havingValue = "multi", matchIfMissing = false)

public class DruidDataSourceProvider {

	@Autowired
    private DruidDataSourceProperties druidDataSourceProperties;
    
    // public DruidDataSourceProvider(DruidDataSourceProperties druidDataSourceProperties) {
    // 	this.druidDataSourceProperties = druidDataSourceProperties;
    // }

    public DruidDataSource dataDruidDataSource() {

		DruidDataSource druidDataSource = new DruidDataSource();
		
		druidDataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
		druidDataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
		druidDataSource.setMaxActive(druidDataSourceProperties.getMaxActive());

		druidDataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
		
		druidDataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
		
		if(druidDataSourceProperties.getMinEvictableIdleTimeMillis() != -1) {
			System.out.println("MinEvictableIdleTimeMillis: " + druidDataSourceProperties.getMinEvictableIdleTimeMillis());
			druidDataSource.setMinEvictableIdleTimeMillis(druidDataSourceProperties.getMinEvictableIdleTimeMillis());
		}
		
		if(druidDataSourceProperties.getMaxEvictableIdleTimeMillis() != -1) {
			System.out.println("MaxEvictableIdleTimeMillis: " + druidDataSourceProperties.getMaxEvictableIdleTimeMillis());
			druidDataSource.setMaxEvictableIdleTimeMillis(druidDataSourceProperties.getMaxEvictableIdleTimeMillis());
		}

		druidDataSource.setValidationQuery(druidDataSourceProperties.getValidationQuery());
		
		druidDataSource.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
		
		druidDataSource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
		
		druidDataSource.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
		
		return druidDataSource;
    }

	public DruidDataSource dataSource(DruidDataSource druidDataSource) {

		druidDataSource.setInitialSize(druidDataSourceProperties.getInitialSize());
		druidDataSource.setMinIdle(druidDataSourceProperties.getMinIdle());
		druidDataSource.setMaxActive(druidDataSourceProperties.getMaxActive());
		
		druidDataSource.setMaxWait(druidDataSourceProperties.getMaxWait());
		
		druidDataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperties.getTimeBetweenEvictionRunsMillis());
		
		if(druidDataSourceProperties.getMinEvictableIdleTimeMillis() != -1) {
			System.out.println("MinEvictableIdleTimeMillis: " + druidDataSourceProperties.getMinEvictableIdleTimeMillis());
			druidDataSource.setMinEvictableIdleTimeMillis(druidDataSourceProperties.getMinEvictableIdleTimeMillis());
		}
		
		if(druidDataSourceProperties.getMaxEvictableIdleTimeMillis() != -1) {
			System.out.println("MaxEvictableIdleTimeMillis: " + druidDataSourceProperties.getMaxEvictableIdleTimeMillis());
			druidDataSource.setMaxEvictableIdleTimeMillis(druidDataSourceProperties.getMaxEvictableIdleTimeMillis());
		}

		druidDataSource.setValidationQuery(druidDataSourceProperties.getValidationQuery());
		
		druidDataSource.setTestWhileIdle(druidDataSourceProperties.isTestWhileIdle());
		
		druidDataSource.setTestOnBorrow(druidDataSourceProperties.isTestOnBorrow());
		
		druidDataSource.setTestOnReturn(druidDataSourceProperties.isTestOnReturn());
		
        	return druidDataSource;
	}
    
}
