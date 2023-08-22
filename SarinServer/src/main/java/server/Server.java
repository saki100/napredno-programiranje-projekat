
package server;

import controller.Controller;
import domain.Trener;
import form.FormaServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.db.DbConnectionFactory;
import server.nit.ProcessClientsRequests;

/**
 *
 * @author Sara
 */
public class Server extends Thread{

    private List<ProcessClientsRequests> treneri;
    ServerSocket serverSocket;
    FormaServer formaServer;

    public Server(FormaServer formaServer) {
        this.formaServer = formaServer;
        treneri=new ArrayList<>();
    }

    
    @Override
    public void run() {
        try {
             serverSocket = new ServerSocket(9000);
            System.out.println("Server je pokrenut");
            while (serverSocket != null && !serverSocket.isClosed()) {

                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Connected!");
                handleClient(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket);
        processClientsRequests.setServer(this);
        treneri.add(processClientsRequests);
        processClientsRequests.start();
    }

    public void zaustaviServer() throws SQLException, IOException {
           

            for (ProcessClientsRequests t : treneri) {
                t.stopHandler();
            }
            
            if(serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
            serverSocket = null;
        }
               DbConnectionFactory.getInstance().disconnect();
       
    }
     public List<Trener> getUlogovaneTrenere()
    {
        List<Trener> lista = new ArrayList<>();
        for(ProcessClientsRequests p : treneri) {
            if(p.getTrener()!= null) {
                lista.add(p.getTrener());
            }
        }
        return lista;
    }
     public void removePcr(ProcessClientsRequests p)
    {
        treneri.remove(p);
    }
     public void osvezi(){
         formaServer.osveziListu();
     }
}

