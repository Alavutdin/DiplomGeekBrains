package com.geekBrains.diplom.API.service;

import com.geekBrains.diplom.API.dto.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> getTicketsByNumClient(String numClient);
    List<TicketDto> createNewTickets(Long seanceId, Long[] places);
    void deleteTicket(Long ticketId);

}