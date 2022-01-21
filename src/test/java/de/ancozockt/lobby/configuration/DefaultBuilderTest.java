package de.ancozockt.lobby.configuration;

import de.ancozockt.utility.database.FileManager;
import org.junit.Test;

public class DefaultBuilderTest {

    private final FileManager fileManager = new FileManager("test");

    @Test
    public void testTempConfiguration() {
        DefaultBuilder sut = new DefaultBuilder();
        sut.createTempFile("defaults/defaultconfiguration.yml", "testconf.dat", new FileManager("test"));

        assert fileManager.getTempFile("testconf.dat").exists();
        assert fileManager.getTempFile("testconf.dat").delete();
        assert !fileManager.getTempFile("testconf.dat").exists();
    }

    @Test
    public void testTempHotBar() {
        DefaultBuilder sut = new DefaultBuilder();
        sut.createTempFile("defaults/defaulthotbar.yml", "testhotbar.dat", new FileManager("test"));

        assert fileManager.getTempFile("testhotbar.dat").exists();
        assert fileManager.getTempFile("testhotbar.dat").delete();
        assert !fileManager.getTempFile("testhotbar.dat").exists();
    }

    @Test
    public void testTempLang() {
        DefaultBuilder sut = new DefaultBuilder();
        sut.createTempFile("defaults/defaultlanguage.yml", "testlang.dat", new FileManager("test"));

        assert fileManager.getTempFile("testlang.dat").exists();
        assert fileManager.getTempFile("testlang.dat").delete();
        assert !fileManager.getTempFile("testlang.dat").exists();
    }

    @Test
    public void testTemNav() {
        DefaultBuilder sut = new DefaultBuilder();
        sut.createTempFile("defaults/defaultnavigator.yml", "testnav.dat", new FileManager("test"));

        assert fileManager.getTempFile("testnav.dat").exists();
        assert fileManager.getTempFile("testnav.dat").delete();
        assert !fileManager.getTempFile("testnav.dat").exists();
    }

    @Test
    public void testTemShop() {
        DefaultBuilder sut = new DefaultBuilder();
        sut.createTempFile("defaults/defaultshop.yml", "testshop.dat", new FileManager("test"));

        assert fileManager.getTempFile("testshop.dat").exists();
        assert fileManager.getTempFile("testshop.dat").delete();
        assert !fileManager.getTempFile("testshop.dat").exists();
    }
}
