package ru.dbtc.bot.cache;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private int birthYear;
    private String city;
}
