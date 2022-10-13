package learn.rabbitmq.restapi.demo.rabbitmq.mike.moeller.example.config;

import org.springframework.context.annotation.Bean;

// @Configuration, CommandLineRunner interface, Logger
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Queue, TopicExchange, Binding, BindingBuilder, SimpleMessageListenerContainer, ConnectionFactory, MessageListenerAdapter
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

@Configuration
public class RabbitmqTokenConfig {

    private static final Logger _log = LoggerFactory.getLogger(RabbitmqConfig.class);
    public static final String _queue = "dtupay";
    public static final String _topicExchange = "token";
    private static int counter = 0;
    
    // // CommandLineRunner interface implementation, used by @SpringApplication tagged class
    // @Bean 
    // CommandLineRunner fLogRabbitmq(){
    //     return args -> {
               // The class require toString method in case to get string value.
    //         _log.info("New Instance Creation: " + new RabbitmaReceiver("Receiver") + String.valueOf(counter+1)); 
    //     };
    // }
    
    // Assigns Queue(Binding key) TopicExchange with values
    // Package org.springframework.amqp.core 
    // Queue class
    @Bean 
    Queue rabbitmqQueue(){
        // new Queue : (<RoutingKey> : String -> <Durability> : bool -> <Instance> : Queue) -> <Instance> : Queue
        return new Queue(_queue, false);
    }
    // TopicExchange class
    @Bean
    TopicExchange exchange() {
        //new TopicExchange : (<Instance> : String -> <Instance> : TopicExchange) -> <Instance> : TopicExchange
        return new TopicExchange(_topicExchange);
    }
    // Assign values to Binding instacne with utility class BindingBuilder.
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        // bind: ( <UtilityClass/StaticClass> : BindingBuilder -> <Queue> : String -> <ExchangeMode> : String -> <BindingKey> : String
        return BindingBuilder.bind(queue).to(exchange).with("dtupay.token");
    }
    // Create the rabbitmq connection factory
    // Package org.springframework.amqp.rabbit
    // SimpleMessageListenerContainer class, helper class minimally required to be injected with ConnectionFctory Implementation
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory rabbitmqAmqpConnection, 
        MessageListenerAdapter rabbitmqListener) {
        // Assign values to <Instance> : SimpleMessageListenerContainer, ConnectionFactory interface is injected 
        // new SimpleMessageListenerContainer : (() -> <Instance> : SimpleMessageListenerContainer) -> <Instance> : SimpleMessageListenerContainer
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        // setConnectionFactory : (<Instance> : SimpleMessageListenerContainer -> <Instance> : ConnectionFactory) -> <Instance> : SimpleMessageListenerContainer
        container.setConnectionFactory(rabbitmqAmqpConnection);
        // setQueueNames : (<Instance> : SimpleMessageListenerContainer -> <Instance> : String) -> <Instance> : SimpleMessageListenerContainer
        container.setQueueNames(_queue);
        // setQueueNames : (<Instance> : SimpleMessageListenerContainer -> <Instance> : MessageListenerAdapter) -> <Instance> : SimpleMessageListenerContainer
        container.setMessageListener(rabbitmqListener);
        // return the <Instance>
        return container;
    }
    // MessageListenerAdapter class, injected with pojo(delegated clas), Message listener adapter that delegates the handling of messages to target listener methods via reflection(use target class method)
    // RabbitmqMsgConsumer is injected with the @Service 
    @Bean
    MessageListenerAdapter listenerAdapter(RabbitmqMsgConsumer receiver) {
        // new essageListenerAdapter -> (<Instance> : <DelegationClass> -> <Function> : String)
        return new MessageListenerAdapter(receiver, "stdOutput");
    }
}
