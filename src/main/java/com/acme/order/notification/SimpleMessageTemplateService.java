package com.acme.order.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class SimpleMessageTemplateService implements MessageTemplateService {

	@Autowired
	private DeliveryTemplate deliveryTemplate;
	@Autowired
	private OrderCancelledTemplate cancelDeliveryTemplate;

	public SimpleMessageTemplateService() {
		this.deliveryTemplate = new DeliveryTemplate();
		this.cancelDeliveryTemplate = new OrderCancelledTemplate();
	}

	@Override
	public DeliveryTemplate getDeliveryTemplate() {
		return deliveryTemplate;
	}

	@Override
	public OrderCancelledTemplate getCancelTemplate() {
		return cancelDeliveryTemplate;
	}

}
