/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesharing;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objekClientServer.objekClientServer;
/**
 *
 * @author evaria
 */
public class Filesharing {
    private Socket socket = null;
    private ObjectOutputStream outputStream = null;
    private boolean isConnected = false;
    private objekClientServer objekCS = null;
    
    public Filesharing(){
        
    }
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
   //        // TODO code application logic here
        ArrayList<ThreadClient> allThread = new ArrayList<>();
       ServerSocket ssServer = null;
        try {
            ssServer = new ServerSocket(6060);
        } catch (IOException ex) {
            Logger.getLogger(Filesharing.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("Menunggu panggilan....");
       while(true)
       {
           Socket sockClient = ssServer.accept();
           System.out.println(sockClient.getInetAddress().toString()+"masuk\r\n");
           synchronized (allThread)
           {
               ThreadClient tc = new ThreadClient(sockClient, allThread);
               allThread.add(tc);
               Thread t = new Thread(tc);
               t.start();
           }
               
       }
    }
    
}
