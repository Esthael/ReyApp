package com.catcoatch.reyapp.vues;

import com.catcoatch.reyapp.entities.Agenda;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AgendaTableModel extends AbstractTableModel {

    private List<Agenda> agenda;

    private String[] columnNames = {"Date", "Heure"};

    public AgendaTableModel(List<Agenda> listAgenda) {
        this.agenda = listAgenda;
    }

    @Override
    public int getRowCount() {
        return agenda.size();
    }

    @Override
    public int getColumnCount() {return columnNames.length;}

    @Override
    public String getColumnName(int columnIndex) { return columnNames[columnIndex]; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Agenda columnAgenda = agenda.get(rowIndex);
        switch (columnIndex) {
            case 0: return columnAgenda.getDateRdv();
            case 1: return columnAgenda.getHeure();
            default: return null;
        }
    }
    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
        fireTableDataChanged();
    }

    // RECUPERE LA LISTE DES RENDEZ-VOUS
    public List<Agenda> getAgenda() {
        return agenda;
    }

    // RECUPERE L ID DU RENDEZ-VOUS DANS LA TABLE A PARTIR DE SON INDEX
    public Long getIdRdv(int row) {
        Agenda agenda1 = agenda.get(row);
        return agenda1.getIdCal();
    }

    // MODIFIER UN RENDEZ-VOUS SELECTIONNE
    public void updateRow(int ligneSelectionnee, Agenda agendas) {
        agenda.set(ligneSelectionnee, agendas);
        fireTableRowsUpdated(ligneSelectionnee, ligneSelectionnee);
    }
}
