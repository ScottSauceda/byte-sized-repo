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
@Table(name = "image_types")
public class ImageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_type_id", nullable = false)
    private int imageTypeId;

    @Column(name = "image_type_name", length = 15)
    private String imageTypeName;
}
