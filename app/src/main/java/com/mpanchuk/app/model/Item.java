package com.mpanchuk.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
@Builder
public class Item implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "item_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @ManyToMany
    @JoinTable(
            name = "item_cities",
            joinColumns = @JoinColumn(
                    name = "item_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cities_id"
            )
    )
    @JsonIgnoreProperties("items")
    private Set<City> cities;
}
