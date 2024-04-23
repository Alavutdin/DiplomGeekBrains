package com.geekBrains.diplom.STORE.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ticket")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @Column(unique = true)
    private String numClient;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "client")
    private List<SeancePlace> tickets = new ArrayList<>();

    public ClientEntity() {
    }

    public ClientEntity(String numClient) {
        this.numClient = numClient;
    }

    public ClientEntity(String numClient, List<SeancePlace> tickets) {
        this.numClient = numClient;
        this.tickets = tickets;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public List<SeancePlace> getTickets() {
        return tickets;
    }

    public void setTickets(List<SeancePlace> tickets) {
        this.tickets = tickets;
    }


}
