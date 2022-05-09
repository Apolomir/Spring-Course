package com.epam.springcore.bean;

import com.epam.springcore.repository.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomBeanPostProcessor implements BeanPostProcessor {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if (bean instanceof Storage) {
      LOGGER.debug(bean.getClass().getName());
      ((Storage<?>) bean).init();
    }
    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }
}
