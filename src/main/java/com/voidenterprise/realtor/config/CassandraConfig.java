package com.voidenterprise.realtor.config;


import com.datastax.driver.core.PlainTextAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.cassandra.core.keyspace.DropKeyspaceSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {


    @Autowired
    CassandraProperties cassandraProperties;

    @Override
    public SchemaAction getSchemaAction() { return SchemaAction.valueOf(cassandraProperties.getSchema_action()); }

    @Bean
    @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        PlainTextAuthProvider sap = new PlainTextAuthProvider(cassandraProperties.getUsername(), cassandraProperties.getPassword());
        cluster.setContactPoints(cassandraProperties.getContact_points());
        cluster.setPort(cassandraProperties.getPort());
        cluster.setAuthProvider(sap);
        return cluster;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(cassandraProperties.getKeyspace_name());

        return Arrays.asList(specification);
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(cassandraProperties.getKeyspace_name()));
    }

    @Override
    protected String getKeyspaceName() {
        return cassandraProperties.getKeyspace_name();
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.voidenterprise.realtor.models"};
    }
}