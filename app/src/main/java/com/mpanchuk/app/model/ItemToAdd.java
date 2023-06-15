package com.mpanchuk.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "item_to_add")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ItemToAdd implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @ManyToMany
    @JoinTable(
            name = "item_cities_to_add",
            joinColumns = @JoinColumn(
                    name = "item_to_add_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cities_id"
            )
    )
    @JsonIgnoreProperties("cities")
    @ToString.Exclude
    private Set<City> cities;

}
