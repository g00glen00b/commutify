package be.g00glen00b.commutify.dto;

public class ProfileDTO {
    private Long id;
    private String firstName;
    private String name;
    private String avatar;

    public static class Builder {
        private Long id;
        private String firstName;
        private String name;
        private String avatar;

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
    }

    public void setId(Long id) {
        this.id = id;
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
}
