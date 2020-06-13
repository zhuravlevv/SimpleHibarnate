package com.test.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subscriber")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "subscribers")
    private Set<Channel> channels;

    public Subscriber() {
        channels = new HashSet<Channel>();
    }

    public Subscriber(String name, Set<Channel> channels) {
        this.name = name;
        if(channels == null)
            this.channels = new HashSet<Channel>();
        else
            this.channels = channels;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }
}
