package cn.wanlinus.beijing.config;

import cn.wanlinus.beijing.domain.BeijingContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanli
 * @date 2018-08-10 13:21
 */
@Configuration
public class BeijingConfig {

    @Value("${beijing.container.initialCapacity}")
    public Integer initialCapacity;

    @Bean
    public BeijingContainer bc() {
        return new BeijingContainer(new ConcurrentHashMap<>(initialCapacity));
    }

}
