package com.byte_sized.byte_sized.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;

    @Convert(disableConversion = true)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @Column(name="review_rating")
    private int reviewRating;

    @Column(name="review_title", length = 100)
    private String reviewTitle;

    @Column(name="review_text", length = 255)
    private String reviewText;

    @Column(name="review_restaurant_id")
    private int reviewRestaurantId;

    @Column(name="review_user_id")
    private int reviewUserId;
}
