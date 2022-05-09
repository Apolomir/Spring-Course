package com.epam.springcore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by maksym_govorischev.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;
    public enum Category {STANDARD, PREMIUM, BAR}

}
