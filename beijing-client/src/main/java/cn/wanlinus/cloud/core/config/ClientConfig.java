package cn.wanlinus.cloud.core.config;

import cn.wanlinus.cloud.core.domain.BeijingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @author wanli
 * @date 2018-08-10 15:44
 */
@Configuration
public class ClientConfig {

    @Value("${beijing.client.name}")
    public String clientName;
    @Value("${server.port}")
    public Integer port;
    @Value("${beijing.server.ip}")
    public String serverIp;

    @Value("${beijing.server.port}")
    public Integer serverPort;

    @Bean
    public BeijingClient bc() throws UnknownHostException {
        BeijingClient bc = new BeijingClient();
        bc.setId(String.valueOf(UUID.randomUUID()));
        bc.setIp(InetAddress.getLocalHost().getHostAddress());
        bc.setName(clientName);
        bc.setPort(port);
        return bc;
    }
}
