package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.mockito.Mockito.mock;

class OutputEventCollectionTest {

    @Test
    void getCollection() {

        Collection collection = mock(Collection.class);
        OutputEventCollection outputEventCollection = new OutputEventCollection(this, "text", collection);
        Assertions.assertEquals(collection, outputEventCollection.getCollection());
    }
}