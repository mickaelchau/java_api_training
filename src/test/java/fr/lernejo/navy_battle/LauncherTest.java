package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LauncherTest {
    @Test
    void invalidLaunch() {
        String[] args = {};
        Launcher.main(args);
        Assertions.assertTrue(true);
    }

    @Test
    void validLaunch() {
        String[] args = {"8888"};
        Launcher.main(args);
        Assertions.assertTrue(true);
    }

    @Test
    void validLaunch2Args() {
        String[] args = {"9999", "http://localhost:7777"};
        Launcher.main(args);
        Assertions.assertTrue(true);
    }
}
