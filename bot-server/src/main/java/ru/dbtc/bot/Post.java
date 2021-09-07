package ru.dbtc.bot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private int age;
}
