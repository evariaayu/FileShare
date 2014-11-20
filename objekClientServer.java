/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekClientServer;

import java.io.Serializable;

/**
 *
 * @author evaria
 */
public class objekClientServer implements Serializable{
    public objekClientServer(){
        
    }
    private static final long serialVersionUID = 1L;
    private String username;
    private String passw;
    private String destinationDirectory;
    private String sourceDirectory;
    private String filename;
    private long fileSize;
    private byte[] fileData;
    private String status;
    private String perintah;
    private String respon;
    private String namaFolder;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the destinationDirectory
     */
    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    /**
     * @param destinationDirectory the destinationDirectory to set
     */
    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    /**
     * @return the sourceDirectory
     */
    public String getSourceDirectory() {
        return sourceDirectory;
    }

    /**
     * @param sourceDirectory the sourceDirectory to set
     */
    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the fileSize
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the fileData
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * @param fileData the fileData to set
     */
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the passw
     */
    public String getPassw() {
        return passw;
    }

    /**
     * @param passw the passw to set
     */
    public void setPassw(String passw) {
        this.passw = passw;
    }

    /**
     * @return the perintah
     */
    public String getPerintah() {
        return perintah;
    }

    /**
     * @param perintah the perintah to set
     */
    public void setPerintah(String perintah) {
        this.perintah = perintah;
    }

    /**
     * @return the respon
     */
    public String getRespon() {
        return respon;
    }

    /**
     * @param respon the respon to set
     */
    public void setRespon(String respon) {
        this.respon = respon;
    }

    /**
     * @return the namaFolder
     */
    public String getNamaFolder() {
        return namaFolder;
    }

    /**
     * @param namaFolder the namaFolder to set
     */
    public void setNamaFolder(String namaFolder) {
        this.namaFolder = namaFolder;
    }
    
}
