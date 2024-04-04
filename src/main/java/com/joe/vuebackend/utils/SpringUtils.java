package com.joe.vuebackend.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring容器工具
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.context = applicationContext;
    }

    /**
     * 根據id獲取spring容器中的物件
     *
     * @param id
     * @return
     */
    public static Object getBean(String id) {
        return context != null ? context.getBean(id) : null;
    }

    /**
     * 根據類型獲取spring容器中的物件
     *
     * @param clazz 物件類型
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return context != null ? context.getBean(clazz) : null;
    }
}
