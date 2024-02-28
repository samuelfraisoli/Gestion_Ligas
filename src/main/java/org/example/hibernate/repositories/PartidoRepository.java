package org.example.hibernate.repositories;

import org.example.hibernate.SessionFactoryManager;
import org.example.hibernate.entities.Partido;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PartidoRepository {
        public void guardarPartido(Partido partido) {
            Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.persist(partido);
            session.getTransaction().commit();
        }

        public void actualizarPartido(long id, String propiedad, Object valor) {
            Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Query query = session.createQuery("UPDATE Partido SET " + propiedad + " = :valor WHERE id = :id")
                    .setParameter("valor", valor)
                    .setParameter("id", id);

            query.executeUpdate();
            session.getTransaction().commit();
        }

        public ArrayList<Partido> leerPartidos(String propiedad, Object valor) {
            Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM Partido WHERE " + propiedad + " = : valor")
                    .setParameter("valor", valor);
            ArrayList<Partido> partidos = (ArrayList<Partido>) query.list();
            session.getTransaction().commit();
            return partidos;
        }

    public ArrayList<Partido> leerPartidos() {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Partido");
        ArrayList<Partido> partidos = (ArrayList<Partido>) query.list();
        session.getTransaction().commit();
        return partidos;
    }

        public void eliminarPartido(Partido partido) {
            Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.delete(partido);
            session.getTransaction().commit();
        }
    }

