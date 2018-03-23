package com.voidenterprise.realtor.models;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.*;
import com.voidenterprise.realtor.models.request.RealtorRequest;
import lombok.*;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Table("clients")
@Data
public class Client implements Serializable {

    @Getter(onMethod = @__( @JsonInclude))
    @Setter(onMethod = @__( @JsonIgnore))
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private Date dateOfBirth;

    @CassandraType(type = DataType.Name.UUID)
    private UUID realtorid;

    @Override
    public String toString() {
        return String.format("Realtor[id=%s, firstName='%s', lastName='%s', email='%s']", this.id,
                this.firstName, this.lastName, this.email);
    }

}
