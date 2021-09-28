package starter.springframework.data.druid.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidStatProperties {

	// private String connectionProperties = null;
	// private String filters;
	private boolean useGlobalDataSourceStat = false;
	// private long timeBetweenLogStatsMillis;

	private String[] aopPatterns;
	private StatViewServlet statViewServlet = new StatViewServlet();
	private WebStatFilter webStatFilter = new WebStatFilter();

	@Data
	public static class StatViewServlet {
		/**
		 * Enable StatViewServlet.
		 */
		private boolean enabled = true;
		private String urlPattern;
		private String allow;
		private String deny;
		private String loginUsername;
		private String loginPassword;
		private String resetEnable;

	}

	@Data
	public static class WebStatFilter {
		/**
		 * Enable WebStatFilter.
		 */
		private boolean enabled = true;
		private String urlPattern;
		private String exclusions;
		private String sessionStatMaxCount;
		private String sessionStatEnable;
		private String principalSessionName;
		private String principalCookieName;
		private String profileEnable;

	}

}
