/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesharing;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objekClientServer.objekClientServer;
/**
 *
 * @author evaria
 */
public class ThreadClient implements Runnable {
    private Socket sockClient;
    private ArrayList<ThreadClient> alThread;
    private BufferedReader br = null;
  
    private SocketAddress sa = null;
    private DataInputStream dis = null;
    private BufferedOutputStream bos = null;
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    private String namaUser = null;
    private File dstFile = null;
    private FileOutputStream fileOutputStream = null;
    private String statlogin = null;
    
    private ArrayList<String> listUserName;
    
    public ThreadClient(Socket sockClient, ArrayList<ThreadClient> alThread)
    {
        this.sockClient = sockClient;
        this.alThread = alThread;
        this.sa = sockClient.getRemoteSocketAddress();
        this.listUserName = new ArrayList<>();
    }
     

    @Override
    public void run() {
        try {
            bos = new BufferedOutputStream(getSockClient().getOutputStream());
            dis = new DataInputStream(getSockClient().getInputStream());
            outputStream = new ObjectOutputStream(getSockClient().getOutputStream());
            inputStream = new ObjectInputStream(getSockClient().getInputStream());
           // System.out.println(alThread);
            objekClientServer ocs = null;
            try {
               // ocs = (objekClientServer) inputStream.readObject();
                while((ocs = (objekClientServer)inputStream.readObject())!= null){
                if(ocs.getPerintah().equals("USER"))
                {
                    this.namaUser = ocs.getUsername();
                    System.out.println("USER " + ocs.getUsername());
                    File file = new File("f:/userpass.dat");
                    System.out.println(file.getName());
                    System.out.println(file);
                    if(file.isFile()){
                        DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                        int len = (int) file.length();
                        byte[] fileBytes = new byte[len];
                        int read =0;
                        int numRead = 0;
                       
                        while ( read < fileBytes.length && (numRead = diStream.read(fileBytes,read, fileBytes.length - read)) >= 0){
                            read = read + numRead;
                      }
                        String user =new String(fileBytes);
                        String[] aluserpass= user.split("\r\n");
                        //String statlogin= "GAGAL";
                        statlogin= "GAGAL";
                        System.out.println(ocs.getUsername()+":"+ocs.getPassw());
                        for(int i=0;i<aluserpass.length;i++)
                        {
                            System.out.println("cek user ke"+(i+1));
                            if(aluserpass[i].equals(ocs.getUsername()+":"+ocs.getPassw()))
                            {
                                System.out.println("login sukses");
                                statlogin="OK";
                                break;
                            }
                        }
                        if(statlogin.equals("OK"))System.out.println("USER OK");
                        else System.out.println("USER GAGAL");
                      /*  for(int i = 0; i<alThread.size();i++)
                        {
                            System.out.println("user aktif " + alThread.get(i).namaUser);
                        }*/
                    }
                }
                if(ocs.getPerintah().equals("UPLOAD")){
                   // ocs = (objekClientServer) inputStream.readObject();
                    if(ocs.getStatus().equalsIgnoreCase("Error")){
                        System.out.println("Error occured..");
                    }
                    String outputFile = ocs.getDestinationDirectory()+ocs.getFilename();
                    if(!new File(ocs.getDestinationDirectory()).exists()){
                        new File(ocs.getDestinationDirectory()).mkdirs();
                    }
                    dstFile = new File(outputFile);
                    fileOutputStream = new FileOutputStream(dstFile);
                    fileOutputStream.write(ocs.getFileData());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    System.out.println("Output file : " + outputFile + "is successfully saved");
                }
                if(ocs.getPerintah().equals("LISTUSER")){
                    System.out.println("masuk listuser");
                    for(int i = 0; i<alThread.size();i++)
                    {
                        System.out.println("masuk situ");
                         if((alThread.get(i).statlogin).equals("OK") && !alThread.get(i).namaUser.equals(getNamaUser())){
                            //if(!alThread.isEmpty()){
                            listUserName.add(alThread.get(i).namaUser);
                         }
                        listUserName.add(alThread.get(i).namaUser);
                    }
                    outputStream.writeObject(listUserName);
                    System.out.println("hehe");
                }
               }
               if(ocs.getPerintah().equals("LISTFOLDER")){
                    //System.out.println("masuk listfolder");
                    String filess = ocs.getDestinationDirectory()+ocs.getUsername();
                    //System.out.println(filess);
                    File directory = new File(filess);
                    File[] fList = directory.listFiles();
                    
                    for(File file : fList)
                    {
                        if (file.isFile())
                        {
                            //System.out.println(file.getName());
                            //System.out.println(file.getCanonicalPath());
                             listFiles.add(file.getName());
                        }                                      
                    }
                    outputStream.writeObject(listFiles);
                }
               if(ocs.getPerintah().equals("EXIT")){
                    System.out.println("masuk perintah EXIT");
                    statlogin="GAGAL";
                    sockClient.close();
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        /*    while((msg = br.readLine())!= null)
            {
                msg = this.sa.toString() + ":" + msg + "\r\n";
                this.sendMessage(msg);
            }*/
            System.out.println("Koneksi terputus");
            outputStream.close();
           // bos.close();
            getSockClient().close();
            synchronized(this.alThread)
            {
                this.alThread.remove(this);
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("disconnected");
            //this.alThread.remove(this);
        }
        
    }

    public Socket getSockClient() {
        return sockClient;
    }

    /**
     * @param sockClient the sockClient to set
     */
    public void setSockClient(Socket sockClient) {
        this.sockClient = sockClient;
    }
    public void send(String msg) throws IOException
    {
        this.bos.write(msg.getBytes());
        this.bos.flush();
    }
    
    public synchronized void sendMessage(String msg) throws IOException {
        for(int i=0;i<this.alThread.size();i++)
        {
            ThreadClient tc = this.alThread.get(i);
            tc.send(msg);
        }
    }

    /**
     * @return the namaUser
     */
    public String getNamaUser() {
        return namaUser;
    }

    /**
     * @param namaUser the namaUser to set
     */
    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }
}
