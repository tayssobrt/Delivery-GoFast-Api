package com.Delivery.GoFast.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep", nullable = false, length = 8)
    @Pattern(regexp = "\\d{8}", message = "CEP need to be 8 digits") //regex
    private Long CEP;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    @Pattern(regexp = "[A-Z]{2}", message = "The state needs to be an abbreviation (SP, RJ, MG)") //regex
    private String state;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "is_default")
    private Boolean isDefault  = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
