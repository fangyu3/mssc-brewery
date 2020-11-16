package com.fangyu3.msscbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private UUID id;
    private String name;

    public static CustomerDtoBuilder builder() {
        return new CustomerDtoBuilder();
    }

    public static class CustomerDtoBuilder {
        private UUID id;
        private String name;

        public CustomerDtoBuilder() {
            ;
        }

        public CustomerDtoBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CustomerDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerDto build() {
            return new CustomerDto(id,name);
        }
    }
}
