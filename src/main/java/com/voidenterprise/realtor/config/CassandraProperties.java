package com.voidenterprise.realtor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("spring.data.cassandra")
public class CassandraProperties {

    private String username;

    private String password;

    private String keyspace_name;

    private String contact_points;

    private Integer port;

    private String schema_action;

}
