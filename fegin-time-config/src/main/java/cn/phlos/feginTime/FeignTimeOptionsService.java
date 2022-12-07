package cn.phlos.feginTime;

import feign.Request;

/**
 * @ClassName: FeignTimeOptionsService
 * @Author: lph
 * @Description:
 * @Date: 2022/12/7 22:32
 */
public interface FeignTimeOptionsService {


   default void setGetOptions(String url, Request.Options options){
   }

    default void setPostOptions(String url, Request.Options options){
    }

    default void setPutOptions(String url, Request.Options options){
    }

    default void setDeleteOptions(String url, Request.Options options){
    }

    default void setAllOptions(String url, Request.Options options){
    }

}
