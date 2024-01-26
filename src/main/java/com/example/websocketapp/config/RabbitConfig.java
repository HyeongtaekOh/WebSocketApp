package com.example.websocketapp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Queue queue() {
        /**
         * durable: true -> restart 시에도 queue가 유지되도록 설정
         * 지속성 큐 (Durable Queue)는 RabbitMQ 서버가 재시작되어도 큐가 유지되도록 지정하는 것.
         * 지속성 큐를 사용하면 RabbitMQ 서버가 재시작되어도 큐가 유지되므로 메시지가 손실되지 않는다.
         * 지속성 큐는 높은 신뢰성이 필요한 경우에 유용하지만, 메모리 대신 디스크에 데이터를 쓰고 읽어야 하기 때문에 성능상의 오버헤드가 발생할 수 있다.
         */
        return new Queue(queue, false);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queue.getName() + ".#");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
