package be.g00glen00b.commutify.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProfileDTO {
    private Long id;
    @NotNull(message = "profile.firstName.notNull")
    @Size(max = 32, message = "profile.firstName.size")
    private String firstName;
    @Size(max = 32, message = "profile.name.size")
    private String name;
    private String avatar;
    private BigDecimal emission;
    private BigDecimal averageKmDay;

    public static class Builder {
        private Long id;
        private String firstName;
        private String name;
        private String avatar;
        private BigDecimal emission;
        private BigDecimal averageKmDay;

        public Builder id(Long id) {
            this.id = id;
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

        public Builder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder emission(BigDecimal emission) {
            this.emission = emission;
            return this;
        }

        public Builder averageKmDay(BigDecimal averageKmDay) {
            this.averageKmDay = averageKmDay;
            return this;
        }

        public ProfileDTO build() {
            return new ProfileDTO(this);
        }
    }

    public ProfileDTO() {
    }

    private ProfileDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.firstName = builder.firstName;
        this.avatar = builder.avatar;
        this.emission = builder.emission;
        this.averageKmDay = builder.averageKmDay;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getEmission() {
        return emission;
    }
    public void setEmission(BigDecimal emission) {
        this.emission = emission;
    }

    public BigDecimal getAverageKmDay() {
        return averageKmDay;
    }
    public void setAverageKmDay(BigDecimal averageKmDay) {
        this.averageKmDay = averageKmDay;
    }
}
