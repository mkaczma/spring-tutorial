package com.acme.order.application;

import com.acme.order.delivery.strategy.PizzaTypeDeliveryTimeStrategy;
import com.acme.order.pizza.PizzaType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import com.acme.order.HashMapOrderRepository;
import com.acme.order.OrderFactory;
import com.acme.order.OrderRepository;
import com.acme.order.delivery.BasicDeliveryTimeServiceImpl;
import com.acme.order.delivery.DeliveryTimeService;
import com.acme.order.delivery.TimeService;
import com.acme.order.delivery.strategy.DeliveryTimeStrategy;
import com.acme.order.delivery.strategy.SimpleDeliveryTimeStrategy;
import com.acme.order.notification.DeliveryTemplate;
import com.acme.order.notification.MailSender;
import com.acme.order.notification.OrderCancelledTemplate;

@Slf4j
@Configuration
@ComponentScan(basePackages = { "com.acme.order.pizza", "com.acme.order.notification" })
public class AppConfig {

	@Autowired
	private DeliveryTimeStrategy strategy;

	@Bean
	public MailSender mailSender() {
		return new MailSender();
	}

	@Bean
	OrderRepository orderRepository() {
		return new HashMapOrderRepository();
	}

	@Bean
	OrderFactory orderFactory() {
		return new OrderFactory();
	}

	@Bean
	DeliveryTimeStrategy simpleDeliveryTimeStrategy(){
		log.info(SimpleDeliveryTimeStrategy.class.getSimpleName());
		return new SimpleDeliveryTimeStrategy();
	}

	@Bean
	@Profile("pizzaType")
	@Primary
	DeliveryTimeStrategy pizzaTypeDeliveryTimeStrategy(){
		log.info(PizzaTypeDeliveryTimeStrategy.class.getSimpleName());
		return new PizzaTypeDeliveryTimeStrategy();
	}

	@Bean
	DeliveryTimeService deliveryTimeService() {
		BasicDeliveryTimeServiceImpl basicDeliveryTimeServiceImpl = new BasicDeliveryTimeServiceImpl();
		basicDeliveryTimeServiceImpl.setStrategy(strategy);
		return basicDeliveryTimeServiceImpl;

	}

	@Bean
	@Autowired
	DeliveryTimeService deliveryTimeService(DeliveryTimeStrategy strategy) {
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
	DeliveryTemplate deliveryTemplate() {
		return new DeliveryTemplate();
	}

	@Bean
	OrderCancelledTemplate cancelledDeliveryTemplate() {
		return new OrderCancelledTemplate();
	}

}
