package cn.wanlinus.core.domain;

import java.io.Serializable;

/**
 * 用来记录服务信息的类
 *
 * @author wanli
 * @date 2018-08-10 10:57
 */
public class BeijingClient implements Serializable {
    /**
     * 唯一标识符
     */
    private String id;
    /**
     * 服务名
     */
    private String name;
    /**
     * 服务ip地址
     */
    private String ip;
    /**
     * 服务端口
     */
    private Integer port;
    /**
     * 上次echo时间
     */
    private Long lastEcho;

    public BeijingClient() {
    }

    public BeijingClient(String id, String name, String ip, Integer port, Long lastEcho) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.lastEcho = lastEcho;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Long getLastEcho() {
        return lastEcho;
    }

    public void setLastEcho(Long lastEcho) {
        this.lastEcho = lastEcho;
    }

    @Override
    public String toString() {
        return "BeijingClient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", lastEcho=" + lastEcho +
                '}';
    }
}
