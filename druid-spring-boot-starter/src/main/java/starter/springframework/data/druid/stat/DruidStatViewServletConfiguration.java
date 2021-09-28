package starter.springframework.data.druid.stat;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import com.alibaba.druid.support.http.StatViewServlet;

import starter.springframework.data.druid.properties.DruidStatProperties;

@ConditionalOnWebApplication
@ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true", matchIfMissing = true)
public class DruidStatViewServletConfiguration {

    @Bean
    public ServletRegistrationBean statViewServletRegistrationBean(DruidStatProperties properties) {

        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();

        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());

        registrationBean.addUrlMappings(StringUtils.hasLength(config.getUrlPattern()) ? config.getUrlPattern() : "/druid/*");

        if (StringUtils.hasLength(config.getAllow())) {
        	 registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_ALLOW, config.getAllow());
        }

        if (StringUtils.hasLength(config.getDeny())) {
            registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_DENY, config.getDeny());
        }

        if (StringUtils.hasLength(config.getLoginUsername())) {
            registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_USERNAME, config.getLoginUsername());
        }
        
        if (StringUtils.hasLength(config.getLoginPassword())) {
            registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_PASSWORD, config.getLoginPassword());
        }

        if (StringUtils.hasLength(config.getResetEnable())) {
            registrationBean.addInitParameter("resetEnable", config.getResetEnable());
        }

        return registrationBean;
    }

}
