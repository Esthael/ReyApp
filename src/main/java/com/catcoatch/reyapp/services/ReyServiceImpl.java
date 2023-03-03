package com.catcoatch.reyapp.services;

import com.catcoatch.reyapp.entities.Agenda;
import com.catcoatch.reyapp.entities.Contact;
import com.catcoatch.reyapp.repositories.AgendaRepository;
import com.catcoatch.reyapp.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReyServiceImpl implements ReyService{

    private ContactRepository contactRepository;
    private AgendaRepository agendaRepository;

    // CONSTRUCTEUR
    public ReyServiceImpl(ContactRepository contactRepository, AgendaRepository agendaRepository) {
        this.contactRepository = contactRepository;
        this.agendaRepository = agendaRepository;
    }

    /* =========================================================================================
                                           CONTACT
    =========================================================================================*/
    // VOIR LA LISTE DES CONTACTS
    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }
    // ENREGISTRER UN NOUVEAU CONTACT
    @Override
    public Contact addNewContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // CHERCHER UN CONTACT PAR SON NOM
    @Override
    public List<Contact> chercherNom(String nom) {
        return contactRepository.findByNomContainingIgnoreCase(nom);
    }

    // SUPPRIMER UN CONTACT
    @Override
    public void deleteContact(Long id) {
        agendaRepository.deleteAgendaByContact_IdContact(id);
        contactRepository.deleteById(id);
    }
    // MODIFIER UN CONTACT SELECTIONNE
    @Override
    public Contact updateContact(Long id, Contact updatedContact) {
        Contact contact = contactRepository.findById(id).orElseThrow();
        contact.setNom(updatedContact.getNom());
        contact.setPrenom(updatedContact.getPrenom());
        contact.setDescription(updatedContact.getDescription());
        contact.setEmail(updatedContact.getEmail());
        contact.setPhone(updatedContact.getPhone());
        contact.setTypes(updatedContact.getTypes());
        contact.setSexe(updatedContact.getSexe());
        return contactRepository.save(contact);
    }
    // VOIR LES RENDEZ-VOUS DU CONTACT SELECTIONNE
    @Override
    public List<Agenda> afficheRdvDuContact(Long id) {
        // Récupération du contact associé aux rendez-vous
        Contact contact = contactRepository.findById(id).orElse(null);
        // Récupération de la liste de rendez-vous du contact
        List<Agenda> listeRdv = new ArrayList<>();
        if (contact != null) {
            listeRdv = contact.getRdv();
        }
        return listeRdv;
    }
    // CHERCHER SI ADRESSE MAIL EXISTE
    @Override
    public Contact getEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    /* =========================================================================================
                                            AGENDA
   =========================================================================================*/
    // ENREGISTRER UN NOUVEAU RENDEZ-VOUS
    @Override
    public Agenda addNewRdv(Long idContact, Agenda agenda) {return agendaRepository.save(agenda);}

    // VOIR LA LISTE DES RENDEZ-VOUS
    @Override
    public List<Agenda> getAllAgenda() {return agendaRepository.findAll();}

    // CHERCHER UN DATE DANS LA BASE DE DONNEE
    @Override
    public List<Agenda> ChercheDate(String date_rdv) {return agendaRepository.findByDateRdv(date_rdv); }

    // SUPPRIMER UN RENDEZ-VOUS
    @Override
    public void deleteRdv(Long id) { agendaRepository.deleteById(id); }

    // CHERCHER UNE DATE ET UNE HEURE
    @Override
    public List<Agenda> chercherDateHeure(String date, String heure) {
        return agendaRepository.findByDateRdvAndHeure(date, heure);
    }

    // BLOQUER LE CRENEAU HORAIRE
    @Override
    public Agenda bloquerHoraire(Agenda agenda) { return agendaRepository.save(agenda);}

    // MODIFIER UN RENDEZ-VOUS SELECTIONNE
    @Override
    public Agenda updateRdv(Long id, Agenda updatedAgenda) {
        Agenda agenda = agendaRepository.findById(id).orElseThrow();
        agenda.setDateRdv(updatedAgenda.getDateRdv());
        agenda.setHeure(updatedAgenda.getHeure());
        return agendaRepository.save(agenda);
    }

    // SELECTIONNER UN CONTACT PAR SON ID
    @Override
    public Contact getContactId(Long id) { return contactRepository.findById(id).orElse(null); }
}
