package com.typeqast.meterReadings.rest;

import com.typeqast.meterReadings.model.Client;
import com.typeqast.meterReadings.model.MeterReading;
import com.typeqast.meterReadings.rest.entity.ClientMeterReadingDto;
import com.typeqast.meterReadings.rest.entity.MeterReadingPerYearDto;
import com.typeqast.meterReadings.rest.entity.MeterReadingPerYearSumDto;
import com.typeqast.meterReadings.service.MeterReadingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;

@RestController
@RequestMapping(value = "/client-meter-readings", produces = {MediaType.APPLICATION_JSON_VALUE })
public class ClientMetersController {

    private static final Logger log = LoggerFactory.getLogger(ClientMetersController.class);

    @Autowired
    private MeterReadingService meterReadingService;

    @GetMapping("")
    public ResponseEntity<MeterReadingPerYearDto> getMeterForClientWithParamYearAndMonth(@RequestParam String addressStreetName,
                                                                                         @RequestParam String addressStreetNumber,
                                                                                         @RequestParam String addressCity,
                                                                                         @NotNull @RequestParam Year year,
                                                                                         @RequestParam (required = false) Month month) throws Exception {

        log.info("Called endpoint GET /client-meter-readings/?addressStreetName=" + addressStreetName + "&addressStreetNumber=" + addressStreetNumber +
                "&addressCity=" + addressCity + "&year=" + year + (month!=null ? "&moth=" + month : ""));

        checkInputParameters(addressStreetName, addressStreetNumber, addressCity, year);

        if (month != null) {

            MeterReading meterReading = meterReadingService.getMeterReadingByClientAndYearAndMonth(addressStreetName,
                    addressStreetNumber, addressCity, year, month);

            MeterReadingPerYearDto meterReadingPerYearDto = new MeterReadingPerYearDto(year, meterReading);

            return new ResponseEntity(meterReadingPerYearDto, HttpStatus.OK);
        }
        else {
            List<MeterReading> meterReadings = meterReadingService.getMeterReadingsByClientAndYear(addressStreetName,
                    addressStreetNumber, addressCity, year);

            if (meterReadings.size() == 0) {
                throw new Exception("There is no meter readings for this year for this client.");
            }

            MeterReadingPerYearDto meterReadingPerYearDto = new MeterReadingPerYearDto(year, meterReadings);

            return new ResponseEntity(meterReadingPerYearDto, HttpStatus.OK);

        }
    }

    @GetMapping("/summary")
    public ResponseEntity<MeterReadingPerYearSumDto> getMetersSummaryForClientWithParamYear(@RequestParam String addressStreetName,
                                                                                            @RequestParam String addressStreetNumber,
                                                                                            @RequestParam String addressCity,
                                                                                            @RequestParam Year year) throws Exception{
        log.info("Called endpoint GET /client-meter-readings/summary/?addressStreetName=" + addressStreetName +
                "&addressStreetNumber=" + addressStreetNumber + "&addressCity=" + addressCity +
                "&year=" + year);


        checkInputParameters(addressStreetName, addressStreetNumber, addressCity, year);

        BigDecimal sumForYear =
                    meterReadingService.getMeterReadingsByClientAndYear(
                            addressStreetName, addressStreetNumber, addressCity,
                            year)
                            .stream()
                            .map(mr -> mr.getValue())
                            .reduce(BigDecimal::add).orElse(new BigDecimal(0));

        MeterReadingPerYearSumDto meterReadingPerYearSumDto = new MeterReadingPerYearSumDto(year, sumForYear);

        return new ResponseEntity<>(meterReadingPerYearSumDto, HttpStatus.OK);
    }

    private void checkInputParameters(String addressStreetName, String addressStreetNumber, String addressCity, Year year) {
        if (addressStreetName == null || addressStreetName.isEmpty() || addressStreetNumber == null || addressStreetNumber.isEmpty()
                || addressCity == null || addressCity.isEmpty() || year == null) {
            String message = "At least one parameter is invalid or not supplied.";
            log.info(message);
            throw new IllegalArgumentException(message);
        }
    }

    @PostMapping("")
    public ResponseEntity<ClientMeterReadingDto> insertMeterForClient(@RequestBody @Valid ClientMeterReadingDto clientMeterReadingDto)
            throws Exception {

        log.info("Called endpoint POST /client-meter-readings/ with Request body + " + clientMeterReadingDto.toString());

        MeterReading mr = meterReadingService.insertMeterReading(convertMeterReadingDtoToMeterReading(clientMeterReadingDto));

       return new ResponseEntity<>(clientMeterReadingDto, HttpStatus.CREATED);
    }

    private MeterReading convertMeterReadingDtoToMeterReading(ClientMeterReadingDto clientMeterReadingDto) throws Exception {
        Client client = meterReadingService.getClientByAddress(clientMeterReadingDto.getAddressStreetName(),
                clientMeterReadingDto.getAddressStreetNumber(), clientMeterReadingDto.getAddressCity());

        MeterReading meterReading = new MeterReading();
        meterReading.setClient(client);
        meterReading.setYear(clientMeterReadingDto.getYear());
        meterReading.setMonth(clientMeterReadingDto.getMonth());
        meterReading.setValue(clientMeterReadingDto.getValue());

        return meterReading;

    }

}
