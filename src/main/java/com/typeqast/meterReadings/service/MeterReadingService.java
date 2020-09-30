package com.typeqast.meterReadings.service;

import com.typeqast.meterReadings.model.Client;
import com.typeqast.meterReadings.model.MeterReading;
import com.typeqast.meterReadings.repository.ClientRepository;
import com.typeqast.meterReadings.repository.MeterReadingRepository;
import com.typeqast.meterReadings.util.MeterReadingExistsException;
import com.typeqast.meterReadings.util.NoSuchClientException;
import com.typeqast.meterReadings.util.NoSuchMeterReadingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Service
public class MeterReadingService {

    @Autowired
    MeterReadingRepository meterReadingRepository;

    @Autowired
    ClientRepository clientRepository;

    public Client getClientByAddress(String addressStreetName, String addressStreetNumber, String addressCity) throws NoSuchClientException {
        return clientRepository.findClientByAddress(addressStreetName, addressStreetNumber, addressCity).orElseThrow(NoSuchClientException::new);
    }

    public List<MeterReading> getMeterReadingsByClientAndYear(String addressStreetName, String addressStreetNumber, String addressCity,
                                                              Year year) throws NoSuchClientException, NoSuchMeterReadingException {
        Client client = getClientByAddress(addressStreetName, addressStreetNumber, addressCity);

        return meterReadingRepository.findMeterReadingsByClientAndYear(client, year);

    }

    public MeterReading getMeterReadingByClientAndYearAndMonth(String addressStreetName, String addressStreetNumber, String addressCity,
                                                               Year year, Month month) throws NoSuchClientException, NoSuchMeterReadingException {

        Client client = getClientByAddress(addressStreetName, addressStreetNumber, addressCity);
        return meterReadingRepository.findMeterReadingByClientAndYearAndMonth(client, year, month).orElseThrow(NoSuchMeterReadingException::new);
    }

    public MeterReading insertMeterReading(MeterReading meterReading) throws MeterReadingExistsException {

        MeterReading meterReadingExisting = meterReadingRepository.findMeterReadingByClientAndYearAndMonth(meterReading.getClient(), meterReading.getYear(), meterReading.getMonth()).orElse(null);
        if (meterReadingExisting != null) {
            throw new MeterReadingExistsException();
        }
        return meterReadingRepository.save(meterReading);
    }

}
