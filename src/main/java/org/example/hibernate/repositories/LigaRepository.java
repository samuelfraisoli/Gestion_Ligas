package org.example.hibernate.repositories;

import org.example.hibernate.SessionFactoryManager;
import org.example.hibernate.entities.Liga;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LigaRepository {
    public void guardarLiga(Liga liga) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(liga);
        session.getTransaction().commit();
    }

    public void actualizarLiga(long id, String propiedad, Object valor) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("UPDATE Liga SET " + propiedad + " = :valor WHERE id = :id")
                .setParameter("valor", valor)
                .setParameter("id", id);

        query.executeUpdate();
        session.getTransaction().commit();
    }

    public ArrayList<Liga> leerLiga(long id) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Liga WHERE id = : id")
                .setParameter("id", id);
        ArrayList<Liga> ligas = (ArrayList<Liga>) query.list();
        session.getTransaction().commit();
        return ligas;
    }

    public ArrayList<Liga> leerLiga() {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Liga");

        ArrayList<Liga> ligas = (ArrayList<Liga>) query.list();
        session.getTransaction().commit();
        return ligas;

    }

    public void eliminarLiga(Liga liga) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(liga);
        session.getTransaction().commit();
    }
}

