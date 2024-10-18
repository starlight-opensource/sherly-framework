package cn.starlightsoftware.sherly.banner.config;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;

/**
 * @author 谷子毅
 * @date 2024/4/25
 */
@Configuration
@Slf4j
public class StartupBannerConfig {

    /**
     * 服务启动成功后输出的项目相关信息
     */
    @Bean
    public CommandLineRunner createStartupBanner(ConfigurableEnvironment environment) {
        return args -> {
            String port = environment.getProperty("server.port");
            String serviceName = environment.getProperty("spring.application.name");
            String ip = InetAddress.getLocalHost().getHostAddress();
            String contextPath = environment.getProperty("server.servlet.context-path");
            String path = ObjectUtil.isNotNull(contextPath) ? contextPath : "";

            log.info("\n----------------------------------------------------------\n\t" +
                    serviceName + " is running! There are some useful urls:\n\t" +
                    "System:\t http://" + ip + ":" + port + path + "\n\t" +
                    "knife4j: http://" + ip + ":" + port + path + "/doc.html\n" +
                    "----------------------------------------------------------");
        };
    }

}
