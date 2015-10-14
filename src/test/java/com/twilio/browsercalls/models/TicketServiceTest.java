package com.twilio.browsercalls.models;

import com.twilio.browsercalls.lib.AppSetup;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TicketServiceTest {
  private static EntityManagerFactory emFactory;
  private static EntityManager em;
  private static TicketService service;

  @BeforeClass
  public static void createService() {
    AppSetup appSetup = new AppSetup();
    emFactory = appSetup.getEntityManagerFactory();
    em = emFactory.createEntityManager();
    service = new TicketService(em);
  }

  @AfterClass
  public static void tearDown() throws Exception {
    if (em != null) {
      em.close();
    }
    if (emFactory != null) {
      emFactory.close();
    }
  }

  @Before
  public void cleanUp() {
    service.deleteAll();
  }

  @Test
  public void testCreate() {
    assertThat(service.count(), is(0L));

    Ticket ticket =
        new Ticket("Mario", "+593999012345", "Test description", new Date());
    service.create(ticket);

    assertThat(service.count(), is(1L));
  }

  @Test
  public void testDeleteAll() {
    Ticket ticket =
        new Ticket("Mario", "+593999012345", "Test description", new Date());
    service.create(ticket);

    Ticket ticket2 =
        new Ticket("Mario2", "+593999012345", "Test description", new Date());
    service.create(ticket2);

    assertThat(service.count(), is(2L));

    service.deleteAll();

    assertThat(service.count(), is(0L));
  }

  @Test
  public void testFindAll() {
    Ticket ticket =
        new Ticket("Mario", "+593999012345", "Test description", new Date());
    service.create(ticket);

    Ticket ticket2 =
        new Ticket("Mario2", "+593999012345", "Test description", new Date());
    service.create(ticket2);

    List<Ticket> result = service.findAll();

    assertThat(result.size(), is(2));
  }

  @Test
  public void testCount() {
    Ticket ticket =
        new Ticket("Mario", "+593999012345", "Test description", new Date());
    service.create(ticket);

    assertThat(service.count(), is(1L));
  }
}
