package cn.phlos.feginTime.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeignTime {

     long connectTimeout() default 1000;

     TimeUnit connectTimeoutUnit() default TimeUnit.MILLISECONDS;

     long readTimeout() default 2000;

     TimeUnit readTimeoutUnit() default TimeUnit.MILLISECONDS;

     boolean followRedirects() default true;
    
}
