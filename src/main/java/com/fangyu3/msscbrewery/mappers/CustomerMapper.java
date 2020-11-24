package com.fangyu3.msscbrewery.mappers;

import com.fangyu3.msscbrewery.domain.Customer;
import com.fangyu3.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
     CustomerDto customerToCustomerDto(Customer customer);

     Customer customerDtoToCustomer(CustomerDto customerDto);
}
