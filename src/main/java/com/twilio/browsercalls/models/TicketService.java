package com.twilio.browsercalls.models;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Class that provides an abstraction to a Ticket's entity database access
 */
public class TicketService {
  private EntityManager entityManager;

  public TicketService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Ticket getTicket(Long id) {
    return entityManager.find(Ticket.class, id);
  }

  public void create(Ticket ticket) {
    entityManager.persist(ticket);
  }

  public void delete(Ticket ticket) {
    entityManager.remove(ticket);
  }

  @SuppressWarnings("unchecked")
  public List<Ticket> findAll() {
    Query query = entityManager.createQuery("SELECT a FROM Ticket a");
    return query.getResultList();
  }

  public void deleteAll() {
    Query query = entityManager.createQuery("DELETE FROM Ticket");
    query.executeUpdate();
  }

  public Long count() {
    Query query = entityManager.createQuery("SELECT COUNT(a) FROM Ticket a");
    return (Long) query.getSingleResult();
  }
}
