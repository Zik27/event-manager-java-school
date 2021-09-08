package ru.dbtc.user_service.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserEntity {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private int birthYear;
    private String city;
}
