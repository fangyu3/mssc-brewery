package com.fangyu3.msscbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private UUID id;

    @NotBlank
    // minimum, maximum for string validation
    @Size(min=3,max=100)
    private String name;
}
