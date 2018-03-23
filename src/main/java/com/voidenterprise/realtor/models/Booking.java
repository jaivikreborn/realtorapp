package com.voidenterprise.realtor.models;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Table("bookings")
@ToString(of={"id", "restaurantId", "bookingDateTime", "persons", "firstName", "lastName","email","phoneNumber"})
@Data
public class Booking implements Serializable {


    @Getter(onMethod = @__(@JsonInclude))
    @Setter(onMethod = @__(@JsonIgnore))
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    @Id
    private UUID id;

    private UUID restaurantId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "EST")
    private Date bookingDateTime;

    private Integer persons;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}


