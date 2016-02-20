package be.g00glen00b.commutify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "car_emission")
public class CarManufacturer {
    @Id
    @Column
    private String manufacturer;
    @OneToMany(mappedBy = "manufacturer")
    private List<CarModel> models;

    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<CarModel> getModels() {
        return models;
    }
    public void setModels(List<CarModel> models) {
        this.models = models;
    }
}
