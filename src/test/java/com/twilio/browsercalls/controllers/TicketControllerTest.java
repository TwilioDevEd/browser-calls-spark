package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.models.Ticket;
import com.twilio.browsercalls.models.TicketService;
import org.junit.Test;
import spark.Request;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TicketControllerTest {
  @Test
  public void createTicketSuccessTest() {
    TicketService mockTicketService = mock(TicketService.class);
    Request mockRequest = mock(Request.class);
    TicketController controller = new TicketController(mockTicketService);

    when(mockRequest.queryParams("name")).thenReturn("Mario");
    when(mockRequest.queryParams("phone_number")).thenReturn("+5551234567");
    when(mockRequest.queryParams("description")).thenReturn("Some description");

    Map params = controller.createTicket(mockRequest);

    verify(mockTicketService).create(any(Ticket.class));
    assertThat(params.get("notice"), is("Ticket created successfully"));
  }

  @Test
  public void invalidTicketTest() {
    TicketService mockTicketService = mock(TicketService.class);
    Request mockRequest = mock(Request.class);
    TicketController controller = new TicketController(mockTicketService);

    when(mockRequest.queryParams("name")).thenReturn("Mario");
    when(mockRequest.queryParams("phone_number")).thenReturn("");
    when(mockRequest.queryParams("description")).thenReturn("Some description");

    Map params = controller.createTicket(mockRequest);

    verify(mockTicketService, never()).create(any(Ticket.class));
    assertThat(params.get("error"), is(true));
  }
}
