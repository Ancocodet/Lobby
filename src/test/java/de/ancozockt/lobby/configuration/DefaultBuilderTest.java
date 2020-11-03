package de.ancozockt.lobby.configuration;

import de.ancozockt.utility.database.FileManager;
import org.junit.Test;

public class DefaultBuilderTest {

    @Test
    public void fileWillBeLoaded() {
        DefaultBuilder sut = new DefaultBuilder();
        sut.createTempFile("defaults/defaultconfiguration.yml", "test.dat", new FileManager("test"));
    }
}
