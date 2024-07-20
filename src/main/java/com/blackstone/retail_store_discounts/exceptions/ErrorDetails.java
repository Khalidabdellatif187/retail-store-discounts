package com.blackstone.retail_store_discounts.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {


    private LocalDateTime timestamp;
    private String message;
    private String details;

}
