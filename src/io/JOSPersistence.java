package io;

import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import observerPattern.Beobachter;


import java.io.*;
import java.util.LinkedList;

public class JOSPersistence {
    private AutomatPlaceHolder automatPlaceHolder;


    public JOSPersistence(AutomatPlaceHolder automatPlaceHolder){
        this.automatPlaceHolder = automatPlaceHolder;
    }


    public void serialize() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("automat.ser"));
        try{
            objectOutputStream.writeObject(this.automatPlaceHolder.getAutomat());
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    public void serialize(ObjectOutputStream objectOutputStream) throws IOException {
        try{
            objectOutputStream.writeObject(this.automatPlaceHolder.getAutomat());
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void deserialize() throws IOException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("automat.ser"));
        AutomatVerwaltung automat = null;
        try{
            automat = (AutomatVerwaltung) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.automatPlaceHolder.setAutomat(automat);
    }



    public void deserialize(ObjectInputStream objectInputStream) throws IOException{
        AutomatVerwaltung automat = null;
        try{
            automat = (AutomatVerwaltung) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.automatPlaceHolder.setAutomat(automat);
    }

}
