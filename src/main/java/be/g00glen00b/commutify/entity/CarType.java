package be.g00glen00b.commutify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "car_emission")
public class CarType {
    @Id
    @Column(name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "model")
    private CarModel model;
    @Column
    private BigDecimal emission;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public CarModel getModel() {
        return model;
    }
    public void setModel(CarModel model) {
        this.model = model;
    }

    public BigDecimal getEmission() {
        return emission;
    }
    public void setEmission(BigDecimal emission) {
        this.emission = emission;
    }
}
