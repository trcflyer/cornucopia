package com.easypay.cornucopiaallqrpay.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
@MapperScan(value = "com.easypay.cornucopiaallqrpay.dal.dao" )
public class DruidDataSourceConfiguration {

    @Value("${spring.datasource.druid.initial-size:5}")
    private int initialSize;

    @Value("${spring.datasource.druid.min-idle:5}")
    private int minIdle;

    @Value("${spring.datasource.druid.max-active:50}")
    private int maxActive;

    @Value("${spring.datasource.druid.max-wait:60000}")
    private int maxWait;

    @Value("${spring.datasource.druid.time-between-eviction-runs-millis:60000}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.min-evictable-idle-time-millis:300000}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.test-while-idle:true}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.test-on-borrow:false}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.test-on-return:false}")
    private boolean testOnReturn;

    @Value("${spring.datasource.druid.pool-prepared-statements:true}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size:20}")
    private int maxPoolPreparedStatementPerConnectionSize;


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();

        // configuration
        datasource.setInitialSize(this.initialSize);
        datasource.setMinIdle(this.minIdle);
        datasource.setMaxActive(this.maxActive);
        datasource.setMaxWait(this.maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        datasource.setTestWhileIdle(this.testWhileIdle);
        datasource.setTestOnBorrow(this.testOnBorrow);
        datasource.setTestOnReturn(this.testOnReturn);
        datasource.setPoolPreparedStatements(this.poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
        return datasource;
    }

    /**
     * durid监控用
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServle() {
        //注册服务
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        // 白名单(为空表示,所有的都可以访问,多个IP的时候用逗号隔开)
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "127.0.0.2");
        // 设置登录的用户名和密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        // 是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * durid监控用
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
                new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
       log.info("druid初始化成功!");
        return filterRegistrationBean;

    }

    /**
     * 分页用
     * @return
     */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //数据库
        properties.setProperty("helperDialect", "mysql");
        //是否将参数offset作为PageNum使用
        properties.setProperty("offsetAsPageNum", "true");
        //是否进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //是否分页合理化
        properties.setProperty("reasonable", "false");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
