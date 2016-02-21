package be.g00glen00b.commutify.entity;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "commutify_entry")
public class CommutifyEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CommutifyType type;
    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
        @Parameter(name = "databaseZone", value = "UTC"),
        @Parameter(name = "javaZone", value = "UTC")
    })
    private DateTime date;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private CommutifyProfile profile;
    @Column
    private BigDecimal km;
    @Column(name = "normal_emission")
    private BigDecimal emission;

    public static class Builder {
        private Long id;
        private CommutifyType type;
        private DateTime date;
        private CommutifyProfile profile;
        private BigDecimal km;
        private BigDecimal emission;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder type(CommutifyType type) {
            this.type = type;
            return this;
        }

        public Builder date(DateTime date) {
            this.date = date;
            return this;
        }

        public Builder profile(CommutifyProfile profile) {
            this.profile = profile;
            return this;
        }

        public Builder km(BigDecimal km) {
            this.km = km;
            return this;
        }

        public Builder emission(BigDecimal emission) {
            this.emission = emission;
            return this;
        }

        public CommutifyEntry build() {
            return new CommutifyEntry(this);
        }
    }

    public CommutifyEntry() {
    }

    private CommutifyEntry(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.emission = builder.emission;
        this.profile = builder.profile;
        this.km = builder.km;
        this.type = builder.type;
    }

    public Long getId() {
        return id;
    }

    public CommutifyType getType() {
        return type;
    }
    public void setType(CommutifyType type) {
        this.type = type;
    }

    public DateTime getDate() {
        return date;
    }
    public void setDate(DateTime date) {
        this.date = date;
    }

    public CommutifyProfile getProfile() {
        return profile;
    }
    public void setProfile(CommutifyProfile profile) {
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
