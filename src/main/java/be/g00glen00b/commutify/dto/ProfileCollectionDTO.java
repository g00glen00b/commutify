package be.g00glen00b.commutify.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProfileCollectionDTO {
    private int offset;
    private int limit;
    private long totalResults;
    private List<ProfileDTO> results;
    private BigDecimal totalSavings;

    public static class Builder {
        private int offset;
        private int limit;
        private long totalResults;
        private List<ProfileDTO> results;
        private BigDecimal totalSavings;

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder totalResults(long totalResults) {
            this.totalResults = totalResults;
            return this;
        }

        public Builder results(List<ProfileDTO> results) {
            this.results = results;
            return this;
        }

        public Builder totalSavings(BigDecimal totalSavings) {
            this.totalSavings = totalSavings;
            return this;
        }

        public ProfileCollectionDTO build() {
            return new ProfileCollectionDTO(this);
        }
    }

    public ProfileCollectionDTO() {
    }

    private ProfileCollectionDTO(Builder builder) {
        this.offset = builder.offset;
        this.limit = builder.limit;
        this.results = builder.results;
        this.totalResults = builder.totalResults;
        this.totalSavings = builder.totalSavings;
    }

    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<ProfileDTO> getResults() {
        return results;
    }
    public void setResults(List<ProfileDTO> results) {
        this.results = results;
    }

    public BigDecimal getTotalSavings() {
        return totalSavings;
    }
    public void setTotalSavings(BigDecimal totalSavings) {
        this.totalSavings = totalSavings;
    }
}
