package ru.dbtc.event_history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEventHistoryDto {
    private int id;
    private int userId;
    private int eventId;
    private int score;

    public UserEventHistoryDto(int id, int userId, int eventId){
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }
}
