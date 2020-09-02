package com.example.definitiongenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "circon")
@NoArgsConstructor
public class ExchangeProperties {
    private Map<String, RabbitExchange> exchanges;
}

@Data
@Component
@ConfigurationProperties(prefix = "circon")
@NoArgsConstructor
 class QueueProperties {
    private Map<String, RabbitQueue> queues;
}

@Data
@Component
@ConfigurationProperties(prefix = "circon")
@NoArgsConstructor
 class BindingsProperties {
    private Map<String, RabbitBinding> bindings;
}

