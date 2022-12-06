package cn.phlos.feginTime.annotation;

import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName: FeignTimeCondition
 * @Author: lph
 * @Description:
 * @Date: 2022/12/6 22:03
 */
public class FeignTimeAnnotation implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(FeignClient.class);
        System.out.println(beansWithAnnotation);
        for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
            Class clazz = getFeignClientClass(entry.getKey());
            findAndSetFeignTimeData(clazz);


        }
    }

    private void findAndSetFeignTimeData(Class clazz){
        for (Method declaredField : clazz.getMethods()) {
            if (!declaredField.isAnnotationPresent(FeignTime.class)){
                continue;
            }
            FeignTime declaredAnnotation = declaredField.getDeclaredAnnotation(FeignTime.class);
            System.out.println(declaredAnnotation);
            RequestMapping requestMappingAnnotation = declaredField.getDeclaredAnnotation(RequestMapping.class);
            System.out.println(requestMappingAnnotation);

        }
    }



    private Class getFeignClientClass(String clazzPath){
        try {
            return ClassLoader.getSystemClassLoader().loadClass(clazzPath);
        }catch (Exception e){
            throw new RuntimeException("FeignClient load error");
        }
    }
}
