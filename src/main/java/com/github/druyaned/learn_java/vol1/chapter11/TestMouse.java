package com.github.druyaned.learn_java.vol1.chapter11;

import static com.github.druyaned.ConsoleColors.bold;
import static com.github.druyaned.learn_java.vol1.chapter11.Images.*;
import java.awt.*;

public class TestMouse {
    public static final int OFFSET = 0;
    public static final Cursor CURSOR_H;
    public static final Cursor CURSOR_F;
    public static final Cursor CURSOR_HG;
    
    static {
        Point point = new Point(OFFSET, OFFSET);
        final Toolkit TK = Toolkit.getDefaultToolkit();
        CURSOR_H = TK.createCustomCursor(makeCursorH(), point, "hand cursor");
        CURSOR_F = TK.createCustomCursor(makeCursorF(), point, "finger cursor");
        CURSOR_HG = TK.createCustomCursor(makeCursorHG(), point, "green hand cursor");
    }

    public static void run() {
        System.out.println("\n" + bold("Testing mouse actions."));

        EventQueue.invokeLater(() -> {
            MouseFrame mouseFrame = new MouseFrame();
            mouseFrame.setVisible(true);
        });
    }
}
