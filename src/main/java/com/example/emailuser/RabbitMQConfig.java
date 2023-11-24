package com.example.emailuser;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	private final CachingConnectionFactory cachingConnectionFactory;

	public RabbitMQConfig(CachingConnectionFactory connectionFactory) {
		this.cachingConnectionFactory = connectionFactory;
	}

	@Bean
	public Queue userQueue() {
		return new Queue("user-email", true);
	}
	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter message) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
		rabbitTemplate.setMessageConverter(message);
		return rabbitTemplate;
	}

//	@Bean
//	public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(
//			ConnectionFactory connectionFactory,
//			MessageConverter messageConverter
//	) {
//		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory);
//		factory.setMessageConverter(messageConverter);
//		return factory;
//	}


}

