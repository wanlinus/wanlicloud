package cn.wanlinus.beijing.controller;

import cn.wanlinus.beijing.domain.BeijingContainer;
import cn.wanlinus.cloud.core.domain.BeijingClient;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wanli
 * @date 2018-08-10 14:11
 */
@RestController("/server")
public class ServerController {

    private Logger logger = LoggerFactory.getLogger(ServerController.class);

    private final BeijingContainer bc;

    @Autowired
    public ServerController(BeijingContainer bc) {
        this.bc = bc;
    }


    /**
     * 服务注册API
     *
     * @param client 需要注册的客户端
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public ResponseEntity<List<BeijingClient>> register(@RequestBody BeijingClient client) {
        logger.info(String.format("服务注册:%s", JSON.toJSONString(client)));
        return ResponseEntity.ok(bc.addClient(client));
    }

}
