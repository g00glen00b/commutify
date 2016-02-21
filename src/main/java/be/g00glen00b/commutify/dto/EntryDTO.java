package be.g00glen00b.commutify.dto;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class EntryDTO {
    private Long id;
    private TypeDTO type;
    private BigDecimal distance;
    private DateTime date;

    public static class Builder {
        private Long id;
        private TypeDTO type;
        private BigDecimal distance;
        private DateTime date;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder type(TypeDTO type) {
            this.type = type;
            return this;
        }

        public Builder distance(BigDecimal distance) {
            this.distance = distance;
            return this;
        }

        public Builder date(DateTime date) {
            this.date = date;
            return this;
        }

        public EntryDTO build() {
            return new EntryDTO(this);
        }
    }

    public EntryDTO() {
    }

    private EntryDTO(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.distance = builder.distance;
        this.date = builder.date;
    }

    public Long getId() {
        return id;
    }

    public TypeDTO getType() {
        return type;
    }
    public void setType(TypeDTO type) {
        this.type = type;
    }

    public BigDecimal getDistance() {
        return distance;
    }
    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public DateTime getDate() {
        return date;
    }
    public void setDate(DateTime date) {
        this.date = date;
    }
}
