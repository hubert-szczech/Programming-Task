package main.java.com.data;

import java.util.Objects;

public class Data {
   private String country;
   private String capital;

    public Data() {
    }

    public Data(String country, String capital) {
        this.country = country;
        this.capital = capital;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }


    @Override
    public String toString() {
        return "[Data{" +
                "country='" + country + '\'' +
                ", capital='" + capital + '\'' +
                "}]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return Objects.equals(getCountry(), data.getCountry()) &&
                Objects.equals(getCapital(), data.getCapital());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCapital());
    }
}
