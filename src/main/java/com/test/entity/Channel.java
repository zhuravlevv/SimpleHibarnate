package com.test.entity;

import com.test.entity.Subscriber;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "channel_subscribers",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )
    private Set<Subscriber> subscribers;

    public Channel() {
        subscribers = new HashSet<Subscriber>();
    }

    public Channel(String name, Set<Subscriber> subscribers) {
        this.name = name;
        if(subscribers==null)
            this.subscribers = new HashSet<Subscriber>();
        else
            this.subscribers = subscribers;
    }

    public void subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
        subscriber.getChannels().add(this);
    }

    public void unsubscribe(Subscriber subscriber){
        subscribers.remove(subscriber);
        subscriber.getChannels().remove(this);
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

    public Set<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
