package com.mikhail.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Type type;

    private String name;

    public enum Type {
        CUSTOMER, SELLER
    }
}
