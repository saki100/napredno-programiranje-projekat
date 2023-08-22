package communication;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Saki
 */
public class Sender {

	private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public void send(Object object) throws Exception{
        try {
            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error sending object!\n"+ex.getMessage());
        }
    }
}
