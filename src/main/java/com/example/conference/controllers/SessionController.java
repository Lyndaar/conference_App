package com.example.conference.controllers;

import com.example.conference.models.Session;
import com.example.conference.repositories.SessionRepository;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //Also need to check for children records before deleting
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){
        //Because this is PUT we expect all attributes to be passed in. A PATCH will only need or allow just a portion to be updated
        //TODO: Add validation that all attributesare passed in, otherwise return a 400 bad payload
        Session existingSession = sessionRepository.getOne(id);
        //BeanUtils takes the existing session and copies the incoming session onto it
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
        //saveAndFlush is a jpa method that saves and flush at once  objects doesnt actually gets commited to the database until you flush it
    }

}
