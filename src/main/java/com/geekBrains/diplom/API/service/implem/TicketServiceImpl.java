package com.geekBrains.diplom.API.service.implem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekBrains.diplom.API.dto.TicketDto;
import com.geekBrains.diplom.API.dto.factory.TicketDtoFactory;
import com.geekBrains.diplom.API.exception.NotFoundException;
import com.geekBrains.diplom.API.service.TicketService;
import com.geekBrains.diplom.STORE.entity.ClientEntity;
import com.geekBrains.diplom.STORE.entity.SeanceEntity;
import com.geekBrains.diplom.STORE.entity.SeancePlace;
import com.geekBrains.diplom.STORE.repository.ClientRepository;
import com.geekBrains.diplom.STORE.repository.SeancePlaceRepository;
import com.geekBrains.diplom.STORE.repository.SeanceRepository;

@Service
public class TicketServiceImpl implements TicketService{

    private final SeancePlaceRepository seancePlaceDao;
    private final ClientRepository clientDao;
    private final TicketDtoFactory ticketDtoFactory;
    private final SeanceRepository seanceDao;


    @Autowired
    public TicketServiceImpl(SeancePlaceRepository seancePlaceDao, ClientRepository clientDao,
                             TicketDtoFactory ticketDtoFactory, SeanceRepository seanceDao) {
        super();
        this.seancePlaceDao = seancePlaceDao;
        this.clientDao = clientDao;
        this.ticketDtoFactory = ticketDtoFactory;
        this.seanceDao = seanceDao;
    }

    @Override
    public List<TicketDto> getTicketsByNumClient(String numClient) {
        ClientEntity client = findClientByNumClient(numClient);
        return ticketDtoFactory
                .createListTicketDto(
                        seancePlaceDao.findAllByClient(client));
    }

    @Override
    public List<TicketDto> createNewTickets(Long seanceId, Long[] places) {
        seanceDao
                .findBySeanceIdAndStartedAtAfter(seanceId, LocalDateTime.now()).orElseThrow(()->
                        new NotFoundException(
                                String.format(
                                        "Сеанс уже начался или его не существует \"%s\" ",
                                        seanceId)));
        String numClient = UUID.randomUUID().toString().substring(0, 8);
        ClientEntity client = clientDao.saveAndFlush(new ClientEntity(numClient));
        List<SeancePlace> tickets = new ArrayList<>();
        Arrays
                .stream(places)
                .map(this::findSeancePlaceByIdAndReserved)
                .forEach((t)->{
                    t.setClient(client);
                    t.setReserved(true);
                    tickets.add(seancePlaceDao.saveAndFlush(t));
                });
        return ticketDtoFactory.createListTicketDto(tickets);
    }

    @Override
    @Transactional
    public void deleteTicket(Long ticketId) {
        SeancePlace ticket = findSeancePlaceById(ticketId);
        ticket.setReserved(false);
        ticket.setClient(null);
    }

    private ClientEntity findClientByNumClient(String numClient) {
        return clientDao.findByNumClient(numClient).orElseThrow(()->
                new NotFoundException(
                        String.format("Клиент с Nomber \"%s\" не найден", numClient)));
    }

    private SeancePlace findSeancePlaceById(Long ticketId) {
        return seancePlaceDao.findById(ticketId).orElseThrow(()->
                new NotFoundException(
                        String.format("Билет с ID \"%s\" не найден", ticketId)));
    }

    private SeancePlace findSeancePlaceByIdAndReserved(Long id) {
        return seancePlaceDao.findByIdAndReserved(id, false).orElseThrow(()->
                new NotFoundException(
                        String.format("Место с ID\"%s\" не найдено или оно уже забронировано",
                                id)));
    }

}
