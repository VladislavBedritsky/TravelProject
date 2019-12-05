package org.htp.ex.model;

import javax.persistence.Embeddable;

@Embeddable
public class TripInfo {

    private Integer amountEconomyPlace;
    private Integer priceEconomyPlace;

    private Integer amountComfortPlace;
    private Integer priceComfortPlace;

    private String modelCar;
    private String driverName;

    public TripInfo() {}

    public TripInfo (
            Integer amountEconomyPlace,
            Integer priceEconomyPlace,
            Integer amountComfortPlace,
            Integer priceComfortPlace,
            String modelCar,
            String driverName) {

        this.amountEconomyPlace = amountEconomyPlace;
        this.amountComfortPlace = amountComfortPlace;
        this.priceComfortPlace = priceComfortPlace;
        this.priceEconomyPlace = priceEconomyPlace;
        this.modelCar = modelCar;
        this.driverName = driverName;
    }

    public Integer getAmountEconomyPlace() {
        return amountEconomyPlace;
    }

    public void setAmountEconomyPlace(Integer amountEconomyPlace) {
        this.amountEconomyPlace = amountEconomyPlace;
    }

    public Integer getPriceEconomyPlace() {
        return priceEconomyPlace;
    }

    public void setPriceEconomyPlace(Integer priceEconomyPlace) {
        this.priceEconomyPlace = priceEconomyPlace;
    }

    public Integer getAmountComfortPlace() {
        return amountComfortPlace;
    }

    public void setAmountComfortPlace(Integer amountComfortPlace) {
        this.amountComfortPlace = amountComfortPlace;
    }

    public Integer getPriceComfortPlace() {
        return priceComfortPlace;
    }

    public void setPriceComfortPlace(Integer priceComfortPlace) {
        this.priceComfortPlace = priceComfortPlace;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

}
