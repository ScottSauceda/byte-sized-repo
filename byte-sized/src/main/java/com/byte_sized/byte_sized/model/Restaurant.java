package com.byte_sized.byte_sized.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name="restaurant_name", length=100)
    private String restaurantName;

    @Column(name="restaurant_phone")
    private Integer restaurantPhone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_owner_id")
    private User owner;

//    @OneToMany
//    @JoinTable(name = "restaurant_images",
//            joinColumns = @JoinColumn(name = "images_id"),
//            inverseJoinColumns = @JoinColumn(name = "restaurants_id")
//    )
//    private List<Image> images;
}