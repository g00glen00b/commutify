package be.g00glen00b.commutify.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "profile")
public class CommutifyProfile {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String name;
    @Column(name = "avg_km_day")
    private BigDecimal averageKmDay;
    @Column(name = "emission")
    private BigDecimal emission;
    @OneToMany(mappedBy = "profile")
    private List<CommutifyEntry> entries;

    public static class Builder {
        private Long id;
        private String email;
        private String password;
        private String firstName;
        private String name;
        private BigDecimal averageKmDay;
        private BigDecimal emission;
        private List<CommutifyEntry> entries;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder averageKmDay(BigDecimal averageKmDay) {
            this.averageKmDay = averageKmDay;
            return this;
        }

        public Builder emission(BigDecimal emission) {
            this.emission = emission;
            return this;
        }

        public Builder entries(List<CommutifyEntry> entries) {
            this.entries = entries;
            return this;
        }

        public CommutifyProfile build() {
            return new CommutifyProfile(this);
        }
    }

    public CommutifyProfile() {
    }

    private CommutifyProfile(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.name = builder.name;
        this.averageKmDay = builder.averageKmDay;
        this.emission = builder.emission;
        this.password = builder.password;
        this.entries = builder.entries;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAverageKmDay() {
        return averageKmDay;
    }
    public void setAverageKmDay(BigDecimal averageKmDay) {
        this.averageKmDay = averageKmDay;
    }

    public BigDecimal getEmission() {
        return emission;
    }
    public void setEmission(BigDecimal emission) {
        this.emission = emission;
    }

    public List<CommutifyEntry> getEntries() {
        return entries;
    }
    public void setEntries(List<CommutifyEntry> entries) {
        this.entries = entries;
    }
}
