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
@Table("houses")
@Data
public class House implements Serializable {

    @Getter(onMethod = @__( @JsonInclude))
    @Setter(onMethod = @__( @JsonIgnore))
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private String address1;

    private String address2;

    private String city;

    private String province;

    private String zipcode;

    public House(House house) {
        this.id = UUID.randomUUID();
        this.address1 = house.getAddress1();
        this.address2 = house.getAddress2();
        this.city = house.getCity();
        this.province = house.getProvince();
        this.zipcode = house.getZipcode();
    }

    public House(String address1,String address2,String city,String province,String zipcode){
        this.id = UUID.randomUUID();
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.zipcode = zipcode;
    }




    @Override
    public String toString() {
        return String.format("House[id=%s, address1='%s', address2='%s', city='%s', province='%s', zipcode='%s']", this.id,
                this.address1, this.address2, this.city, this.province, this.zipcode);
    }

}
