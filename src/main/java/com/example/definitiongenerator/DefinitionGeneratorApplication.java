package com.example.definitiongenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DefinitionGeneratorApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DefinitionGeneratorApplication.class, args).close();
    }

    @Autowired
    ConnectionProperties connectionProperties;

    @Autowired
    ExchangeProperties exchangeProperties;

    @Autowired
    QueueProperties queueProperties;

    @Autowired
    BindingsProperties bindingsProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        DefinitionsJson definitionsJson = new DefinitionsJson();
        List<RabbitQueue> queues = new ArrayList<>();
        List<RabbitExchange> exchanges = new ArrayList<>();
        List<RabbitBinding> bindings = new ArrayList<>();
        List<RabbitUser> users = new ArrayList<>();
        List<RabbitPermission> permissions = new ArrayList<>();

        if(exchangeProperties.getExchanges() != null){
            exchangeProperties.getExchanges().forEach((name, exchange) -> {
                RabbitExchange tempExchange = new RabbitExchange();
                tempExchange.setName(exchange.getName());
                if(tempExchange.getDurable() != null) tempExchange.setDurable(exchange.getDurable());
                if(tempExchange.getAuto_delete() != null) tempExchange.setDurable(exchange.getAuto_delete());
                if(tempExchange.getArguments() != null) tempExchange.setArguments(exchange.getArguments());
                if(tempExchange.getVhost() != null) tempExchange.setVhost(exchange.getVhost());
                if(tempExchange.getType() != null) tempExchange.setType(exchange.getType());
                exchanges.add(tempExchange);
            });
        }

        if(queueProperties.getQueues() != null){
            queueProperties.getQueues().forEach((name, queue) -> {
                RabbitQueue tempQueue = new RabbitQueue();
                tempQueue.setName(queue.getName());
                if(tempQueue.getDurable() != null) tempQueue.setDurable(queue.getDurable());
                if(tempQueue.getAuto_delete() != null) tempQueue.setDurable(queue.getAuto_delete());
                if(tempQueue.getArguments() != null) tempQueue.setArguments(queue.getArguments());
                if(tempQueue.getVhost() != null) tempQueue.setVhost(queue.getVhost());
                queues.add(tempQueue);
            });
        }

        if(bindingsProperties.getBindings() != null){
            bindingsProperties.getBindings().forEach((name, binding) -> {
                RabbitBinding tempBinding = new RabbitBinding();
                if(tempBinding.getSource() != null) tempBinding.setSource(binding.getSource());
                if(tempBinding.getDestination() != null) tempBinding.setDestination(binding.getDestination());
                if(tempBinding.getRouting_key() != null) tempBinding.setRouting_key(binding.getRouting_key());
                if(tempBinding.getArguments() != null) tempBinding.setArguments(binding.getArguments());
                if(tempBinding.getDestination_type() != null) tempBinding.setDestination_type(binding.getDestination_type());
                if(tempBinding.getVhost() != null) tempBinding.setVhost(binding.getVhost());
                bindings.add(tempBinding);
            });
        }

        if(connectionProperties.getUsers() != null){
            connectionProperties.getUsers().forEach((name, object) -> {
                RabbitUser tempUser = new RabbitUser();
                tempUser.setName(object.getUsername());
                tempUser.setPassword(object.getPassword());

                RabbitPermission permission = new RabbitPermission();
                permission.setName(object.getUsername());
                permission.setRead(object.getPermission());

                users.add(tempUser);
                permissions.add(permission);
            });
        }

        System.out.println(bindings);
        System.out.println(exchanges);
        System.out.println(permissions);
        System.out.println(queues);
        System.out.println(users);

        // Add two vhosts
        List<RabbitVhost> hosts = Arrays.asList(new RabbitVhost("/"), new RabbitVhost("example-host"));


        definitionsJson.setBindings(bindings);
        definitionsJson.setExchanges(exchanges);
        definitionsJson.setQueue(queues);
        definitionsJson.setPermissions(permissions);
        definitionsJson.setUsers(users);
        definitionsJson.setVhosts(hosts);
        objectMapper.writeValue(new File("definitions.json"), definitionsJson);

        PrintWriter output = new PrintWriter("rabbitmq.conf");
        output.println("management.load_definitions = /etc/rabbitmq/definitions.json");
        output.close();
    }
}
