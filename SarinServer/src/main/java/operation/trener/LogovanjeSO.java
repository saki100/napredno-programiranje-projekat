/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.trener;

import domain.Trener;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class LogovanjeSO extends AbstractGenericOperation{

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

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }
    
}

