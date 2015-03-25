package model;

import java.io.Serializable;

public class Country implements Serializable {
    private String name;

    public Country(String name) {
        this.name = name;}

    public Country() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
