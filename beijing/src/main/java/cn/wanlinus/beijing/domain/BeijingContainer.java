package cn.wanlinus.beijing.domain;

import cn.wanlinus.beijing.exception.BeijingException;
import cn.wanlinus.cloud.core.domain.BeijingClient;

import java.io.Serializable;
import java.util.*;

/**
 * 用于存放所有注册服务的容器,本是单例
 * 但容器使用Spring来管理,在spring中设置为单例就好
 *
 * @author wanli
 * @date 2018-08-10 13:06
 */
public class BeijingContainer implements Serializable {
    private volatile Map<String, List<BeijingClient>> clients = null;

    public BeijingContainer(Map<String, List<BeijingClient>> clients) {
        this.clients = clients;
    }

    /**
     * 添加客户端
     *
     * @param client 需要被添加的客户端
     * @return 客户端实例数组
     */
    public List<BeijingClient> addClient(BeijingClient client) {
        List<BeijingClient> list = clients.getOrDefault(client.getName(), new ArrayList<>());
        list.add(client);
        return clients.put(client.getName(), list);
    }

    /**
     * 删除服务名为name Id为id的客户端
     *
     * @param name 服务名
     * @param id   服务Id
     * @return 删除的客户端实例
     */
    public BeijingClient delClient(String name, String id) {
        List<BeijingClient> list = clients.get(name);
        if (list == null || list.isEmpty()) {
            throw new BeijingException(String.format("删除服务: %s 失败,因为没有这个服务", name));
        }
        int index = -1;
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new BeijingException(String.format("删除服务%s失败, Id错误", name));
        }
        return list.remove(index);
    }

    /**
     * 获取所有的客户端
     *
     * @return 所有的客户端容器
     */
    public List<BeijingClient> getAllClients() {
        Set<String> set = clients.keySet();
        List<BeijingClient> list = new ArrayList<>();
        for (String s : set) {
            list.addAll(clients.get(s));
        }
        return list.isEmpty() ? Collections.emptyList() : list;
    }

    public Map<String, List<BeijingClient>> getClients() {
        return clients;
    }
}
