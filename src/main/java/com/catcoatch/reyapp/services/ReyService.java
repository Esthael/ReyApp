package com.catcoatch.reyapp.services;

import com.catcoatch.reyapp.entities.Agenda;
import com.catcoatch.reyapp.entities.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReyService {
    /* ==============================================
                        CONTACT
    ===============================================*/
    // VOIR TOUS LES CONTACTS
    List<Contact> getAllContact();

    // CREER UN NOUVEAU CONTACT
    Contact addNewContact(Contact contact);

    // CHERCHER UN CONTACT PAR SON NOM
    List<Contact> chercherNom(String nom);

    // SUPPRIMER UN CONTACT
    void deleteContact(Long id);

    // MODIFIER UN CONTACT
    Contact updateContact(Long id, Contact updatedContact);

    // VOIR LES RENDEZ-VOUS DU CONTACT SELECTIONNE
    List<Agenda> afficheRdvDuContact(Long id);

    // SELECTIONNER UN CONTACT PAR SON ID
    Contact getContactId(Long id);

    // CHERCHER SI ADRESSE MAIL EXISTE
    Contact getEmail(String email);

    /* ============================================
                         AGENDA
    =============================================*/
    // VOIR TOUS LES RENDEZ-VOUS
    List<Agenda> getAllAgenda();

    // CREER UN NOUVEAU RENDEZ-VOUS ATTRIBUER A UN CONTACT
    Agenda addNewRdv(Long idContact, Agenda agenda);

    // CHERCHER UN DATE DANS LA BASE DE DONNEE
    List<Agenda> ChercheDate(String date_rdv);

    // SUPRIMER UN RENDEZ-VOUS
    void deleteRdv(Long id);

    // CHERCHER UNE DATE ET UNE HEURE
    List<Agenda> chercherDateHeure(String date, String heure);

    // BLOQUER LE CRENEAU HORAIRE
    Agenda bloquerHoraire(Agenda agenda);

    // MODIFIER UN RENDEZ-VOUS SELECTIONNE
    Agenda updateRdv(Long id, Agenda updatedAgenda);
}
