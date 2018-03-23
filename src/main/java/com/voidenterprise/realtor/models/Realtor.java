package com.voidenterprise.realtor.models;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.voidenterprise.realtor.models.request.RealtorRequest;
import lombok.*;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Table("realtor")
@Data
public class Realtor implements Serializable {

    @Getter(onMethod = @__( @JsonInclude))
    @Setter(onMethod = @__( @JsonIgnore))
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    public Realtor(RealtorRequest realtorRequest) {
        this.id = UUID.randomUUID();
        this.firstName = realtorRequest.getFirstName();
        this.lastName = realtorRequest.getLastName();
        this.email = realtorRequest.getEmail();
    }


    @Override
    public String toString() {
        return String.format("Realtor[id=%s, firstName='%s', lastName='%s']", this.id,
                this.firstName, this.lastName);
    }

}
