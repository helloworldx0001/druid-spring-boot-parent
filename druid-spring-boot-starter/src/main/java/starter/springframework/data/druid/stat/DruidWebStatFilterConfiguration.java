package starter.springframework.data.druid.stat;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import com.alibaba.druid.support.http.WebStatFilter;

import starter.springframework.data.druid.properties.DruidStatProperties;

@ConditionalOnWebApplication
@ConditionalOnProperty(name = "spring.datasource.druid.web-stat-filter.enabled", havingValue = "true", matchIfMissing = true)
public class DruidWebStatFilterConfiguration {

	@Bean
	public FilterRegistrationBean webStatFilterRegistrationBean(DruidStatProperties properties) {

		DruidStatProperties.WebStatFilter config = properties.getWebStatFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		WebStatFilter filter = new WebStatFilter();
		registrationBean.setFilter(filter);

		registrationBean.addUrlPatterns(StringUtils.hasLength(config.getUrlPattern()) ? config.getUrlPattern() : "/*");

		registrationBean.addInitParameter("exclusions",
				StringUtils.hasLength(config.getExclusions()) ? config.getExclusions()
						: "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

		if (StringUtils.hasLength(config.getSessionStatEnable())) {
			registrationBean.addInitParameter("sessionStatEnable", config.getSessionStatEnable());
		}

		if (StringUtils.hasLength(config.getSessionStatMaxCount())) {
			registrationBean.addInitParameter("sessionStatMaxCount", config.getSessionStatMaxCount());
		}

		if (StringUtils.hasLength(config.getPrincipalSessionName())) {
			registrationBean.addInitParameter("principalSessionName", config.getPrincipalSessionName());
		}

		if (StringUtils.hasLength(config.getPrincipalCookieName())) {
			registrationBean.addInitParameter("principalCookieName", config.getPrincipalCookieName());
		}

		if (StringUtils.hasLength(config.getProfileEnable())) {
			registrationBean.addInitParameter("profileEnable", config.getProfileEnable());
		}

		return registrationBean;
	}

}
