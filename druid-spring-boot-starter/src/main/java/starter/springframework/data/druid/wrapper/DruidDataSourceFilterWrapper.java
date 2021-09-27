package starter.springframework.data.druid.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.filter.encoding.EncodingConvertFilter;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;

public class DruidDataSourceFilterWrapper extends DruidDataSource { 

	private static final long serialVersionUID = -5497889605511756514L;

	public DruidDataSourceFilterWrapper(DataSourceProperties basicProperties) {
		super.setUsername(basicProperties.determineUsername());
		super.setPassword(basicProperties.determinePassword());
		super.setUrl(basicProperties.determineUrl());
		super.setDriverClassName(basicProperties.getDriverClassName());
		super.setName(basicProperties.getName());
	}
	
    @Autowired(required = false)
    public void addStatFilter(StatFilter statFilter) {
        super.filters.add(statFilter);
        System.out.println("pool: druid addStatFilter");
    }

    @Autowired(required = false)
    public void addConfigFilter(ConfigFilter configFilter) {
        super.filters.add(configFilter);
    }

    @Autowired(required = false)
    public void addEncodingConvertFilter(EncodingConvertFilter encodingConvertFilter) {
        super.filters.add(encodingConvertFilter);
    }

    @Autowired(required = false)
    public void addSlf4jLogFilter(Slf4jLogFilter slf4jLogFilter) {
        super.filters.add(slf4jLogFilter);
        System.out.println("pool: druid addSlf4jLogFilter");
    }

    @Autowired(required = false)
    public void addLog4jFilter(Log4jFilter log4jFilter) {
        super.filters.add(log4jFilter);
    }

    @Autowired(required = false)
    public void addLog4j2Filter(Log4j2Filter log4j2Filter) {
        super.filters.add(log4j2Filter);
    }

    @Autowired(required = false)
    public void addCommonsLogFilter(CommonsLogFilter commonsLogFilter) {
        super.filters.add(commonsLogFilter);
    }

    @Autowired(required = false)
    public void addWallFilter(WallFilter wallFilter) {
        super.filters.add(wallFilter);
    }

}
