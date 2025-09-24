package com.example.citybreak.city.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "City name is required")
    private String name;

    @NotBlank(message = "Country is required")
    private String country;

    @Size(max = 1000, message = "Details should be less than 1000 characters")
    @Column(length = 2000)
    private String details;
}
