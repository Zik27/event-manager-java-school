package ru.dbtc.event_history.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Builder
public class UserEventHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int eventId;
    private int score;

    public UserEventHistory(int userId, int eventId){
        this.userId = userId;
        this.eventId = eventId;
    }

}
