package com.test.dao;

import com.test.entity.Channel;
import com.test.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ChannelDao {

    public Channel getById(Integer id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.get(Channel.class, id);
    }

    public List<Channel> getAll(){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from Channel", Channel.class).list();
    }

    public Channel add(Channel channel){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(channel);
        session.getTransaction().commit();
        session.close();
        return channel;
    }

    public Channel update(Channel newChannel, Integer id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Channel channel = getById(id);
        channel.setName(newChannel.getName());
        channel.setSubscribers(newChannel.getSubscribers());
        session.update(channel);
        session.getTransaction().commit();
        session.close();
        return channel;
    }

    public void delete(Integer id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.remove(getById(id));
        session.getTransaction().commit();
        session.close();
    }
}
