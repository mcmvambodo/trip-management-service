package com.doit_well.trip_service.entity;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Role {
    USER("USER"),
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    Role(String value){
    }
}
