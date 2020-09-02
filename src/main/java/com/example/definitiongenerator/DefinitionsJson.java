package com.example.definitiongenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Setter @Getter
public class DefinitionsJson {
    private String rabbit_version = "3.8.3";
    private String rabbitmq_version = "3.8.3";
    private List<RabbitUser> users = new ArrayList<>();
    private List<RabbitVhost> vhosts = new ArrayList<>();
    private List<RabbitPermission> permissions = new ArrayList<>();
    private List<RabbitTopicPermission> topic_permissions = new ArrayList<>();
    private List<String> parameters = new ArrayList<>();
    private List<String> global_parameters = new ArrayList<>();
    private List<String> policies = new ArrayList<>();
    private List<RabbitExchange> exchanges = new ArrayList<>();
    private List<RabbitQueue> queue = new ArrayList<>();
    private List<RabbitBinding> bindings = new ArrayList<>();
}


@Data
class RabbitExchange{
    private String name = "unknown";
    private String vhost = "/";
    private String type = "fanout";
    private Boolean durable = true;
    private Boolean auto_delete = false;
    private Boolean internal = false;
    private Map<String, String> arguments = new HashMap<>();
}


@Data
class RabbitQueue{
    private String name = "unkown";
    private String vhost = "/";
    private Boolean durable = true;
    private Boolean auto_delete = false;
    private Map<String, String> arguments = new HashMap<>();
}


@Data
class RabbitUser{
    private String name;
    private String password;
    private String tags = "administrator";
}

@Data
class RabbitBinding{
    private String source = "unknown";
    private String vhost = "/";
    private String destination = "unknown";
    private String destination_type = "queue";
    private String routing_key = "*";
    private Map<String, String> arguments = new HashMap<>();
}

@Data
class RabbitPermission{
    private String name;
    private String vhost = "/";
    private String configure = ".*";
    private String write = ".*";
    private String read = ".*";
}

@Data
class RabbitTopicPermission{
    private String user = "guest";
    private String vhost = "/";
    private String exchange;
    private String write = ".*";
    private String read = ".*";
}



@Data
@AllArgsConstructor
class RabbitVhost{
    private String name = "/";
}