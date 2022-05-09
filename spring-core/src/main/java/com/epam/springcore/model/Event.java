package com.epam.springcore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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
public class Event {

    private long id;
    private String title;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;

}
