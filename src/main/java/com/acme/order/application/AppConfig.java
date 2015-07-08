package com.acme.order.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.acme.order.HashMapOrderRepository;
import com.acme.order.OrderFactory;
import com.acme.order.delivery.BasicDeliveryTimeServiceImpl;
import com.acme.order.delivery.TimeService;
import com.acme.order.delivery.strategy.DeliveryTimeStrategy;
import com.acme.order.delivery.strategy.SimpleDeliveryTimeStrategy;
import com.acme.order.notification.DeliveryTemplate;
import com.acme.order.notification.MailSender;
import com.acme.order.notification.OrderCancelledTemplate;

@Configuration
public class AppConfig {

	@Autowired
	private DeliveryTimeStrategy strategy;

	@Bean
	public MailSender mailSender() {
		return new MailSender();
	}

	@Bean
	HashMapOrderRepository orderRepository() {
		return new HashMapOrderRepository();
	}

	@Bean
	OrderFactory orderFactory() {
		return new OrderFactory();
	}

	@Bean
	BasicDeliveryTimeServiceImpl deliveryTimeService() {
		BasicDeliveryTimeServiceImpl basicDeliveryTimeServiceImpl = new BasicDeliveryTimeServiceImpl();
		basicDeliveryTimeServiceImpl.setStrategy(strategy);
		return basicDeliveryTimeServiceImpl;

	}

	@Bean
	@Autowired
	BasicDeliveryTimeServiceImpl deliveryTimeService(DeliveryTimeStrategy strategy) {
		BasicDeliveryTimeServiceImpl basicDeliveryTimeServiceImpl = new BasicDeliveryTimeServiceImpl();
		basicDeliveryTimeServiceImpl.setStrategy(strategy);
		return basicDeliveryTimeServiceImpl;

	}

	// @Bean
	// SimpleMessageTemplateService messageTemplate() {
	// return new SimpleMessageTemplateService();
	// }

	@Bean
	TimeService timeService() {
		return new TimeService();
	}

	@Bean
	SimpleDeliveryTimeStrategy strategy() {
		return new SimpleDeliveryTimeStrategy();
	}

	@Bean
	DeliveryTemplate deliveryTemplate() {
		return new DeliveryTemplate();
	}

	@Bean
	OrderCancelledTemplate cancelledDeliveryTemplate() {
		return new OrderCancelledTemplate();
	}

}
