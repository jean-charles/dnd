package com.gayasystem.games.dnd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import javax.swing.*;

public abstract class SwingTestCase {
    private JFrame testFrame;

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "false");
    }

    @AfterEach
    protected void tearDown() throws Exception {
        if (this.testFrame != null) {
            this.testFrame.dispose();
            this.testFrame = null;
        }
    }

    public JFrame getTestFrame() {
        if (this.testFrame == null) {
            this.testFrame = new JFrame("Test");
        }
        return this.testFrame;
    }
}
