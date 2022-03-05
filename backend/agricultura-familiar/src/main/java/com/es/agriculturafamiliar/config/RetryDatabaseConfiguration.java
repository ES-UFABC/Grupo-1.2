package com.es.agriculturafamiliar.config;

import javax.sql.DataSource;

import com.es.agriculturafamiliar.repository.RetryDataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RetryDatabaseConfiguration implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {            
            return new RetryDataSource( (DataSource) bean);
        }
        
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
    
    
}