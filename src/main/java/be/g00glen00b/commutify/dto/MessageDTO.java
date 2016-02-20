package be.g00glen00b.commutify.dto;

public class MessageDTO {
    private String code;
    private String message;

    public static class Builder {
        private String code;
        private String message;

        public MessageDTO build() {
            return new MessageDTO(this);
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }
    }

    public MessageDTO() {
    }

    private MessageDTO(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
