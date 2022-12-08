package cn.phlos.feginTime;

import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.FeignLoadBalancer;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * @ClassName: NewLoadBalancerFeignClient
 * @Author: lph
 * @Description:
 * @Date: 2022/12/5 23:07
 */
public class NewLoadBalancerFeignClient extends LoadBalancerFeignClient implements FeignTimeOptionsService {

    private volatile Map<String, Map<Request.HttpMethod, Request.Options>> cache = new ConcurrentReferenceHashMap();

    public NewLoadBalancerFeignClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory, SpringClientFactory clientFactory) {
        super(delegate, lbClientFactory, clientFactory);
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        Request.Options newOptions = getOptions(request);
        if (newOptions != null){
            options = newOptions;
        }
        return super.execute(request, options);
    }

    private Request.Options getOptions(Request request) {

        if (request == null){
            return null;
        }

        URI asUri = URI.create(request.url());
        if (!cache.containsKey(asUri.getPath())){
            return null;
        }
        return cache.get(asUri.getPath()).get(request.httpMethod());
    }


    @Override
    public void setGetOptions(String url, Request.Options options) {
        setOptions(Request.HttpMethod.GET, url, options);
    }

    @Override
    public void setPostOptions(String url, Request.Options options) {
        setOptions(Request.HttpMethod.POST, url, options);
    }

    @Override
    public void setPutOptions(String url, Request.Options options) {
        setOptions(Request.HttpMethod.PUT, url, options);
    }

    @Override
    public void setDeleteOptions(String url, Request.Options options) {
        setOptions(Request.HttpMethod.DELETE, url, options);
    }

    @Override
    public void setAllOptions(String url, Request.Options options) {
        setGetOptions(url, options);
        setPostOptions(url, options);
        setPutOptions(url, options);
        setDeleteOptions(url, options);
    }


    private void setOptions(Request.HttpMethod httpMethod, String url, Request.Options options) {
        Map<Request.HttpMethod, Request.Options> requestOptionsMap = cache.getOrDefault(url, new HashMap<>());
        requestOptionsMap.put(httpMethod, options);
        cache.put(url, requestOptionsMap);
    }


}
