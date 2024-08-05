package com.byte_sized.byte_sized.data;

import com.byte_sized.byte_sized.model.RestaurantImage;
import com.byte_sized.byte_sized.model.Review;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInformation {
    public Integer restaurantId;
    public String restaurantName;
    public Integer restaurantPhone;
    public Integer locationId;
    public Integer ownerId;

    // location <-- matched from restaurant_location_id
    public String location_name;
    public String address;
    public String city;
    public String state;
    public Integer zipCode;

    // user  <-- matched from restaurant_owner_id
    private String ownerName;

    // matches to restaurant reviews
    private List<Review> restaurantReviews;

    // matches to restaurant images
    private List<RestaurantImage> restaurantImages;

}