package listeners;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemeHandler implements ActionListener {

    public ThemeHandler() {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        try {
            switch(actionCommand) {
                case "Darcula":
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                    FlatDarculaLaf.setup();
                    FlatDarculaLaf.updateUI();
                    break;
                case "Dark":
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    FlatDarkLaf.setup();
                    FlatDarkLaf.updateUI();
                    break;
                case "Intellij":
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    FlatIntelliJLaf.setup();
                    FlatIntelliJLaf.updateUI();
                    break;
            }
        }catch(UnsupportedLookAndFeelException ulaf) {
            System.out.println("Unsupported laf: "+ulaf.getMessage());
        }
    }
}
