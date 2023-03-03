package com.catcoatch.reyapp.repositories;

import com.catcoatch.reyapp.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // CHERCHER UN CONTACT PAR SON NOM EN IGNORANT LA CASE
    List<Contact> findByNomContainingIgnoreCase(String nom);

    // CHERCHER SI ADRESSE MAIL EXISTE
    Contact findByEmail(String email);
}
