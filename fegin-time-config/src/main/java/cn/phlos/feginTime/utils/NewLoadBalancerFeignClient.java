package cn.phlos.feginTime.utils;

import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.FeignLoadBalancer;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName: NewLoadBalancerFeignClient
 * @Author: lph
 * @Description:
 * @Date: 2022/12/5 23:07
 */
public class NewLoadBalancerFeignClient extends LoadBalancerFeignClient {

    private volatile Map<String, FeignLoadBalancer> cache = new ConcurrentReferenceHashMap();

    public NewLoadBalancerFeignClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory, SpringClientFactory clientFactory) {
        super(delegate, lbClientFactory, clientFactory);
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        options = new Request.Options(1000,2000);
        return super.execute(request, options);
    }
}
