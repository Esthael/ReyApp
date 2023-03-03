package com.catcoatch.reyapp.vues;

import com.catcoatch.reyapp.connexion.AppConfig;
import com.catcoatch.reyapp.entities.Agenda;
import com.catcoatch.reyapp.entities.Contact;
import com.catcoatch.reyapp.enums.EnumSexe;
import com.catcoatch.reyapp.enums.EnumTypes;
import com.catcoatch.reyapp.services.ReyService;
import com.toedter.calendar.JDateChooser;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ReyVues extends JFrame {

    private ReyService reyService;

    private InfoPartage infoPartage;

    private JPanel rootPanel;
    private JTabbedPane tabbedMenu;
    private JPanel panelContact;
    private JButton btnAjoutContact;
    private JButton btnModifContact;
    private JButton btnSupContact;
    private JTable tableContactContact;
    private JEditorPane labelDescrContact;
    private JTable tableRdvContact;
    private JTextField textChercher;
    private JButton btnValideRecherche;
    private JPanel panelAjoutContact;
    private JPanel panelRadioTypes;
    private JRadioButton radioBtnParticulier;
    private JRadioButton radioBtnEntreprise;
    private JButton btnSaveContact;
    private JEditorPane editorDescr;
    private JTextField textEmail;
    private JTextField textPhone;
    private JTextField textNom;
    private JLabel labelPrenom;
    private JTextField textPrenom;
    private JPanel panelRadioSexe;
    private JRadioButton radioBtnFemme;
    private JRadioButton radioBtnHomme;
    private JPanel panelRdv;
    private JLabel labelHeureRdv;
    private JLabel labelDateRdv;
    private JPanel panelModifContact;
    private JPanel panelRadioTypesModif;
    private JRadioButton radioBtnParticulierModif;
    private JRadioButton radioBtnEntrepriseModif;
    private JButton btnModifier;
    private JEditorPane editorDescrModif;
    private JTextField textEmailModif;
    private JTextField textPhoneModif;
    private JTextField textNomModif;
    private JLabel labelPrenomModif;
    private JTextField textPrenomModif;
    private JPanel panelRadioSexeModif;
    private JRadioButton radioBtnFemmeModif;
    private JRadioButton radioBtnHommeModif;
    private JLabel labelId;
    private JPanel panelAgenda;
    private JButton btnAjouterRdv;
    private JButton btnAgendaModifRdv;
    private JButton btnSupRdv;
    private JTable tableRdvAgenda;
    private JLabel textDescrRdv;
    private JTable tableContactRdv;
    private JPanel panelDateChoix;
    private JLabel labDateChoix;
    private JPanel panelAjoutRdv;
    private JPanel panelDateChoose;
    private JButton btnSaveRdv;
    private JPanel panelTableContactRdv;
    private JTextField textChercherContact;
    private JButton btnRechercheContact;
    private JTable tableAjoutRdvContact;
    private JPanel panelLabelContact;
    private JPanel panelBtnContact;
    private JButton btnConnu;
    private JButton btnInconnu;
    private JButton btnBloquerRdv;
    private JPanel panelHeure;
    private JPanel panelBtnHeure;
    private JButton h1;
    private JButton h16;
    private JButton h17;
    private JButton h18;
    private JButton h4;
    private JButton h5;
    private JButton h6;
    private JButton h7;
    private JButton h10;
    private JButton h13;
    private JButton h8;
    private JButton h9;
    private JButton h11;
    private JButton h12;
    private JButton h14;
    private JButton h15;
    private JButton h2;
    private JButton h3;
    private JPanel panelInfo;
    private JLabel labelDate;
    private JLabel labelHeure;
    private JLabel labelContact;
    private JPanel panelModifRdv;
    private JPanel panelDateModif;
    private JButton BtnModifRdv;
    private JButton m1;
    private JButton m16;
    private JButton m17;
    private JButton m18;
    private JButton m4;
    private JButton m5;
    private JButton m6;
    private JButton m7;
    private JButton m10;
    private JButton m13;
    private JButton m8;
    private JButton m9;
    private JButton m11;
    private JButton m12;
    private JButton m14;
    private JButton m15;
    private JButton m2;
    private JButton m3;
    private JLabel labPre;
    private JLabel LabelNomModif;
    private JLabel LabelPhoneModif;
    private JLabel LabelPrenomModif;
    private JLabel LabelEmailModif;
    private JLabel LabelDateModif;
    private JLabel LabelHeureModif;
    private JLabel labelIdRdv;
    private JPanel panelActu;
    private JButton btnAjouterActu;
    private JButton btnAjoutVideo;
    private JPanel panelVideo;
    private JButton btnAjoutImage;
    private JPanel panelImage;
    private JEditorPane editorPaneCom;

    JDateChooser dateChooser = new JDateChooser();
    JDateChooser dateChoix = new JDateChooser();
    JDateChooser dateChoixModifRdv = new JDateChooser();
    private ButtonGroup GroupTypes = new ButtonGroup();
    private ButtonGroup GroupSexe = new ButtonGroup();

    // CONSTRUCTEUR
    public ReyVues(ReyService reyService, InfoPartage infoPartage) {
        this.reyService = reyService;
        this.infoPartage = infoPartage;

        // ACTION EVENT

        // Gestion des différents onglets
        tabbedMenu.addChangeListener(e ->  {
            int selectedIndex = tabbedMenu.getSelectedIndex();
            updateTable(selectedIndex);
        });

         /* =========================================================================================
                                            CONTACT
         =========================================================================================*/
        // Direction vers l'onglet "Ajout d'un contact" du bouton "Ajouter" de l'onglet "Contact"
        btnAjoutContact.addActionListener(e -> {
            // Obtenez l'index de l'onglet actuel
            int currentIndex = tabbedMenu.getSelectedIndex();
            // Calculez l'index de l'onglet suivant
            int nextIndex = (currentIndex + 1) % tabbedMenu.getTabCount();
            // Sélectionnez l'onglet suivant
            tabbedMenu.setSelectedIndex(nextIndex);
        });

        // Direction vers l'onglet "Modifier contact" d'un contact sélectionné dans la table
        btnModifContact.addActionListener(e ->  {
            int ligneSelectionnee = tableContactContact.getSelectedRow();
            ContactTableModel tableModel = (ContactTableModel) tableContactContact.getModel();
            if (ligneSelectionnee == -1) {
                JOptionPane.showMessageDialog(tabbedMenu, "Veuillez sélectionner un contact à modifier.");
            } else {
                Contact contact = tableModel.getContact(ligneSelectionnee);
                labelId.setText(contact.getIdContact().toString());
                textNomModif.setText(contact.getNom());
                textPrenomModif.setText(contact.getPrenom());
                editorDescrModif.setText(contact.getDescription());
                textEmailModif.setText(contact.getEmail());
                textPhoneModif.setText(contact.getPhone());

                if (contact.getTypes() == EnumTypes.PARTICULIER) {
                    radioBtnParticulierModif.setSelected(true);
                    panelRadioSexeModif.setVisible(true);
                    textPrenomModif.setVisible(true);
                    labelPrenomModif.setVisible(true);
                } else if (contact.getTypes() == EnumTypes.ENTREPRISE) {
                    radioBtnEntrepriseModif.setSelected(true);
                    panelRadioSexeModif.setVisible(false);
                    textPrenomModif.setVisible(false);
                    labelPrenomModif.setVisible(false);
                }
                if (contact.getSexe() == EnumSexe.FEMME) {
                    radioBtnFemmeModif.setSelected(true);
                } else if (contact.getSexe() == EnumSexe.HOMME) {
                    radioBtnHommeModif.setSelected(true);
                }
                // Obtenez l'index de l'onglet actuel
                int currentIndex = tabbedMenu.getSelectedIndex();
                // Calculez l'index de l'onglet suivant
                int nextIndex = (currentIndex + 2) % tabbedMenu.getTabCount();
                // Sélectionnez l'onglet suivant
                tabbedMenu.setSelectedIndex(nextIndex);
            }
        });

        // Supprimer le contact sélectionné dans la table depuis son id dans la BDD
        btnSupContact.addActionListener(e ->  {supprimerContact();});

        // Création d'un nouveau contact
        btnSaveContact.addActionListener(e ->  { saveContact(); });

        // Chercher un contact avec son nom
        btnValideRecherche.addActionListener(e ->  { chercherContact();});

        // Modifier un contact sélectionné
        btnModifier.addActionListener(e -> { modifierContact();});

        // Voir la description du contact sélectionné
        tableContactContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                voirDescription();
            }
        });

        // Evenement au clique du radio bouton entreprise
        radioBtnEntreprise.addActionListener(e ->  {
            panelRadioSexe.setVisible(false);
            textPrenom.setVisible(false);
            labelPrenom.setVisible(false);
        });
        // Evenement au clique du radio bouton particulier
        radioBtnParticulier.addActionListener(e -> {
            panelRadioSexe.setVisible(true);
            textPrenom.setVisible(true);
            labelPrenom.setVisible(true);
        });
        // Evenement au clique du radio bouton entreprise
        radioBtnEntrepriseModif.addActionListener(e ->  {
            panelRadioSexeModif.setVisible(false);
            textPrenomModif.setVisible(false);
            labelPrenomModif.setVisible(false);
        });
        // Evenement au clique du radio bouton particulier
        radioBtnParticulierModif.addActionListener(e -> {
            panelRadioSexeModif.setVisible(true);
            textPrenomModif.setVisible(true);
            labelPrenomModif.setVisible(true);
        });

         /* =========================================================================================
                                            AGENDA
         =========================================================================================*/
        // Evénement au clique du bouton pour se diriger vers l'onglet "Ajout d'un rendez-vous"
        btnAjouterRdv.addActionListener(e -> {
            // Obtenez l'index de l'onglet actuel
            int currentIndex = tabbedMenu.getSelectedIndex();
            // Calculez l'index de l'onglet suivant
            int nextIndex = (currentIndex + 1) % tabbedMenu.getTabCount();
            // Sélectionnez l'onglet suivant
            tabbedMenu.setSelectedIndex(nextIndex);
        });

        // Création d'un nouveau rdv
        btnSaveRdv.addActionListener(e ->  { saveRdv();});

        // Supprimer un rendez-vous
        btnSupRdv.addActionListener(e ->  { supprimerRdv();});

        // Evenement au clique du bouton pour faire apparaitre la liste des contacts
        btnConnu.addActionListener(e ->  { panelTableContactRdv.setVisible(true);});

        // Evenement au clique du bouton pour se diriger vers l'onglet "ajouter contact"
        btnInconnu.addActionListener(e ->  {
            panelTableContactRdv.setVisible(false);
            // Obtenez l'index de l'onglet actuel
            int currentIndex = tabbedMenu.getSelectedIndex();
            // Calculez l'index de l'onglet suivant
            int nextIndex = (currentIndex - 3) % tabbedMenu.getTabCount();
            // Sélectionnez l'onglet suivant
            tabbedMenu.setSelectedIndex(nextIndex);

            // HEURE
            String heureRdv = labelHeure.getText();
            this.infoPartage.setHeureRdv(heureRdv);
            String heureContact = this.infoPartage.getHeureRdv();
            labelHeureRdv.setText(heureContact);

            // DATE
            String dateRdv = labelDate.getText();
            this.infoPartage.setDateRdv(dateRdv);
            String dateContact = this.infoPartage.getDateRdv();
            labelDateRdv.setText(dateContact);

            panelRdv.setVisible(true);
        });

        // Evenement au choix d'une date sélectionné mise à jour des tableaux Agenda et Contact
        dateChoix.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                Date selectedDate = dateChoix.getDate();
                if(selectedDate == null) {
                    labDateChoix.setText("");
                    return;
                }
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = dateFormat.format(selectedDate);
                labDateChoix.setText(formattedDate);

                List<Agenda> agenda = reyService.ChercheDate(formattedDate);

                List<Agenda> validAgenda = new ArrayList<>();
                for (Agenda rdv : agenda) {
                    Contact contact = rdv.getContact();
                    if (contact != null) {
                        validAgenda.add(rdv);
                    }
                }

                AgendaTableModel tableModelAgenda = (AgendaTableModel) tableRdvAgenda.getModel();
                tableModelAgenda.setAgenda(validAgenda);
                tableModelAgenda.fireTableDataChanged();

                List<Contact> contacts = new ArrayList<>();

                // Parcourez la liste des rendez-vous et ajoutez chaque contact unique à la liste des contacts
                for(Agenda rdv : agenda) {
                    Contact contact = rdv.getContact();
                    if(contact != null && !contacts.contains(contact)) {
                        contacts.add(contact);
                    }
                }
                // Mettre à jour le modèle de tableau avec les données des contacts
                ContactTableModel tableModelContact = (ContactTableModel) tableContactRdv.getModel();
                tableModelContact.setContacts(contacts);
                tableModelContact.fireTableDataChanged();
                textDescrRdv.setText("");
            }
        });

        // Evènement pour mettre la date dans le label
        dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    // Récupérez la date sélectionnée avec le JDateChooser
                    Date date = dateChooser.getDate();
                    if(date == null) {
                        labelDate.setText("");
                        return;
                    }
                    // Formatez la date en utilisant le format par défaut
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = dateFormat.format(date);

                    // Mise à jour du label avec la date formatée
                    labelDate.setText(formattedDate);
                }
                labelHeure.setText("");
            }
        });

        // Evènement à l'affichage de la date les boutons se désactive si l'heure est deja prise
        labelDate.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String date = labelDate.getText();
                if (date.isEmpty()){
                    panelHeure.setVisible(false);
                    panelBtnHeure.setVisible(false);
                }else {
                    panelHeure.setVisible(true);
                    panelBtnHeure.setVisible(true);
                    panelInfo.setVisible(true);
                }
                choixHeure();
            }
        });

        // Evenement a l'affichage de l'heure les boutons pour sélectionner le contact s'affiche
        labelHeure.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String heure = labelHeure.getText();
                if (heure.isEmpty()){
                    panelLabelContact.setVisible(false);
                    panelBtnContact.setVisible(false);
                }else {
                    panelLabelContact.setVisible(true);
                    panelBtnContact.setVisible(true);
                }
            }
        });

        // Modifier la date
        dateChoixModifRdv.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    // Récupérez la date sélectionnée avec le JDateChooser
                    Date date = dateChoixModifRdv.getDate();
                    if(date == null) {
                        LabelDateModif.setText("");
                        return;
                    }
                    // Formatez la date en utilisant le format par défaut
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String formattedDate = dateFormat.format(date);

                    // Mise à jour du label avec la date formatée
                    LabelDateModif.setText(formattedDate);
                }
                LabelHeureModif.setText("");
            }
        });

        // Sélectionner un contact existant dans la base
        tableAjoutRdvContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rdvContact();
            }
        });

        // Evenement du choix du rendez-vous pour voir le contact
        tableRdvAgenda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rdvAgenda();
            }
        });

        // Evenement pour afficher la description du contact du rendez-vous sélectionné
        tableContactRdv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {afficheDescrContactRdv();     }
        });

        // Evenement pour bloquer le créneau horaire de ce jour
        btnBloquerRdv.addActionListener(e ->  {bloqueCreneau(); });

        // Modifier le rendez-vous sélectionné
        BtnModifRdv.addActionListener(e ->  {modifierRdv();});

        // Direction vers l'onglet "Modifier rendez-vous"
        btnAgendaModifRdv.addActionListener(e ->  {
            int ligneSelectionnee = tableRdvAgenda.getSelectedRow();
            AgendaTableModel tableModel = (AgendaTableModel) tableRdvAgenda.getModel();
            if (ligneSelectionnee == -1) {
                JOptionPane.showMessageDialog(tabbedMenu, "Veuillez sélectionner un rendez-vous à modifier.");
            } else {
                Agenda agenda = tableModel.getAgenda().get(ligneSelectionnee);
                labelIdRdv.setText(agenda.getIdCal().toString());
                LabelNomModif.setText(agenda.getContact().getNom());
                LabelPrenomModif.setText(agenda.getContact().getPrenom());
                LabelEmailModif.setText(agenda.getContact().getEmail());
                LabelPhoneModif.setText(agenda.getContact().getPhone());

                // Obtenez l'index de l'onglet actuel
                int currentIndex = tabbedMenu.getSelectedIndex();
                // Calculez l'index de l'onglet suivant
                int nextIndex = (currentIndex + 2) % tabbedMenu.getTabCount();
                // Sélectionnez l'onglet suivant
                tabbedMenu.setSelectedIndex(nextIndex);
            }
        });

        // Modification du label de la date
        LabelDateModif.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                choixHeureModif();
            }
        });

        // Evénement au clique du bouton pour insérer l'heure dans un label
        h1.addActionListener(e ->  { labelHeure.setText("06H00"); });
        h2.addActionListener(e ->  { labelHeure.setText("07H00"); });
        h3.addActionListener(e ->  { labelHeure.setText("08H00"); });
        h4.addActionListener(e ->  { labelHeure.setText("09H00"); });
        h5.addActionListener(e ->  { labelHeure.setText("10H00"); });
        h6.addActionListener(e ->  { labelHeure.setText("11H00"); });
        h7.addActionListener(e ->  { labelHeure.setText("12H00"); });
        h8.addActionListener(e ->  { labelHeure.setText("13H00"); });
        h9.addActionListener(e ->  { labelHeure.setText("14H00"); });
        h10.addActionListener(e ->  { labelHeure.setText("15H00"); });
        h11.addActionListener(e ->  { labelHeure.setText("16H00"); });
        h12.addActionListener(e ->  { labelHeure.setText("17H00"); });
        h13.addActionListener(e ->  { labelHeure.setText("18H00"); });
        h14.addActionListener(e ->  { labelHeure.setText("19H00"); });
        h15.addActionListener(e ->  { labelHeure.setText("20H00"); });
        h16.addActionListener(e ->  { labelHeure.setText("21H00"); });
        h17.addActionListener(e ->  { labelHeure.setText("22H00"); });
        h18.addActionListener(e ->  { labelHeure.setText("23H00"); });
        // Evénement au clique du bouton pour modifier l'heure dans un label
        m1.addActionListener(e ->  { LabelHeureModif.setText("06H00"); });
        m2.addActionListener(e ->  { LabelHeureModif.setText("07H00"); });
        m3.addActionListener(e ->  { LabelHeureModif.setText("08H00"); });
        m4.addActionListener(e ->  { LabelHeureModif.setText("09H00"); });
        m5.addActionListener(e ->  { LabelHeureModif.setText("10H00"); });
        m6.addActionListener(e ->  { LabelHeureModif.setText("11H00"); });
        m7.addActionListener(e ->  { LabelHeureModif.setText("12H00"); });
        m8.addActionListener(e ->  { LabelHeureModif.setText("13H00"); });
        m9.addActionListener(e ->  { LabelHeureModif.setText("14H00"); });
        m10.addActionListener(e ->  { LabelHeureModif.setText("15H00"); });
        m11.addActionListener(e ->  { LabelHeureModif.setText("16H00"); });
        m12.addActionListener(e ->  { LabelHeureModif.setText("17H00"); });
        m13.addActionListener(e ->  { LabelHeureModif.setText("18H00"); });
        m14.addActionListener(e ->  { LabelHeureModif.setText("19H00"); });
        m15.addActionListener(e ->  { LabelHeureModif.setText("20H00"); });
        m16.addActionListener(e ->  { LabelHeureModif.setText("21H00"); });
        m17.addActionListener(e ->  { LabelHeureModif.setText("22H00"); });
        m18.addActionListener(e ->  { LabelHeureModif.setText("23H00"); });
    }

    /* =========================================================================================
                               GESTION DES ONGLETS DU MENU
    =========================================================================================*/
    private void updateTable(int selectedIndex) {
        switch (selectedIndex) {
            case 0 -> {
                labelIdRdv.setText("");
                labelId.setText("");
                textChercher.setText("");
                List<Contact> contactContact = reyService.getAllContact();
                ContactTableModel contactModelContact = new ContactTableModel(contactContact);
                tableContactContact.setModel(contactModelContact);
                labelDescrContact.setText("");
                List<Agenda> agendasContact = reyService.getAllAgenda();
                AgendaTableModel agendaModelAgenda = new AgendaTableModel(agendasContact);
                tableRdvContact.setModel(agendaModelAgenda);
            }
            case 1 -> {
                labelIdRdv.setText("");
                labelId.setText("");
                panelRdv.setVisible(false);
                panelRadioSexe.setVisible(true);
                labelId.setText("");
                GroupTypes.add(radioBtnEntreprise);
                GroupTypes.add(radioBtnParticulier);
                GroupTypes.clearSelection();
                GroupSexe.add(radioBtnFemme);
                GroupSexe.add(radioBtnHomme);
                GroupSexe.clearSelection();
                textNom.setText(null);
                textPrenom.setText("");
                textPhone.setText("");
                editorDescr.setText("");
                textEmail.setText("");
            }
            case 2 -> {
                labelIdRdv.setText("");
                if (labelId.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(tabbedMenu,
                            "Veuillez sélectionner un contact à modifier.");
                    // Obtenez l'index de l'onglet actuel
                    int currentIndex = tabbedMenu.getSelectedIndex();
                    // Calculez l'index de l'onglet suivant
                    int nextIndex = (currentIndex - 2) % tabbedMenu.getTabCount();
                    // Sélectionnez l'onglet suivant
                    tabbedMenu.setSelectedIndex(nextIndex);
                }
            }
            case 3 -> {
                labelIdRdv.setText("");
                labelId.setText("");
                dateChoix.setDateFormatString("dd-MM-yyyy");
                panelDateChoix.add(dateChoix);
                dateChoix.setDate(null);
                List<Contact> contactsAgenda = reyService.getAllContact();
                ContactTableModel contactModel = new ContactTableModel(contactsAgenda);
                tableContactRdv.setModel(contactModel);
                labelDescrContact.setText("");
                List<Agenda> agendasAgenda = reyService.getAllAgenda();
                AgendaTableModel agendaModel = new AgendaTableModel(agendasAgenda);
                tableRdvAgenda.setModel(agendaModel);
                textDescrRdv.setText("");
            }
            case 4 -> {
                labelIdRdv.setText("");
                labelId.setText("");
                dateChooser.setDateFormatString("dd-MM-yyyy");
                panelDateChoose.add(dateChooser);
                labelDate.setText("");
                labelHeure.setText("");
                labelContact.setText("");
                panelInfo.setVisible(false);
                panelTableContactRdv.setVisible(false);
                dateChooser.setDate(null);
            }
            case 5 -> {
                labelId.setText("");
                if (labelIdRdv.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(tabbedMenu,
                            "Veuillez sélectionner un rendez-vous à modifier.");
                    // Obtenez l'index de l'onglet actuel
                    int currentIndex = tabbedMenu.getSelectedIndex();
                    // Calculez l'index de l'onglet suivant
                    int nextIndex = (currentIndex - 2) % tabbedMenu.getTabCount();
                    // Sélectionnez l'onglet suivant
                    tabbedMenu.setSelectedIndex(nextIndex);
                }
                dateChoixModifRdv.setDateFormatString("dd-MM-yyyy");
                panelDateModif.add(dateChoixModifRdv);
                dateChoixModifRdv.setDate(null);
            }
            case 6 -> {
                labelIdRdv.setText("");
                labelId.setText("");
            }
        }
    }

    /* =========================================================================================
                                        CONTACT
    ========================================================================================= */
    // ENREGISTRER UN NOUVEAU CONTACT
    private void saveContact() {
        // Identifier les inputs du contact
        String nom = textNom.getText();
        String prenom = textPrenom.getText();
        String email = textEmail.getText();
        String phone = textPhone.getText();
        String description = editorDescr.getText();

        // Identifier les elements du contact de la base de donnée
        Contact contact = new Contact();
        contact.setNom(nom);
        contact.setPrenom(prenom);
        contact.setEmail(email);
        contact.setPhone(phone);
        contact.setDescription(description);

        // Identifier les inputs de l'agenda
        String date = labelDateRdv.getText();
        String heure = labelHeureRdv.getText();

        // Identifier les elements de l'agenda de la base de donnée
        Agenda agenda = new Agenda();
        agenda.setContact(contact);
        agenda.setDateRdv(date);
        agenda.setHeure(heure);

        // Vérifier si le bouton Entreprise est sélectionné et Vérifier si les champs visibles ne sont pas vide
        if (radioBtnEntreprise.isSelected()){
            contact.setTypes(EnumTypes.ENTREPRISE);
            if (nom.isEmpty() || email.isEmpty() || phone.isEmpty() || description.isEmpty()){
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (radioBtnParticulier.isSelected()) {
            contact.setTypes(EnumTypes.PARTICULIER);
            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty() || description.isEmpty()){
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }else {
            // Afficher une alerte si aucun n'est sélectionné
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un type de contact.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifiez le format de l'adresse email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (!emailPattern.matcher(email).matches()) {
            JOptionPane.showMessageDialog(null, "L'adresse email est incorrecte !",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifiez le format du numéro de téléphone
        String phoneRegex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        if (!phonePattern.matcher(phone).matches()) {
            JOptionPane.showMessageDialog(null, "Le numéro de téléphone est incorrect !",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifier si les sexes sont visibles et Enregistrement du choix du sexe
        if (panelRadioSexe.isVisible()){
            if (radioBtnFemme.isSelected()) {
                contact.setSexe(EnumSexe.FEMME);
            } else if (radioBtnHomme.isSelected()) {
                contact.setSexe(EnumSexe.HOMME);
            }else {
                // Afficher une alerte si aucun n'est sélectionné
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner le sexe du contact.",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }else if (!panelRadioSexe.isVisible()){
            contact.setSexe(null);
        }

        // Vérifier si l'adresse email est déjà présente dans la base de donnée
        Contact emailExist = reyService.getEmail(email);
        if (emailExist != null){
            JOptionPane.showMessageDialog(null,
                    "Cette adresse email est existe déjà !",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            textEmail.setText("");
            return;
        }

        if (!panelRdv.isVisible()){
            // Enregistrement des elements du contact dans la base de donnée
            reyService.addNewContact(contact);
        }else if (panelRdv.isVisible()){
            // Enregistrement des elements du contact et du rendez-vous dans la base de donnée
            reyService.addNewContact(contact);
            reyService.addNewRdv(contact.getIdContact(), agenda);
        }

        JOptionPane.showMessageDialog(this, "Contact sauvegardé avec succès.");

        // Vider les composants
        textNom.setText("");
        textPrenom.setText("");
        textEmail.setText("");
        textPhone.setText("");
        editorDescr.setText("");
        panelRdv.setVisible(false);
        GroupTypes.add(radioBtnEntreprise);
        GroupTypes.add(radioBtnParticulier);
        GroupTypes.clearSelection();
        GroupSexe.add(radioBtnFemme);
        GroupSexe.add(radioBtnHomme);
        GroupSexe.clearSelection();

        // Obtenez l'index de l'onglet actuel
        int currentIndex = tabbedMenu.getSelectedIndex();
        // Calculez l'index de l'onglet suivant
        int nextIndex = (currentIndex - 1) % tabbedMenu.getTabCount();
        // Sélectionnez l'onglet suivant
        tabbedMenu.setSelectedIndex(nextIndex);

    }

    // CHERCHER UN CONTACT AVEC SON NOM
    private void chercherContact() {
        String nom = textChercher.getText();
        List<Contact> contacts = reyService.chercherNom(nom);
        ContactTableModel tableModel = (ContactTableModel) tableContactContact.getModel();
        tableModel.setContacts(contacts);
        tableModel.fireTableDataChanged();
        labelDescrContact.setText("");
    }

    // SUPPRIMER UN CONTACT
    private void supprimerContact() {
        int ligneSelectionnee = tableContactContact.getSelectedRow();
        ContactTableModel tableModel = (ContactTableModel) tableContactContact.getModel();
        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(tabbedMenu, "Veuillez sélectionner un contact à supprimer.");
        }else{
            if (ligneSelectionnee >= 0 && ligneSelectionnee < tableModel.getContacts().size()) {
                Long id_contact = tableModel.getId_Contact(ligneSelectionnee);
                reyService.deleteContact(id_contact);
                displayContacts();
                List<Agenda> agendasContact = reyService.getAllAgenda();
                AgendaTableModel agendaModelAgenda = new AgendaTableModel(agendasContact);
                tableRdvContact.setModel(agendaModelAgenda);
            }
            JOptionPane.showMessageDialog(this, "Contact supprimé avec succès.");
            labelDescrContact.setText("");
            tableRdvContact.repaint();
        }
    }

    // METTRE A JOUR LE TABLEAU DES CONTACTS
    private void displayContacts() {
        List<Contact> contacts = reyService.getAllContact();
        ContactTableModel model = new ContactTableModel(contacts);
        tableContactContact.setModel(model);
        tableContactRdv.setModel(model);
        tableAjoutRdvContact.setModel(model);
    }

    // MODIFIER LE CONTACT SELECTIONNE
    private void modifierContact() {
        // Identifier les inputs du contact
        String nom = textNomModif.getText();
        String prenom = textPrenomModif.getText();
        String email = textEmailModif.getText();
        String phone = textPhoneModif.getText();
        String description = editorDescrModif.getText();

        int ligneSelectionnee = tableContactContact.getSelectedRow();
        ContactTableModel tableModel = (ContactTableModel) tableContactContact.getModel();
        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un contact à modifier.");
        } else {
            Long id = tableModel.getId_Contact(ligneSelectionnee);
            Contact updatedContact = new Contact();
            updatedContact.setNom(textNomModif.getText());
            updatedContact.setPrenom(textPrenomModif.getText());
            updatedContact.setDescription(editorDescrModif.getText());
            updatedContact.setEmail(textEmailModif.getText());
            updatedContact.setPhone(textPhoneModif.getText());

            // Vérifier si le bouton Entreprise est sélectionné et Vérifier si les champs visibles ne sont pas vide
            if (radioBtnEntrepriseModif.isSelected()){
                updatedContact.setTypes(EnumTypes.ENTREPRISE);
                if (nom.isEmpty() || email.isEmpty() || phone.isEmpty() || description.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (radioBtnParticulierModif.isSelected()) {
                updatedContact.setTypes(EnumTypes.PARTICULIER);
                if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || phone.isEmpty() || description.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }else {
                // Afficher une alerte si aucun n'est sélectionné
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un type de contact.",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérifiez le format de l'adresse email
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
            Pattern emailPattern = Pattern.compile(emailRegex);
            if (!emailPattern.matcher(email).matches()) {
                JOptionPane.showMessageDialog(null,
                        "L'adresse email est incorrecte !",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérifiez le format du numéro de téléphone
            String phoneRegex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
            Pattern phonePattern = Pattern.compile(phoneRegex);
            if (!phonePattern.matcher(phone).matches()) {
                JOptionPane.showMessageDialog(null,
                        "Le numéro de téléphone est incorrect !",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifier si les sexes sont visibles et Enregistrement du choix du sexe
            if (panelRadioSexeModif.isVisible()){
                if (radioBtnFemmeModif.isSelected()) {
                    updatedContact.setSexe(EnumSexe.FEMME);
                } else if (radioBtnHommeModif.isSelected()) {
                    updatedContact.setSexe(EnumSexe.HOMME);
                }else {
                    // Afficher une alerte si aucun n'est sélectionné
                    JOptionPane.showMessageDialog(this, "Veuillez sélectionner le sexe du contact.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }else if (!panelRadioSexeModif.isVisible()){
                updatedContact.setSexe(null);
            }

            // Sauvegarder les modifications du contact
            Contact contact = reyService.updateContact(id, updatedContact);
            // Mettre à jour la ligne de la table correspondant au contact modifié
            tableModel.updateRow(ligneSelectionnee, contact);
            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(this, "Contact modifié avec succès.");
            labelId.setText("");
            GroupTypes.add(radioBtnEntrepriseModif);
            GroupTypes.add(radioBtnParticulierModif);
            GroupTypes.clearSelection();
            GroupSexe.add(radioBtnFemmeModif);
            GroupSexe.add(radioBtnHommeModif);
            GroupSexe.clearSelection();
            textNomModif.setText("");
            textPrenomModif.setText("");
            textPhoneModif.setText("");
            editorDescrModif.setText("");
            textEmailModif.setText("");

            // Obtenez l'index de l'onglet actuel
            int currentIndex = tabbedMenu.getSelectedIndex();
            // Calculez l'index de l'onglet suivant
            int nextIndex = (currentIndex - 2) % tabbedMenu.getTabCount();
            // Sélectionnez l'onglet suivant
            tabbedMenu.setSelectedIndex(nextIndex);

        }
    }

    // AFFICHER LA DESCRIPTION DU CONTACT SELECTION
    private void voirDescription() {
        // Récupération de la ligne sélectionnée dans la table
        int ligneSelectionnee = tableContactContact.getSelectedRow();

        // Récupération du ContactTableModel associé à la table
        ContactTableModel tableModel = (ContactTableModel) tableContactContact.getModel();
        AgendaTableModel agendaTableModel = (AgendaTableModel) tableRdvContact.getModel();

        // Vérification que l'index est valide (compris entre 0 et la taille de la liste moins 1)
        if (ligneSelectionnee >= 0 && ligneSelectionnee < tableModel.getContacts().size()) {
            // Utilisation de la méthode getDescription du ContactTableModel pour récupérer la description
            String description = tableModel.getDescription(ligneSelectionnee);
            // Utilisation de la méthode setText pour afficher la description dans le label textDescription
            labelDescrContact.setText(description);

            Long id_contact = tableModel.getId_Contact(ligneSelectionnee);

            List<Agenda> agenda = reyService.afficheRdvDuContact(id_contact);

            agendaTableModel.setAgenda(agenda);

            tableRdvContact.repaint();
        }
    }


    /* =========================================================================================
                                        AGENDA
    ========================================================================================= */
    // SAUVEGARDER UN RENDEZ VOUS
    private void saveRdv() {
        String date = labelDate.getText();
        String heure = labelHeure.getText();
        String labContact = labelContact.getText();

        if (date.isEmpty()){
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un date !",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (heure.isEmpty()){
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une heure disponible !",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (labContact.isEmpty()){
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un contact dans vos contact ou enregistrer un nouveau contact !",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ligneSelectionnee = tableAjoutRdvContact.getSelectedRow();
        ContactTableModel tableModel = (ContactTableModel) tableAjoutRdvContact.getModel();
        Long id_contact = tableModel.getId_Contact(ligneSelectionnee);
        Contact contact = reyService.getContactId(id_contact);

        Agenda agenda = new Agenda();
        agenda.setDateRdv(date);
        agenda.setHeure(heure);
        agenda.setContact(contact);

        reyService.addNewRdv(contact.getIdContact(), agenda);

        JOptionPane.showMessageDialog(this, "Rendez-vous enregistré avec succès.");

        labelDate.setText("");
        labelHeure.setText("");
        labelContact.setText("");
        panelInfo.setVisible(false);
        panelTableContactRdv.setVisible(false);

        // Obtenez l'index de l'onglet actuel
        int currentIndex = tabbedMenu.getSelectedIndex();
        // Calculez l'index de l'onglet suivant
        int nextIndex = (currentIndex - 1) % tabbedMenu.getTabCount();
        // Sélectionnez l'onglet suivant
        tabbedMenu.setSelectedIndex(nextIndex);
    }

    // SUPPRIMER UN RENDEZ VOUS
    private void supprimerRdv() {
        // Récupération de la ligne sélectionnée dans la table
        int ligneSelectionnee = tableRdvAgenda.getSelectedRow();
        // Récupération de AgendaTableModel associé à la table
        AgendaTableModel tableModel = (AgendaTableModel) tableRdvAgenda.getModel();

        if (ligneSelectionnee == -1) {
            JOptionPane.showMessageDialog(tabbedMenu, "Veuillez sélectionner un rendez-vous à supprimer.");
        }else {
            // Vérification que l'index est valide (compris entre 0 et la taille de la liste moins 1)
            if (ligneSelectionnee >= 0 && ligneSelectionnee < tableModel.getAgenda().size()) {
                // Utilisation de la méthode getIdRdv de AgendaTableModel pour récupérer l'ID du rdv
                Long id = tableModel.getIdRdv(ligneSelectionnee);
                // Utilisation de la méthode delete pour supprimer le rdv
                reyService.deleteRdv(id);
                // Mise à jour du tableau
                displayAgenda();
                textDescrRdv.setText("");
            }
            JOptionPane.showMessageDialog(this, "Rendez-vous supprimé avec succès.");
        }
    }

    // MISE A JOUR DES TABLEAUX DES AGENDAS
    private void displayAgenda() {
        List<Agenda> agenda = reyService.getAllAgenda();
        AgendaTableModel model = new AgendaTableModel(agenda);
        tableRdvAgenda.setModel(model);
    }

    // BOUTON D HEURE SAVE POUR DESACTIVE SI DEJA PRIS
    private void setButtonState(JButton button, String heure) {
        String date = labelDate.getText();
        List<Agenda> agenda = reyService.chercherDateHeure(date, heure);
        boolean hasContact = false;
        boolean isTaken = true;
        Agenda rdv = null;
        for(Agenda a:agenda) {
            if(a.getContact() != null) {
                hasContact = true;
                rdv = a;
                break;
            }
        }
        if (agenda.isEmpty()){
            isTaken = false;
        }
        if (!hasContact) {
            if (isTaken){
                button.setEnabled(false);
                button.setBackground(Color.RED);
            }
            else{
                button.setEnabled(true);
                button.setBackground(new Color(45, 146, 220));
            }
        } else {
            if (isTaken){
                button.setEnabled(false);
                button.setBackground(Color.GRAY);
            }
            else{
                button.setEnabled(true);
                button.setBackground(Color.RED);
            }
        }
    }

    // INITIALISATION DES BOUTONS D HEURE SAVE
    private void choixHeure(){
        setButtonState(h1, "06H00");
        setButtonState(h2, "07H00");
        setButtonState(h3, "08H00");
        setButtonState(h4, "09H00");
        setButtonState(h5, "10H00");
        setButtonState(h6, "11H00");
        setButtonState(h7, "12H00");
        setButtonState(h8, "13H00");
        setButtonState(h9, "14H00");
        setButtonState(h10, "15H00");
        setButtonState(h11, "16H00");
        setButtonState(h12, "17H00");
        setButtonState(h13, "18H00");
        setButtonState(h14, "19H00");
        setButtonState(h15, "20H00");
        setButtonState(h16, "21H00");
        setButtonState(h17, "22H00");
        setButtonState(h18, "23H00");
    }

    // SELECTIONNER UN CONTACT PAR SON ID
    private void rdvContact() {
        // Récupération de la ligne sélectionnée dans la table
        int ligneSelectionnee = tableAjoutRdvContact.getSelectedRow();
        // Récupération du ContactTableModel associé à la table
        ContactTableModel tableModel = (ContactTableModel) tableAjoutRdvContact.getModel();
        // Vérification que l'index est valide (compris entre 0 et la taille de la liste moins 1)
        if (ligneSelectionnee >= 0 && ligneSelectionnee < tableModel.getContacts().size()) {
            // Utilisation de la méthode getId_Contact du ContactTableModel pour récupérer l'ID du contact
            Long id_contact = tableModel.getId_Contact(ligneSelectionnee);
            Contact contact = reyService.getContactId(id_contact);
            String nom = contact.getNom();
            labelContact.setText(nom);
        }
    }

    // VOIR LE CONTACT DU RENDEZ-VOUS SELECTIONNE
    private void rdvAgenda() {
        // Récupération de la ligne sélectionnée dans la table
        int ligneSelectionnee = tableRdvAgenda.getSelectedRow();

        // Récupération du ContactTableModel associé à la table
        AgendaTableModel agendaTableModel = (AgendaTableModel) tableRdvAgenda.getModel();
        ContactTableModel tableModel = (ContactTableModel) tableContactRdv.getModel();

        // Vérification que l'index est valide (compris entre 0 et la taille de la liste moins 1)
        if (ligneSelectionnee >= 0 && ligneSelectionnee < agendaTableModel.getAgenda().size()) {
            // Récupération de l'ID de contact à partir de l'agenda
            Contact idContact = agendaTableModel.getAgenda().get(ligneSelectionnee).getContact();

            // Condition si le contact n'est pas null
            if(idContact != null) {
                // Mise à jour du modèle de table pour afficher le contact sélectionné
                tableModel.setContact(idContact);
                tableModel.fireTableDataChanged();
                // Effacer le texte
                textDescrRdv.setText("");
            } else {
                //Ajoutez votre logique pour gérer le cas où le contact est null
                textDescrRdv.setText("Créneau horaire bloqué");
                // Mise à jour du tableau contact
                displayContacts();
            }
        }
    }

    // AFFICHER LA DESCRIPTION DU CONTACT SELECTIONNE DU RDV
    private void afficheDescrContactRdv(){
        // Récupération de la ligne sélectionnée dans la table
        int ligneSelectionnee = tableContactRdv.getSelectedRow();
        // Récupération du ContactTableModel associé à la table
        ContactTableModel tableModel = (ContactTableModel) tableContactRdv.getModel();
        // Vérification que l'index est valide (compris entre 0 et la taille de la liste moins 1)
        if (ligneSelectionnee >= 0 && ligneSelectionnee < tableModel.getContacts().size()) {
            // Utilisation de la méthode getDescription du ContactTableModel pour récupérer la description
            String description = tableModel.getDescription(ligneSelectionnee);
            // Utilisation de la méthode setText pour afficher la description dans le label textDescription
            textDescrRdv.setText(description);
        }
    }

    // BLOQUER DES CRENEAUX SANS CONTACT
    private void bloqueCreneau() {

        String date = labelDate.getText();
        String heure = labelHeure.getText();

        Agenda agenda = new Agenda();
        agenda.setDateRdv(date);
        agenda.setHeure(heure);

        reyService.bloquerHoraire(agenda);

        JOptionPane.showMessageDialog(this, "Créneau horaire bloqués avec succès.");

        // Obtenez l'index de l'onglet actuel
        int currentIndex = tabbedMenu.getSelectedIndex();
        // Calculez l'index de l'onglet suivant
        int nextIndex = (currentIndex - 1) % tabbedMenu.getTabCount();
        // Sélectionnez l'onglet suivant
        tabbedMenu.setSelectedIndex(nextIndex);
    }

    // MODIFIER LE RENDEZ-VOUS SELECTIONNE
    private void modifierRdv(){
        int ligneSelectionnee = tableRdvAgenda.getSelectedRow();
        AgendaTableModel tableModel = (AgendaTableModel) tableRdvAgenda.getModel();
        Long id = tableModel.getIdRdv(ligneSelectionnee);
        Agenda updatedAgenda = new Agenda();
        updatedAgenda.setDateRdv(LabelDateModif.getText());
        updatedAgenda.setHeure(LabelHeureModif.getText());

        // Sauvegarder les modifications du contact
        Agenda agenda = reyService.updateRdv(id, updatedAgenda);
        // Mettre à jour la ligne de la table correspondant au contact modifié
        tableModel.updateRow(ligneSelectionnee, agenda);
        // Afficher un message de confirmation
        JOptionPane.showMessageDialog(this, "Rendez-vous modifié avec succès.");

        // Obtenez l'index de l'onglet actuel
        int currentIndex = tabbedMenu.getSelectedIndex();
        // Calculez l'index de l'onglet suivant
        int nextIndex = (currentIndex - 2) % tabbedMenu.getTabCount();
        // Sélectionnez l'onglet suivant
        tabbedMenu.setSelectedIndex(nextIndex);
    }

    // BOUTON D HEURE MODIFIER POUR DESACTIVE SI DEJA PRIS
    private void setButtonStateModif(JButton button, String heure) {
        String date = LabelDateModif.getText();
        List<Agenda> agenda = reyService.chercherDateHeure(date, heure);
        boolean hasContact = false;
        boolean isTaken = true;
        Agenda rdv = null;
        for(Agenda a:agenda) {
            if(a.getContact() != null) {
                hasContact = true;
                rdv = a;
                break;
            }
        }
        if (agenda.isEmpty()){
            isTaken = false;
        }
        if (!hasContact) {
            if (isTaken){
                button.setEnabled(false);
                button.setBackground(Color.RED);
            }
            else{
                button.setEnabled(true);
                button.setBackground(new Color(45, 146, 220));
            }
        } else {
            if (isTaken){
                button.setEnabled(false);
                button.setBackground(Color.GRAY);
            }
            else{
                button.setEnabled(true);
                button.setBackground(Color.RED);
            }
        }
    }

    // INITIALISATION DES BOUTONS D HEURE MODIFIER
    private void choixHeureModif(){
        setButtonStateModif(m1, "06H00");
        setButtonStateModif(m2, "07H00");
        setButtonStateModif(m3, "08H00");
        setButtonStateModif(m4, "09H00");
        setButtonStateModif(m5, "10H00");
        setButtonStateModif(m6, "11H00");
        setButtonStateModif(m7, "12H00");
        setButtonStateModif(m8, "13H00");
        setButtonStateModif(m9, "14H00");
        setButtonStateModif(m10, "15H00");
        setButtonStateModif(m11, "16H00");
        setButtonStateModif(m12, "17H00");
        setButtonStateModif(m13, "18H00");
        setButtonStateModif(m14, "19H00");
        setButtonStateModif(m15, "20H00");
        setButtonStateModif(m16, "21H00");
        setButtonStateModif(m17, "22H00");
        setButtonStateModif(m18, "23H00");
    }

    /* =========================================================================================
                                       MAIN
    =========================================================================================*/
    public static void main(String[] args) {
        // CONNEXION A LA BASE DE DONNEES
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ReyVues app = context.getBean(ReyVues.class);

        // RECUPERER LA LISTE DES CONTACTS
        List<Contact> contacts = app.reyService.getAllContact();
        // INITIALISER LE MODEL DE TABLE AVEC LA LISTE DES CONTACTS
        ContactTableModel ContactTableModel = new ContactTableModel(contacts);
        app.tableContactContact.setModel(ContactTableModel);
        app.tableContactRdv.setModel(ContactTableModel);
        app.tableAjoutRdvContact.setModel(ContactTableModel);
        // RECUPERER LA LISTE DES RENDEZ-VOUS
        List<Agenda> agenda = app.reyService.getAllAgenda();
        // INITIALISER LE MODEL DE TABLE AVEC LA LISTE DES RENDEZ-VOUS
        AgendaTableModel agendaTableModel = new AgendaTableModel(agenda);
        app.tableRdvContact.setModel(agendaTableModel);
        app.tableRdvAgenda.setModel(agendaTableModel);

        JFrame frame = new JFrame("Rey");
        frame.pack();
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int height = tailleEcran.height;
        int width = tailleEcran.width;
        frame.setSize(width/2, height/2);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(app.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


