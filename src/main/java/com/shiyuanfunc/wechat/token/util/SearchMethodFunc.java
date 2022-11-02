package com.shiyuanfunc.wechat.token.util;

import java.beans.Introspector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @Author MUSI
 * @Date 2022/10/28 10:23 PM
 * @Description
 * @Version
 **/
@FunctionalInterface
public interface SearchMethodFunc<T, R> extends Function<T, R>, Serializable {

    /**
     * 获取字段名称
     * @return
     */
    default String getFieldName(){

        try{
            Method writeReplace = this.getClass().getDeclaredMethod("writeReplace");
            writeReplace.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) writeReplace.invoke(this);
            String implMethodName = serializedLambda.getImplMethodName();
            if (implMethodName != null){
                return Introspector.decapitalize(implMethodName.replaceFirst("get", ""));
            }
        }catch (Exception ex){
            throw new RuntimeException("获取字段名称异常", ex);
        }
        throw new RuntimeException("获取字段名称异常");
    }
}
