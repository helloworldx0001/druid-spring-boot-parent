package starter.springframework.data.druid.wrapper;

import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidDataSourceBuilder {

    public static DruidDataSourceBuilder create() {
        return new DruidDataSourceBuilder();
    }

    /**
     * For build multiple DruidDataSource, detail see document.
     */
    public DruidDataSource build() {
        return new DruidDataSourceWrapper();
    }

    public DruidDataSource build(Environment env, String prefix) {
        DruidDataSource druidDataSource = new DruidDataSourceWrapper();
        druidDataSource.setMinEvictableIdleTimeMillis(
                env.getProperty(prefix + "min-evictable-idle-time-millis",
                        Long.class,
                        DruidDataSource.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS));
        druidDataSource.setMaxEvictableIdleTimeMillis(
                env.getProperty(prefix + "max-evictable-idle-time-millis",
                        Long.class,
                        DruidDataSource.DEFAULT_MAX_EVICTABLE_IDLE_TIME_MILLIS));
        return druidDataSource;
    }
    
}
