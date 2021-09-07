package ru.dbtc.event_history.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//названия колонок
public class UserEventHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userId;
    private int eventId;
    private int score;

    public UserEventHistory(String userId, int eventId){
        this.userId = userId;
        this.eventId = eventId;
    }
}
