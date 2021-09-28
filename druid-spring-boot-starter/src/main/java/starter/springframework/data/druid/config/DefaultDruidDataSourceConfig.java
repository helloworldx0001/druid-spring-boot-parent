package starter.springframework.data.druid.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@ConditionalOnProperty(prefix = "spring.datasource.druid", name = "initiate-mode", havingValue = "simple", matchIfMissing = true)

public class DefaultDruidDataSourceConfig {

	@ConfigurationProperties(prefix = "spring.datasource.druid")
	@Bean
	@Primary
	@ConditionalOnMissingBean(value = DataSource.class)
	public DataSource druidDataSource() throws SQLException {
		return new DruidDataSource();
	}

	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),
				"/druid/*");
		Map<String, String> initParam = new HashMap<>();
		initParam.put(StatViewServlet.PARAM_NAME_USERNAME, "root");
		initParam.put(StatViewServlet.PARAM_NAME_PASSWORD, "root");
		initParam.put(StatViewServlet.PARAM_NAME_ALLOW, "");
		initParam.put(StatViewServlet.PARAM_NAME_DENY, "192.168.0.1");
		bean.setInitParameters(initParam);
		return bean;
	}

	public FilterRegistrationBean webStatFilter() {
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new WebStatFilter());

		Map<String, String> initPrams = new HashMap<>();
		initPrams.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
		bean.setInitParameters(initPrams);

		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
	}

}
