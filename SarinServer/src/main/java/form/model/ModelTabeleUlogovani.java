/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.model;


import domain.Trener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milaz
 */
public class ModelTabeleUlogovani extends AbstractTableModel {

    String[] kolone = {"Ime i prezime", "Korisnicko ime"};
    private List<Trener> treneri;

    public ModelTabeleUlogovani(List<Trener> treneri) {
        this.treneri = treneri;
    }

    

    @Override
    public int getRowCount() {
        return treneri.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Trener t = treneri.get(row);
        switch (column) {
            case 0:
                return t.getIme() + " " + t.getPrezime();
            case 1:
                return t.getUsername();
            default:
                return "/n";
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public List<Trener> getKorisnici() {
        return treneri;
    }

    public void setTreneri(ArrayList<Trener> treneri) {
        this.treneri = treneri;
    }


    public void dodaj(Trener t) {
        treneri.add(t);
        fireTableDataChanged();
    }

    public void obrisi(Trener t) {
        treneri.remove(t);
        fireTableDataChanged();
    }

    public void odjaviSe(Trener t) {
        treneri.remove(t);
        fireTableDataChanged();
    }

    public void obrisiSve() {
        this.treneri = new ArrayList<>();
    }

}
