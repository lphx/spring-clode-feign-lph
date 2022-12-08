package cn.phlos.feginTime.annotation;

import cn.phlos.feginTime.FeignTimeOptionsService;
import cn.phlos.feginTime.NewLoadBalancerFeignClient;
import feign.Request;
import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName: FeignTimeCondition
 * @Author: lph
 * @Description:
 * @Date: 2022/12/6 22:03
 */
public class FeignTimeAnnotation implements ApplicationContextAware {

    private FeignTimeOptionsService balancerFeignClient;

    public FeignTimeAnnotation(FeignTimeOptionsService balancerFeignClient) {
        this.balancerFeignClient = balancerFeignClient;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(FeignClient.class);
        System.out.println(beansWithAnnotation);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            Class clazz = getFeignClientClass(entry.getKey());
            findAndSetFeignTimeData(clazz);
        }
    }

    private void findAndSetFeignTimeData(Class clazz) {
        for (Method method : clazz.getMethods()) {
            if (!method.isAnnotationPresent(FeignTime.class)) {
                continue;
            }
            FeignClient feignClient = (FeignClient) clazz.getDeclaredAnnotation(FeignClient.class);
            FeignTime feignTime = method.getDeclaredAnnotation(FeignTime.class);
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                requestAnnotation(annotation, feignTime,feignClient);
            }

        }
    }

    private void requestAnnotation(Annotation annotation, FeignTime feignTime,FeignClient feignClient) {
        if (annotation instanceof RequestMapping) {
            Arrays.stream(((RequestMapping) annotation).value()).forEach(v -> {

                balancerFeignClient.setAllOptions(feignClient.path()+v, getFeignTimeToOptions(feignTime));
            });
            return;
        }

        if (annotation instanceof GetMapping) {
            Arrays.stream(((RequestMapping) annotation).value()).forEach(v -> {

                balancerFeignClient.setGetOptions(v, getFeignTimeToOptions(feignTime));
            });
            return;
        }

        if (annotation instanceof PostMapping) {
            Arrays.stream(((RequestMapping) annotation).value()).forEach(v -> {

                balancerFeignClient.setPostOptions(v, getFeignTimeToOptions(feignTime));
            });
            return;
        }

        if (annotation instanceof PutMapping) {
            Arrays.stream(((RequestMapping) annotation).value()).forEach(v -> {

                balancerFeignClient.setPutOptions(v, getFeignTimeToOptions(feignTime));
            });
            return;
        }

        if (annotation instanceof DeleteMapping) {
            Arrays.stream(((RequestMapping) annotation).path()).forEach(v -> {

                balancerFeignClient.setDeleteOptions(v, getFeignTimeToOptions(feignTime));
            });
        }
    }


    Request.Options getFeignTimeToOptions(FeignTime feignTime) {
        return new Request.Options(feignTime.connectTimeout(), feignTime.readTimeout(), feignTime.followRedirects());
    }


    private Class getFeignClientClass(String clazzPath) {
        try {
            return ClassLoader.getSystemClassLoader().loadClass(clazzPath);
        } catch (Exception e) {
            throw new RuntimeException("FeignClient load error");
        }
    }
}
