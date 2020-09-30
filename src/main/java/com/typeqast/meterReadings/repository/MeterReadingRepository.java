package com.typeqast.meterReadings.repository;

import com.typeqast.meterReadings.model.Client;
import com.typeqast.meterReadings.model.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReading, Integer> {

    @Query("SELECT mr FROM MeterReading mr " +
            "where mr.client = :client " +
            "and mr.year like :year " +
            "and mr.month like :month ")
    Optional<MeterReading> findMeterReadingByClientAndYearAndMonth(@Param("client") Client client,
                                         @Param("year") Year year,
                                         @Param("month") Month month);

    @Query("SELECT mr FROM MeterReading mr " +
            "where mr.client like :client " +
            "and mr.year like :year ")
    List<MeterReading> findMeterReadingsByClientAndYear(@Param("client") Client client,
                                                        @Param("year") Year year);
}
