package controller;


import communication.StatusOdgovora;
import domain.Clan;
import domain.Ishrana;
import domain.Obrok;
import domain.PlanIshrane;
import domain.PlanTreninga;
import domain.Trener;
import form.FormaServer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import operation.clan.IzmeniClana;
import operation.clan.ObrisiClana;
import operation.clan.PretraziClana;
import operation.clan.UcitajListuClanova;
import operation.clan.ZapamtiClana;
import operation.ishrana.DodajIshranu;
import operation.ishrana.IzmeniIshranu;
import operation.ishrana.PretraziIshrane;
import operation.obrok.DodajObrok;
import operation.obrok.IzmeniObrok;
import operation.obrok.PretraziObrok;
import operation.obrok.UcitajListuObroka;
import operation.planIshrane.DodajPlanIshrane;
import operation.planIshrane.IzmeniPlanIshrane;
import operation.planIshrane.PretraziPlanoveIshrane;
import operation.planTreninga.DodajPlanTreninga;
import operation.planTreninga.IzmeniPlanTreninga;
import operation.planTreninga.PretraziPlanoveTreninga;
import operation.trener.LogovanjeSO;
import operation.trener.UcitajTrenere;
import repository.Repository;
import repository.db.impl.RepositoryDBGeneric;
import server.nit.ProcessClientsRequests;

/**
 *
 * @author Sara
 */
public class Controller {
    
    private static Controller controller;
    private ArrayList<ProcessClientsRequests> aktivniKorisnici;
    private FormaServer forma;
    
    public Controller() {
        this.aktivniKorisnici=new ArrayList<>();
    }
    
    
    
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    public void setForma(FormaServer forma) {
        this.forma = forma;
    }

    public FormaServer getFormaServer() {
        return forma;
    }

    public void FormaServer(FormaServer forma) {
        this.forma = forma;
    }
    public Object login(Object obj) throws Exception {
        LogovanjeSO login=new LogovanjeSO();
        System.out.println(obj.toString());
        login.execute(obj);
        System.out.println(obj.toString());
        System.out.println(login.getTrener().toString());
        return login.getTrener();
    }

    public boolean vecJeUlogovan(Trener trener) {
     for (ProcessClientsRequests klijent : aktivniKorisnici) {
            if (klijent.getTrener().equals(trener)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ProcessClientsRequests> getAktivniKorisnici() {
        return aktivniKorisnici;
    }

    public void setAktivniKorisnici(ArrayList<ProcessClientsRequests> aktivniKorisnici) {
        this.aktivniKorisnici = aktivniKorisnici;
    }
    public Object addClan(Clan clan)  {
        ZapamtiClana zcl=new ZapamtiClana();
        try {
            zcl.execute(clan);
            System.out.println(clan);
        return zcl.getClan();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex;
        }
                
    }
    public List<Clan> getAllClanove() throws Exception{
        
        UcitajListuClanova ulc=new UcitajListuClanova();
        ulc.execute(new Clan());
        return ulc.getClanove();
    }
    public List<Clan> getClanovePoUslovu(Clan clan) throws Exception{
        PretraziClana pc=new PretraziClana();
        pc.execute(clan);
        return pc.getClanovi();
    }

    public Object deleteClana(Clan clan) throws Exception {
        ObrisiClana oc=new ObrisiClana();
        oc.execute(clan);
        return StatusOdgovora.OK.toString();
    }

    public Object editClana(Clan clan) throws Exception {
        IzmeniClana ic=new IzmeniClana();
        ic.execute(clan);
        return StatusOdgovora.OK.toString();
    }

    public Object getPlanovePoUslovu(Clan clan) throws Exception {
      PretraziPlanoveIshrane ppi=new PretraziPlanoveIshrane();
      ppi.execute(clan);
      return ppi.getPlanoveIshrane();
    }

    public Object editPlanoveIshrane(List<PlanIshrane> list) throws Exception {
        IzmeniPlanIshrane ipl=new IzmeniPlanIshrane();
        for(PlanIshrane pi:list)
            ipl.execute(pi);
        return StatusOdgovora.OK.toString();
    }

    public Object addNovePlanoveIshrane(List<PlanIshrane> list) throws Exception {
         DodajPlanIshrane dpi=new DodajPlanIshrane();
         for(PlanIshrane pi:list)
             dpi.execute(pi);
         return StatusOdgovora.OK.toString();
         
    }

    public Object getIshranePoUslovu(long planIshraneID) throws Exception {
      PlanIshrane p=new PlanIshrane(); p.setIshranaID(planIshraneID);
      Ishrana i=new Ishrana(); i.setPlanIshrane(p);
        PretraziIshrane pi=new PretraziIshrane();
        pi.execute(i);
        return pi.getIshrane();
    }

    public Obrok getObrokPoUslovu(Obrok obrok) throws Exception {
       PretraziObrok po=new PretraziObrok();
       po.execute(obrok);
       return po.getO();
    }

    public Object getAllObroke(Obrok obrok) throws Exception {
      UcitajListuObroka ulo=new UcitajListuObroka();
      ulo.execute(obrok);
      return ulo.getObroci();
    }

    public Object editIshrane(List<Ishrana> list) throws Exception {
     IzmeniIshranu ii=new IzmeniIshranu();
     for(Ishrana i:list) ii.execute(i);
     return StatusOdgovora.OK.toString();
    }

    public Object addIshrane(List<Ishrana> list) throws Exception {
        DodajIshranu di=new DodajIshranu();
        for(Ishrana i:list) di.execute(i);
        return StatusOdgovora.OK.toString();
    }

    public Object getPlanoveTreningaPoUslovu(Clan clan) throws Exception {
          PretraziPlanoveTreninga ppt=new PretraziPlanoveTreninga();
          ppt.execute(clan);
          return ppt.getPlanovi();
    }

    public Object editPlanoveTreninga(List<PlanTreninga> list) throws Exception {
     IzmeniPlanTreninga ipt=new IzmeniPlanTreninga();
     for(PlanTreninga p:list) ipt.execute(p);
     return StatusOdgovora.OK.toString();
    }

    public Object addNovePlanoveTreninga(List<PlanTreninga> list) throws Exception {
           DodajPlanTreninga dpt=new DodajPlanTreninga();
         for(PlanTreninga pt:list)
             dpt.execute(pt);
         return StatusOdgovora.OK.toString();
    }

    public void addObrok(Obrok obrok) throws Exception {
        DodajObrok dod=new DodajObrok();
        dod.execute(obrok);
        
    }

    public Object editObrok(Obrok obrok) throws Exception {
        IzmeniObrok io=new IzmeniObrok();
        io.execute(obrok);
        return "OK";
    }

    public Object getAllTrenere() throws Exception {
      UcitajTrenere ut=new UcitajTrenere();
      ut.execute(new Trener());
      return ut.getTreneri();
    }
    
}

