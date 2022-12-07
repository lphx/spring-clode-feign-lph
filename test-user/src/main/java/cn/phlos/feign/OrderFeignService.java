package cn.phlos.feign;

import cn.phlos.feginTime.annotation.FeignTime;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fox
 */

//FeignConfig局部配置
//@FeignClient(value = "mall-order",path = "/order",configuration = FeignConfig.class)
@FeignClient(value = "test-order",path = "/order")
public interface OrderFeignService {

    @RequestMapping("/findOrderByUserId")
    @FeignTime(connectTimeout = 1000)
    String findOrderByUserId();

    @RequestMapping({"/findOrderByUserId1"})
    @FeignTime(connectTimeout = 100)
    String findOrderByUserIdaa();




//    @RequestLine("GET /findOrderByUserId/{userId}")
//    R findOrderByUserId(@Param("userId") Integer userId);
}
