package com.catcoatch.reyapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cal")
    private Long idCal;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(name = "date_crea")
    private Date dateCrea;

    @Column(name = "date_rdv")
    private String dateRdv;

    @Column(name = "heure")
    private String heure;

    @ManyToOne
    @JoinColumn(name = "id_contact", referencedColumnName = "id_contact")
    private Contact contact;
}
