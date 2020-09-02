package com.example.definitiongenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "circon")
@NoArgsConstructor
public class ConnectionProperties {

    private final Map<String, User> users = new HashMap<>();

    @Data
    public static class User {
        private String username;
        private String password;
        private String permission;
    }
}
