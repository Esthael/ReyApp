package com.catcoatch.reyapp.repositories;

import com.catcoatch.reyapp.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    // CHERCHER UNE DATE
    List<Agenda> findByDateRdv(String date_rdv);

    // SUPPRIMER LES RENDEZ-VOUS ASSOCIE AU CONTACT SUPPRIME
    void deleteAgendaByContact_IdContact(long id);

    // CHERCHER UNE DATE ET UNE HEURE
    List<Agenda> findByDateRdvAndHeure(String date, String heure);
}
