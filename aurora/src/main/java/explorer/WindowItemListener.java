package explorer;

import app.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowItemListener implements ActionListener {

    Window window;

    public WindowItemListener(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if(e.equals("Preferences")) {
            openPreferencesDialog();
        }
    }

    private void openPreferencesDialog() {
        JDialog preferDialog = new JDialog();
       // preferdialog
        preferDialog.setVisible(true);

    }
}
