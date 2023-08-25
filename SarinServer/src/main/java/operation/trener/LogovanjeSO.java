/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.trener;

import domain.Trener;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja predstavlja logovanje Trenera.
 *
 * @author Sara
 */
public class LogovanjeSO extends AbstractGenericOperation{
    /**
     * Pronadjeni Trener iz baze podataka, cuva rezultat sistemske operacije.
     * Ukoliko je uspesno pronadjen bice ulogovan na sistem.
     */
    private Trener trener;
    
    
    @Override
  public void preconditions(Object param) throws Exception {
           
        if(!(param instanceof Trener)){
           throw new Exception("Greska, trener na postoji u bazi.");
       }
        else System.out.println("nadjen trener");
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         
        trener=(Trener) repository.getObject(param);
    }
    /**
     * Vraca Trenera koji predstavlja rezultat izvrsenja sistemske operacije.
     * 
     * @return Trener koga smo trazili po primarnom kljucu iz baze.
     */
    public Trener getTrener() {
        return trener;
    }
    /**
     * Postavlja atribute Trenera na odredjene vrednosti.
     * 
     * @param trener Trener kao Trener.
     */
    public void setTrener(Trener trener) {
        this.trener = trener;
    }
    
}

