package com.byte_sized.byte_sized.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer locationId;

    @Column(name="location_name", length=45)
    private String locationName;

    @Column(name="address", length=45)
    private String address;

    @Column(name="city", length=45)
    private String city;

    @Column(name="state", length=45)
    private String state;

    @Column(name="zip_code")
    private int zipCode;
}
