package com.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
   public final static SessionFactory sessionFactory = new Configuration().configure()
            .buildSessionFactory();
}
