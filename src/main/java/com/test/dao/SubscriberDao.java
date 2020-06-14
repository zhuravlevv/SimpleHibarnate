package com.test.dao;

import com.test.entity.Subscriber;
import com.test.utils.HibernateSessionFactoryUtil;
import javafx.scene.SubScene;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class SubscriberDao {

    public Subscriber getById(Integer id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.get(Subscriber.class, id);
    }

    public List<Subscriber> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("from Subscriber", Subscriber.class).list();
    }

    public Subscriber add(Subscriber subscriber){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(subscriber);
        session.getTransaction().commit();
        session.close();
        return subscriber;
    }

    public Subscriber update(Subscriber newSubscriber, Integer id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Subscriber subscriber = getById(id);
        subscriber.setName(newSubscriber.getName());
        subscriber.setChannels(newSubscriber.getChannels());
        session.getTransaction().begin();
        session.update(subscriber);
        session.getTransaction().commit();
        session.close();
        return subscriber;
    }

    public void delete(Integer id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.remove(getById(id));
        session.getTransaction().commit();
        session.close();
    }
}
