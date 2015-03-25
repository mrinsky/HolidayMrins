package model;

import java.io.Serializable;

public class Tradition implements Serializable {
    private Holiday holiday;
    private Country country;
    private String description;

    public Tradition(Holiday holiday, Country country) {
        this.holiday = holiday;
        this.country = country;
        this.description = "";
    }

    public Tradition(Holiday holiday, Country country, String description) {
        this.holiday = holiday;
        this.country = country;
        this.description = description;
    }

    public Tradition() {

    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Holiday getHoliday() {
        return this.holiday;
    }

    public Country getCountry() { return this.country;}

    public String toString() {
        return String.format("%30s%20s",this.holiday.getName(),this.country.getName());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}