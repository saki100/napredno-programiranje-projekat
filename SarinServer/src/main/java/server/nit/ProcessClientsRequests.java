
package server.nit;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import communication.StatusOdgovora;
import controller.Controller;
import domain.Clan;
import domain.Ishrana;
import domain.Obrok;
import domain.PlanIshrane;
import domain.PlanTreninga;
import domain.Trener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;


/**
 *
 * @author Sara
 */
public class ProcessClientsRequests extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;
    private Trener trener;
    Server server;  //dodato
    
    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;        
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
  public void stopHandler() throws IOException
    {
        if(this.socket != null) {
            if(!this.socket.isClosed()) {
                this.socket.close();
            }
        }
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

 

    @Override
    public void run() {
        

        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                       case LOGIN:
                            Trener t = (Trener) request.getArgument();
                            trener=(Trener)Controller.getInstance().login(t);
                            response.setResult(trener);
                            boolean aktivan = Controller.getInstance().vecJeUlogovan(trener);
                            if (aktivan) {
                            response.setException(new Exception("Korisnik je vec ulogovan"));
                                System.out.println("vec je ulogovan");
                        } else {
                            server.osvezi();
                                response.setStatus(StatusOdgovora.OK);
                                System.out.println("usao u else");
                                Controller.getInstance().getAktivniKorisnici().add(this);
                                sender.send(response);
                                //Kontroler.getInstanca().getFormaServer().dodajPrijavljenogKorisnika(korisnik);
                            } 
                                //response.setException(new Exception("Neuspesna prijava na sistem."));
                            
                            break;
                       case DODAJ_CLANA:
                           Clan clan=(Clan)request.getArgument();
                           Object rez=Controller.getInstance().addClan(clan);
                           if(!(rez instanceof Clan)) response.setException((Exception)rez);
                           else response.setResult(rez);
                            sender.send(response);
                            
                            break;
                       case VRATI_SVE_CLANOVE:
                            response.setResult(Controller.getInstance().getAllClanove());
                            sender.send(response);
                            break;
                       case PRETRAZI_CLANA:
                           response.setResult(Controller.getInstance().getClanovePoUslovu((Clan)request.getArgument()));
                           sender.send(response);
                           break;
                       case OBRISI_CLANA:
                            //Clan clan=(Clan)request.getArgument();
                           response.setResult(Controller.getInstance().deleteClana((Clan)request.getArgument()));
                           sender.send(response);
                           break;
                       case IZMENI_CLANA:
                           response.setResult(Controller.getInstance().editClana((Clan)request.getArgument()));
                           sender.send(response);
                           break;
                       case VRATI_SVE_PLANOVE_ISHRANE:
                           response.setResult(Controller.getInstance().getPlanovePoUslovu((Clan)request.getArgument()));
                           sender.send(response);
                           break;
                       case IZMENI_PLAN_ISHRANE:
                           response.setResult(Controller.getInstance().editPlanoveIshrane((List<PlanIshrane>)request.getArgument()));
                           sender.send(response);
                           break;
                       case DODAJ_PLAN_ISHRANE:
                           response.setResult(Controller.getInstance().addNovePlanoveIshrane((List<PlanIshrane>)request.getArgument()));
                           sender.send(response);
                           break;
                       case VRATI_SVE_ISHRANE:
                           List<Ishrana> ishrane=new ArrayList<>();
                           ishrane=(List<Ishrana>)Controller.getInstance().getIshranePoUslovu((Long)request.getArgument());
                           for(Ishrana i:ishrane){
                               Obrok o=Controller.getInstance().getObrokPoUslovu(i.getObrok());
                               i.setObrok(o);
                           }
                           response.setResult(ishrane);
                           sender.send(response);
                           break;
                       case VRATI_SVE_OBROKE:
                            response.setResult(Controller.getInstance().getAllObroke((Obrok)request.getArgument()));
                            sender.send(response);
                            break;
                       case IZMENI_ISHRANU:
                           response.setResult(Controller.getInstance().editIshrane((List<Ishrana>)request.getArgument()));
                            sender.send(response);
                            break;
                       case DODAJ_ISHRANU:
                           response.setResult(Controller.getInstance().addIshrane((List<Ishrana>)request.getArgument()));
                            sender.send(response);
                            break;
                       case VRATI_SVE_PLANOVE_TRENINGA:
                           response.setResult(Controller.getInstance().getPlanoveTreningaPoUslovu((Clan)request.getArgument()));
                           sender.send(response);
                           break;
                       case IZMENI_PLAN_TRENINGA:
                           response.setResult(Controller.getInstance().editPlanoveTreninga((List<PlanTreninga>)request.getArgument()));
                           sender.send(response);
                       case DODAJ_PLAN_TRENINGA:
                           response.setResult(Controller.getInstance().addNovePlanoveTreninga((List<PlanTreninga>)request.getArgument()));
                           sender.send(response);
                           break;
                        case DODAJ_OBROK:
                            
                            Controller.getInstance().addObrok((Obrok)request.getArgument());
                            break;
                        case IZMENI_OBROK:
                            response.setResult(Controller.getInstance().editObrok((Obrok)request.getArgument()));
                           sender.send(response);
                           break;
                        case VRATI_SVE_TRENERE:
                            response.setResult(Controller.getInstance().getAllTrenere());
                           sender.send(response);
                           break;
                        /*case DELETE_PRODUCT:
                            Product productDelete = (Product) request.getArgument();
                            Controller.getInstance().deleteProduct(productDelete);
                            break;
                        case ADD_INVOICE:
                            Invoice invoiceInsert = (Invoice) request.getArgument();
                            Controller.getInstance().addInvoice(invoiceInsert);
                            response.setResult(invoiceInsert);
                            break;*/
                    }
                //sender.send(response);
                }
                 catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                System.out.println("dosao do sendera");
                
                System.out.println("poslao odgovor");
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    

}

