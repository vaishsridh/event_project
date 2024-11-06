package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultEventService implements EventService {

    @Autowired
    EventRepository eventRepository;


    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void saveEvent(Event event) throws Exception {
        if(event.getId() != null) throw new Exception("not valid");
        eventRepository.save(event);
    }

    @Override
    //TODO: Implement this
    public Integer total(String by) throws Exception {
        if(!by.equalsIgnoreCase("cost")
                && !by.equalsIgnoreCase("duration")) {
            throw new Exception("not valid");
        }
        List<Event> events = getAllEvents();
        Optional<Integer> total = events.stream().map(event -> {
            if(by.equalsIgnoreCase("cost"))
                return event.getCost();
            else return event.getDuration();
        }).reduce(Integer::sum);

        return total.orElse(0);
    }

    @Override
    //TODO: Implement this
    public List<Event> top3(String by) throws Exception {
        if(!by.equalsIgnoreCase("cost")
                && !by.equalsIgnoreCase("duration")) {
            throw new Exception("not valid");
        }
        List<Event> events = getAllEvents();

        List<Event> eventList = events.stream().sorted((e1, e2) -> {
            if(by.equalsIgnoreCase("cost"))
                return e1.getCost().compareTo(e2.getCost());
                 else return e1.getDuration().compareTo(e2.getDuration());
        }).toList();

        return eventList.stream().limit(3).toList();
    }
}
