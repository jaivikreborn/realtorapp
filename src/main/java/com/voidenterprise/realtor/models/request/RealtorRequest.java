package com.voidenterprise.realtor.models.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RealtorRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;
}
