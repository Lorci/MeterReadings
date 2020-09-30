package com.typeqast.meterReadings.repository;

import com.typeqast.meterReadings.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c FROM Client c " +
            "where c.addressStreetName like :addressStreetName " +
            "and c.addressStreetNumber like :addressStreetNumber " +
            "and c.addressCity like :addressCity ")
    Optional<Client> findClientByAddress(@Param("addressStreetName") String addressStreetName,
                                         @Param("addressStreetNumber") String addressStreetNumber,
                                         @Param("addressCity") String addressCity);
}
