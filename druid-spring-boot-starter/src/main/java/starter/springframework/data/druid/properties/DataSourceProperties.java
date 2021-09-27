package starter.springframework.data.druid.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
 */
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties {

	/**
	 * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
	 */
	private String driverClassName;

	/**
	 * JDBC URL of the database.
	 */
	private String url;

	/**
	 * Login username of the database.
	 */
	private String username;

	/**
	 * Login password of the database.
	 */
	private String password;

}
