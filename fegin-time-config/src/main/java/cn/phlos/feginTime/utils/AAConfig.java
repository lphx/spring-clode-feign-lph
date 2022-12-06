package cn.phlos.feginTime.utils;

import feign.Client;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import cn.phlos.feginTime.annotation.FeignTimeAnnotation;

/**
 * @ClassName: AAConfig
 * @Author: lph
 * @Description:
 * @Date: 2022/12/5 23:11
 */
@Configuration
public class AAConfig {

    @Bean
    public FeignTimeAnnotation getFeignTimeAnnotation(){
        return new FeignTimeAnnotation();
    }


    @Bean
    public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory, SpringClientFactory clientFactory) {
        return new NewLoadBalancerFeignClient(new Client.Default((SSLSocketFactory)null, (HostnameVerifier)null), cachingFactory, clientFactory);
    }
}
