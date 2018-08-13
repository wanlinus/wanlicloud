package cn.wanlinus.beijing.task;

import cn.wanlinus.beijing.domain.BeijingContainer;
import cn.wanlinus.cloud.core.domain.BeijingClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author wanli
 * @date 2018-08-10 14:19
 */
@Component
public class HeartBeatSchedule {

    private Logger logger = LoggerFactory.getLogger(HeartBeatSchedule.class);
    private final BeijingContainer bc;

    @Value("${beijing.server.heartBeat}")
    private Integer heartBeatTime;

    @Autowired
    public HeartBeatSchedule(BeijingContainer bc) {
        this.bc = bc;
    }


    @Scheduled(cron = "*/${beijing.server.heartBeat} * * * * ?")
    public void heartBeat() {
        logger.info("执行心跳事件");
        long heartbeatStart = System.currentTimeMillis();

        List<BeijingClient> list = bc.getAllClients();
        if (list.isEmpty()) {
            logger.info("没有注册服务");
            return;
        }
        for (BeijingClient client : list) {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet get = new HttpGet(String.format("%s:%s/heartbeat", client.getIp(), client.getPort()));
                CloseableHttpResponse response = httpClient.execute(get);
                byte[] rtc = new byte[4];
                response.getEntity().getContent().read(rtc, 0, 4);
                //如果返回不为true,删除该客户端
                if (!"TRUE".equalsIgnoreCase(new String(rtc))) {
                    bc.delClient(client.getName(), client.getId());
                }
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        long heartbeatEnd = System.currentTimeMillis();
        logger.info("heartBeat结束, 用时: %d", heartbeatEnd - heartbeatStart);
    }

}
