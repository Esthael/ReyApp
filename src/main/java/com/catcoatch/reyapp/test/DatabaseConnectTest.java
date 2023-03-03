package com.catcoatch.reyapp.test;

import com.catcoatch.reyapp.connexion.AppConfig;
import com.catcoatch.reyapp.entities.Agenda;
import com.catcoatch.reyapp.entities.Contact;
import com.catcoatch.reyapp.services.ReyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class DatabaseConnectTest {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private ReyService reyService;

    // TEST CONNEXION BASE DE DONNEES
    @Test
    public void testConnection() throws SQLException {
        try {
            Connection connection = appConfig.dataSource().getConnection();
            System.out.println("Connection réussie ! La base de données est connectée.");
            assertNotNull(connection);
        } catch (SQLException e) {
            System.out.println("La connexion a échoué : " + e.getMessage());
        }
    }

    // AJOUT D'UN CONTACT
    @Test
    public void ajoutContact() throws SQLException{
        Contact contact = new Contact();
        Contact result = reyService.addNewContact(contact);
        assertNotNull(result);
    }

    // AJOUT D'UN RENDEZ-VOUS
    @Test
    public void ajoutRdv() {
        Long idContact = 1L;
        Agenda agenda = new Agenda();
        Agenda result = reyService.addNewRdv(idContact, agenda);
        assertNotNull(result);
    }

    // VOIR LES CONTACTS
    @Test
    public void testAfficheListeContact()throws SQLException{
        List<Contact> list = reyService.getAllContact();
        assertNotNull(list);
    }

    // VOIR LES RENDEZ-VOUS
    @Test
    public void testAfficheListeRdv()throws SQLException{
        List<Agenda> list = reyService.getAllAgenda();
        assertNotNull(list);
    }

    // VOIR UN CONTACT PAR SON NOM
    @Test
    public void nomContact()throws SQLException{
        String nom = "Gibon";
        List<Contact> list = reyService.chercherNom(nom);
        assertNotNull(list);
    }
}

