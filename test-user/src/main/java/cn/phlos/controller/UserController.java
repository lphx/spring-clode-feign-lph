package cn.phlos.controller;


import cn.phlos.feign.OrderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@RestController
public class UserController {

    @Autowired
    OrderFeignService orderFeignService;

    @RequestMapping(value = "/test")
    public String  findOrderByUserId() {
        //feign调用
        String result = orderFeignService.findOrderByUserId();
        return result;
    }
    

}
