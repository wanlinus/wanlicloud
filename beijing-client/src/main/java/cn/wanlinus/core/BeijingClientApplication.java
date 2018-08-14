package cn.wanlinus.core;

import cn.wanlinus.core.domain.BeijingClient;
import cn.wanlinus.core.domain.InitInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author wanli
 * @date 2018-08-10 15:24
 */
@SpringBootApplication
public class BeijingClientApplication {

    private static Logger logger = LoggerFactory.getLogger(BeijingClientApplication.class);

    public static void main(String[] args) throws IOException {


        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            BeijingClient beijingClient = InitInfo.getInstance().getBeijingClient();
            HttpPost httpPost = new HttpPost(String.format("%s:%s/register", beijingClient.getIp(), beijingClient.getPort()));
            HttpResponse response = httpClient.execute(httpPost);
            String entity = EntityUtils.toString(response.getEntity());
            logger.info(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(BeijingClientApplication.class);
    }
}
