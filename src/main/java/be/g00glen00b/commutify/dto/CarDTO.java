package be.g00glen00b.commutify.dto;

import java.math.BigDecimal;

public class CarDTO {
    private String manufacturer;
    private String model;
    private String type;
    private BigDecimal emission;

    public static class Builder {
        private String manufacturer;
        private String model;
        private String type;
        private BigDecimal emission;

        public Builder manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder emission(BigDecimal emission) {
            this.emission = emission;
            return this;
        }

        public CarDTO build() {
            return new CarDTO(this);
        }
    }

    public CarDTO() {
    }

    private CarDTO(Builder builder) {
        this.manufacturer = builder.manufacturer;
        this.model = builder.model;
        this.type = builder.type;
        this.emission = builder.emission;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getEmission() {
        return emission;
    }
    public void setEmission(BigDecimal emission) {
        this.emission = emission;
    }
}
