package be.g00glen00b.commutify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "car_emission")
public class CarModel {
    @Id
    @Column
    private String model;
    @ManyToOne
    @JoinColumn(name = "manufacturer")
    private CarManufacturer manufacturer;
    @OneToMany(mappedBy = "model")
    private List<CarType> types;

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public CarManufacturer getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(CarManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<CarType> getTypes() {
        return types;
    }
    public void setTypes(List<CarType> types) {
        this.types = types;
    }
}
