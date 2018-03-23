package com.voidenterprise.realtor.models;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table("restaurants")
@Data
public class Restaurant  implements Serializable {

    @Getter(onMethod = @__( @JsonInclude))
    @Setter(onMethod = @__( @JsonIgnore))
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private String name;

    private String address1;

    private String address2;

    private String city;

    private String province;

    private String zipcode;

    public Restaurant(Restaurant restaurant) {
        this.id = UUID.randomUUID();
        this.name = restaurant.getName();
        this.address1 = restaurant.getAddress1();
        this.address2 = restaurant.getAddress2();
        this.city = restaurant.getCity();
        this.province = restaurant.getProvince();
        this.zipcode = restaurant.getZipcode();
    }

    public Restaurant(String address1,String name,String address2,String city,String province,String zipcode){
        this.id = UUID.randomUUID();
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.zipcode = zipcode;
    }




    @Override
    public String toString() {
        return String.format("Restaurant[id=%s, address1='%s', address2='%s', city='%s', province='%s', zipcode='%s']", this.id,
                this.address1, this.address2, this.city, this.province, this.zipcode);
    }

}
