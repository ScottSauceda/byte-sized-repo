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
@IdClass(AssignedRoleId.class)
@Table(name = "assigned_user_role")
public class AssignedRole {
    @Id
    @Column(name="roles_id")
    private Integer rolesId;

    @Id
    @Column(name="users_id")
    private Integer usersId;
}