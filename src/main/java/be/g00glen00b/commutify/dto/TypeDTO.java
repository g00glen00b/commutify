package be.g00glen00b.commutify.dto;

import java.math.BigDecimal;

public class TypeDTO {
    private Long id;
    private String name;
    private BigDecimal emission;

    public TypeDTO(Long id, String name, BigDecimal emission) {
        this.id = id;
        this.name = name;
        this.emission = emission;
    }

    public TypeDTO() {
    }

    public Long getId() {
        return id;
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
}
