package com.example.demo.service;

import com.example.demo.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    public Optional<Event> getEventById(Long id);
    public List<Event> getAllEvents();
    public void saveEvent(Event event) throws Exception;
    public Integer total(String by) throws Exception;
    public List<Event> top3(String by) throws Exception;

}
