package cn.wanlinus.beijing.controller;

import cn.wanlinus.beijing.domain.BeijingContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wanli
 * @date 2018-08-10 13:03
 */
@Controller
public class BeijingController {

    @Autowired
    private BeijingContainer bc;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("clients", bc.getClients());
        return "index";
    }


}
