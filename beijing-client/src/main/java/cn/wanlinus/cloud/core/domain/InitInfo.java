package cn.wanlinus.cloud.core.domain;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

/**
 * @author wanli
 * @date 2018-08-10 16:14
 */
public class InitInfo {
    private BeijingClient beijingClient;

    private InitInfo() {
        beijingClient = new BeijingClient();
    }

    private static volatile InitInfo initInfo = null;

    public static InitInfo getInstance() throws IOException {
        if (initInfo == null) {
            synchronized (InitInfo.class) {
                if (initInfo == null) {
                    InitInfo info = new InitInfo();
                    Properties properties = new Properties();
                    InputStream in = new BufferedInputStream(new FileInputStream("application.properties"));
                    properties.load(in);
                    info.beijingClient.setIp(properties.getProperty("beijing.server.ip"));
                    info.beijingClient.setPort(Integer.parseInt(properties.getProperty("beijing.server.port")));
                    info.beijingClient.setName(properties.getProperty("beijing.client.name"));
                    info.beijingClient.setId(UUID.randomUUID().toString());
                }
            }
        }
        return initInfo;
    }

    public BeijingClient getBeijingClient() {
        return beijingClient;
    }
}
