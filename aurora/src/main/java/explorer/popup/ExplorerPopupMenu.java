package explorer.popup;

import javax.swing.*;

public class ExplorerPopupMenu extends JPopupMenu {

    //Menu goes here
    JMenu New;


    //Menu items goes here
    JMenuItem file, folder,cut, copy, paste, delete;

    public ExplorerPopupMenu() {
        //initializing menu
        New = createMenu(New, "New");


        //initializing menuitems
        file = createMenuItem(file, "File");
        folder = createMenuItem(folder, "Folder");

        cut = createMenuItem(cut, "Cut");
        copy = createMenuItem(copy, "Copy");
        paste = createMenuItem(paste, "Paste");
        delete = createMenuItem(delete, "Delete");

        //inflating menu
        inflateMenu(New, file, folder);

        //inflate popup
        this.add(New);
        this.add(cut);
        this.add(copy);
        this.add(paste);
        this.add(delete);
    }

    private JMenu createMenu(JMenu menu, String string) {
        menu = new JMenu(string);

        return menu;
    }

    private JMenuItem createMenuItem(JMenuItem menuItem, String string) {
        menuItem = new JMenuItem(string);

        return menuItem;
    }

    private void inflateMenu(JMenu menu, JMenuItem ...menuItems) {
        for(JMenuItem menuItem: menuItems) {
            menu.add(menuItem);
        }
    }


}
