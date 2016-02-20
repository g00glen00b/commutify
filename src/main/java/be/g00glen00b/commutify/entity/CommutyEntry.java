package be.g00glen00b.commutify.entity;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "commuty_entry")
public class CommutyEntry {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CommutyType type;
    @Column
    private DateTime date;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @Column
    private BigDecimal km;
    @Column
    private BigDecimal emission;

    public Long getId() {
        return id;
    }

    public CommutyType getType() {
        return type;
    }
    public void setType(CommutyType type) {
        this.type = type;
    }

    public DateTime getDate() {
        return date;
    }
    public void setDate(DateTime date) {
        this.date = date;
    }

    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public BigDecimal getKm() {
        return km;
    }
    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public BigDecimal getEmission() {
        return emission;
    }
    public void setEmission(BigDecimal emission) {
        this.emission = emission;
    }
}
