package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Event {
    @jakarta.persistence.Id
    Long Id;
    String name;
    Integer cost;
    Integer duration;
}
