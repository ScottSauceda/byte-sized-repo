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
@Table(name = "profiles")
public class Profile {
    @Id
    @Column(name="profile_user_id", unique = true)
    private Integer profileUserId;

    @Column(name="first_name", length=50)
    private String firstName;

    @Column(name="last_name", length=50)
    private String lastName;

    @Column(name="email", length = 50, unique = true)
    private String email;

    @Column(name="phone", length = 50, unique = true)
    private String phone;
}
