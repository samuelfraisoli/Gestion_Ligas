package org.example.hibernate.repositories;

import org.example.hibernate.SessionFactoryManager;
import org.example.hibernate.entities.Equipo;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EquipoRepository {
    public void guardarEquipo(Equipo equipo) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(equipo);
        session.getTransaction().commit();
    }

    public void actualizarEquipo(long id, String propiedad, Object valor) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("UPDATE Equipo SET " + propiedad + " = :valor WHERE id = :id")
                .setParameter("valor", valor)
                .setParameter("id", id);

        query.executeUpdate();
        session.getTransaction().commit();
    }

    public ArrayList<Equipo> leerEquipos(String propiedad, Object valor) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Equipo WHERE " + propiedad + " = : valor")
                .setParameter("valor", valor);
        ArrayList<Equipo> equipos = (ArrayList<Equipo>) query.list();
        session.getTransaction().commit();
        return equipos;
    }

    public ArrayList<Equipo> leerEquipos() {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Equipo");
        ArrayList<Equipo> equipos = (ArrayList<Equipo>) query.list();
        session.getTransaction().commit();
        return equipos;
    }

    public void eliminarEquipo(Equipo equipo) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(equipo);
        session.getTransaction().commit();
    }
}
