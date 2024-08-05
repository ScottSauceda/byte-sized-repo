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
@IdClass(AssignedImageTypeId.class)
@Table(name = "assigned_image_type")
public class AssignedImageType {

    @Id
    @Column(name = "images_type_id")
    private Integer imagesTypeId;

    @Id
    @Column(name = "images_id")
    private Integer imagesId;
}