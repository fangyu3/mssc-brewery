package com.fangyu3.msscbrewery.services;

import com.fangyu3.msscbrewery.web.model.BeerDto;
import org.springframework.http.ContentDisposition;

import java.util.UUID;

public interface BeerService {
    public BeerDto getBeerById(UUID beerId);

    public BeerDto saveBeer(BeerDto beerDto);

    public void updateBeer(UUID beerId,BeerDto beerDto);

    public void deleteBeer(UUID beerId);
}
