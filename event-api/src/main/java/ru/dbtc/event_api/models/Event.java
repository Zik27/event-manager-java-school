package ru.dbtc.event_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "events")
public class Event {
    @Id
    private Integer id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Keyword)
    private List<String> categories;

    @Field(type = FieldType.Short)
    private int ageLimit;

    @Field(type = FieldType.Text)
    private String url;

//    @Field(type = FieldType.Boolean)
//    private boolean isFree;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Date)
    private Date startEvent;

    @Field(type = FieldType.Date)
    private Date endEvent;

    @Field(type = FieldType.Keyword)
    private String country;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Text)
    private String address;
}

