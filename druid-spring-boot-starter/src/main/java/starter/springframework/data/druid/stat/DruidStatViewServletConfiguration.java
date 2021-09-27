package starter.springframework.data.druid.stat;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.util.Utils;

import starter.springframework.data.druid.properties.DruidStatProperties;

@ConditionalOnWebApplication
@ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true", matchIfMissing = true)
public class DruidStatViewServletConfiguration {

    @Bean
    public ServletRegistrationBean statViewServletRegistrationBean(DruidStatProperties properties) {

        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();

        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());

        registrationBean.addUrlMappings(config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*");
        
        if (config.getAllow() != null) {
            registrationBean.addInitParameter("allow", config.getAllow());
        }

        if (config.getDeny() != null) {
            registrationBean.addInitParameter("deny", config.getDeny());
        }

        if (config.getLoginUsername() != null) {
            registrationBean.addInitParameter("loginUsername", config.getLoginUsername());
        }
        
        if (config.getLoginPassword() != null) {
            registrationBean.addInitParameter("loginPassword", config.getLoginPassword());
        }

        if (config.getResetEnable() != null) {
            registrationBean.addInitParameter("resetEnable", config.getResetEnable());
        }

        return registrationBean;
    }

	@Bean
	public FilterRegistrationBean removeDruidFilterRegistrationBean(DruidStatProperties properties) {
		
		DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();

		String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
		String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
		final String filePath = "support/http/resources/js/common.js";
	
		Filter filter = new Filter() {
			
			@Override
			public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
			}

			@Override
			public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
				
				chain.doFilter(request, response);
				response.resetBuffer();
				String text = Utils.readFromResource(filePath);
				text = text.replaceAll("<a.*?banner\"></a><br/>", "");
				text = text.replaceAll("powered.*?shrek.wang</a>", "");
				response.getWriter().write(text);
				
			}

			@Override
			public void destroy() {
				
			}
		};
		
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns(commonJsPattern);
		
		return registrationBean;
	}

}
