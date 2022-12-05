package cn.phlos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fox
 */

//FeignConfig局部配置
//@FeignClient(value = "mall-order",path = "/order",configuration = FeignConfig.class)
@FeignClient(value = "test-order",path = "/order")
public interface OrderFeignService {

    @RequestMapping("/findOrderByUserId")
    String findOrderByUserId();

//    @RequestLine("GET /findOrderByUserId/{userId}")
//    R findOrderByUserId(@Param("userId") Integer userId);
}
