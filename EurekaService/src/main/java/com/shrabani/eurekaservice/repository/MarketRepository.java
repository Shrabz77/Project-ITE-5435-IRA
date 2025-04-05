package com.shrabani.eurekaservice.repository;

import com.shrabani.eurekaservice.model.Market;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends MongoRepository<Market, String> {
}
