package com.catcoatch.reyapp.entities;

import com.catcoatch.reyapp.enums.EnumSexe;
import com.catcoatch.reyapp.enums.EnumTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long idContact;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_crea")
    @CreationTimestamp
    private Date dateCrea;

    private String nom;
    private String prenom;
    private String description;

    @Column(name = "email", unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "types")
    private EnumTypes types;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private EnumSexe sexe;

    @OneToMany(mappedBy = "contact", fetch = FetchType.EAGER)
    private List<Agenda> rdv;
}
