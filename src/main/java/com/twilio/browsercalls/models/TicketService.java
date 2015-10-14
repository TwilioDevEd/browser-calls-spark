package com.twilio.browsercalls.models;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/** Class that provides an abstraction to a Ticket's entity database access */
public class TicketService {
  private EntityManager entityManager;

  public TicketService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Ticket getTicket(Long id) {
    return entityManager.find(Ticket.class, id);
  }

  public void create(Ticket ticket) {
    getTransaction().begin();
    entityManager.persist(ticket);
    getTransaction().commit();
  }

  public void delete(Ticket ticket) {
    getTransaction().begin();
    entityManager.remove(ticket);
    getTransaction().commit();
  }

  @SuppressWarnings("unchecked")
  public List<Ticket> findAll() {
    Query query = entityManager.createQuery("SELECT a FROM Ticket a");
    return query.getResultList();
  }

  public void deleteAll() {
    getTransaction().begin();
    Query query = entityManager.createQuery("DELETE FROM Ticket");
    query.executeUpdate();
    getTransaction().commit();
  }

  public Long count() {
    Query query = entityManager.createQuery("SELECT COUNT(a) FROM Ticket a");
    return (Long) query.getSingleResult();
  }

  private EntityTransaction getTransaction() {
    return entityManager.getTransaction();
  }
}
