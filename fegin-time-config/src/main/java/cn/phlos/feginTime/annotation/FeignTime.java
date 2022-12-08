package cn.phlos.feginTime.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeignTime {

     int connectTimeout() default 10000;

//     TimeUnit connectTimeoutUnit() default TimeUnit.MILLISECONDS;

     int readTimeout() default 60000;

//     TimeUnit readTimeoutUnit() default TimeUnit.MILLISECONDS;

     boolean followRedirects() default true;
    
}
