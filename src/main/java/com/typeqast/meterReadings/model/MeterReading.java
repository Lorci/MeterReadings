package com.typeqast.meterReadings.model;

import com.typeqast.meterReadings.util.YearAttributeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.Objects;

@Entity
@Table(name = "meter_reading", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"year", "month", "client_id"})
})
public class MeterReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "year")
    @Convert(converter = YearAttributeConverter.class)
    private Year year;

    @Column(name = "month")
    private Month month;

    @Column(name = "value")
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterReading that = (MeterReading) o;
        return Objects.equals(year, that.year) &&
                month == that.month &&
                Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, client);
    }

    @Override
    public String toString() {
        return "MeterReading{" +
                "year=" + year +
                ", month=" + month +
                ", value=" + value +
                '}';
    }
}
