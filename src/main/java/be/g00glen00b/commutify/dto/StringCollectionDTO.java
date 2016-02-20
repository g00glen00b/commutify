package be.g00glen00b.commutify.dto;

import java.util.List;

public class StringCollectionDTO {
    private int offset;
    private int limit;
    private long totalResults;
    private List<String> results;

    public static class Builder {
        private int offset;
        private int limit;
        private long totalResults;
        private List<String> results;

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

        public Builder results(List<String> results) {
            this.results = results;
            return this;
        }

        public StringCollectionDTO build() {
            return new StringCollectionDTO(this);
        }
    }

    public StringCollectionDTO() {
    }

    private StringCollectionDTO(Builder builder) {
        this.offset = builder.offset;
        this.limit = builder.limit;
        this.results = builder.results;
        this.totalResults = builder.totalResults;
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

    public List<String> getResults() {
        return results;
    }
    public void setResults(List<String> results) {
        this.results = results;
    }
}
