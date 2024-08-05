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
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "image_name", length = 50)
    private String imageName;

    @Column(name = "image_source", length = 300)
    private String imageSource;

    @Column(name="image_user_id")
    private int imageUserId;

    @OneToOne
    @JoinTable(name = "assigned_image_type",
            joinColumns = @JoinColumn(name = "images_type_id"),
            inverseJoinColumns = @JoinColumn(name = "images_id"))
    private ImageType assignedImageType;


}