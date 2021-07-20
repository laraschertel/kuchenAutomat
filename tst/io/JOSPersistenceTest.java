package io;

import automat.AutomatException;
import automat.AutomatPlaceHolder;
import automat.AutomatVerwaltung;
import automat.HerstellerImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class JOSPersistenceTest {

    @Test
    void serialize(){
        ObjectOutputStream oos=mock(ObjectOutputStream.class);
        AutomatPlaceHolder automatPlaceHolder = mock(AutomatPlaceHolder.class);
        JOSPersistence josPersistence = new JOSPersistence(automatPlaceHolder);
        try {
            josPersistence.serialize(oos);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        try {
            verify(oos).writeObject(any());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void deserialize() {
        ObjectInputStream ois=mock(ObjectInputStream.class);
        AutomatPlaceHolder automatPlaceHolder = mock(AutomatPlaceHolder.class);
        JOSPersistence josPersistence = new JOSPersistence(automatPlaceHolder);
        try {
            josPersistence.deserialize(ois);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
            try {
                verify(ois).readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                fail();
            } catch (IOException e) {
                e.printStackTrace();
                fail();
            }
        }
}
