package com.catcoatch.reyapp.vues;

import com.catcoatch.reyapp.entities.Contact;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.List;

/* Définition d'un modèle de table pour afficher une liste d'objets
     sous forme de tableau dans une interface graphique */
public class ContactTableModel extends AbstractTableModel {
    // Liste d'objets contact
    private List<Contact> contacts;

    // Nom des column de la table
    private String[] columnNames = {"Nom", "Prenom", "Email", "Téléphone", "Type", "Sexe"};

    // Constructeur qui a en paramètre une liste d'objets contact et retourne la variable contact
    public ContactTableModel(List<Contact> contacts) {this.contacts = contacts; }

    // Récupère le nombre de lignes de la table
    @Override
    public int getRowCount() { return contacts.size();}

    // Récupère le nombre de colonnes de la table
    @Override
    public int getColumnCount() {return columnNames.length;}

    // Récupère le nom de la colonne correspondante à l'indice
    @Override
    public String getColumnName(int columnIndex) { return columnNames[columnIndex]; }

    // Récupère l'objet contact correspondant à la ligne
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact contact = contacts.get(rowIndex);
        switch (columnIndex) {
            case 0: return contact.getNom();
            case 1: return contact.getPrenom();
            case 2: return contact.getEmail();
            case 3: return contact.getPhone();
            case 4: return contact.getTypes();
            case 5: return contact.getSexe();
            default: return null;
        }
    }
    // Mettre à jour la liste de contacts
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        fireTableDataChanged();
    }

    // Récupère la liste des contacts
    public List<Contact> getContacts() {
        return contacts;
    }


    // RECUPERE L ID D UN CONTACT DANS LA TABLE A PARTIR DE SON INDEX
    public Long getId_Contact(int row) {
        Contact contact = contacts.get(row);
        return contact.getIdContact();
    }

    // RECUPERER LA LISTE DES CONTACTS DE LA LIGNE SELECTIONNEE
    public Contact getContact(int ligneSelectionnee) {
        return contacts.get(ligneSelectionnee);
    }

    // MODIFIER UN CONTACT SELECTIONNE
    public void updateRow(int ligneSelectionnee, Contact contact) {
        contacts.set(ligneSelectionnee, contact);
        fireTableRowsUpdated(ligneSelectionnee, ligneSelectionnee);
    }

    // RECUPERE LA DESCRIPTION D UN CONTACT DANS LA TABLE A PARTIR DE SON INDEX
    public String getDescription(int row) {
        Contact contact = contacts.get(row);
        return contact.getDescription();
    }

    // VOIR LE CONTACT DU RENDEZ-VOUS SELECTIONNE
    public void setContact(Contact contact){
        this.contacts = Collections.singletonList(contact);
        fireTableDataChanged();
    }
}
