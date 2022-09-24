package app;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import com.formdev.flatlaf.FlatDarculaLaf;
import editor.TabbedPane;
import explorer.FileExplorer;
import listeners.EditListener;
import listeners.FileListener;
import listeners.NewListener;
import listeners.ThemeHandler;
import statusbar.StatusBar;
import toolbar.ToolBar;
import values.Value;

public class Window extends JFrame{

	JMenuBar menuBar;
	JMenu file, edit, newItem, windowMenu, terminal, help, theme;
	JMenuItem  open, openFolder, save, saveAs, newFile, newFolder;  //for File menu

	JMenuItem cut, copy, paste;  //for Edit menu
	JMenuItem showHideTerminal;
	TabbedPane tabPane;
	public FileExplorer explorer;
	boolean folderOpened = false;
	NewListener newListener;
	public ArrayList<JLabel> list;
	public ArrayList<String> fileNameList;
	FileListener fileListener;
	StatusBar statusBar;
	EditListener editListener;
	ToolBar toolBar;
	ThemeHandler themeHandler; //Handling themes via Theme menu
	
	
	//temp
	public int i = 0;
	
	public Window() {
		//Border layout
		setLayout(new BorderLayout());

		int red = this.getBackground().getRed();
		int green = this.getBackground().getGreen();
		int blue = this.getBackground().getBlue();
		
		System.out.printf("%d, %d, %d", red, green, blue);
		
		list = new ArrayList<>();
		fileNameList = new ArrayList<>();
		menuBar = new JMenuBar();
		tabPane = new TabbedPane(this);
		explorer = new FileExplorer(this);
		newListener = new NewListener(this, tabPane, "untitled");
		fileListener = new FileListener(this, tabPane);
		statusBar = new StatusBar();
		editListener = new EditListener(this, tabPane);
		toolBar = new ToolBar();
		themeHandler = new ThemeHandler();
		
		
		
		file = createMenu(file, "File");
		edit = createMenu(edit, "Edit");
		windowMenu = createMenu(windowMenu, "Window");
		terminal = createMenu(terminal, "Terminal");
		help = createMenu(help, "Help");
		
		//File menu items
		newItem = createMenu(newItem, "New");
		open = createMenuItem(open, "Open");
		openFolder = createMenuItem(openFolder, "Open folder");
		save = createMenuItem(save, "Save");
		saveAs = createMenuItem(saveAs, "Save As");
		
		
		
		//setting up accelerators for save, saveAs, open
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveAs.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		
		addListener(fileListener, save, saveAs);
		addListener(explorer, openFolder); //adding the explorer as action listener beacuse of redundant code
		
		//sub menu for newItem
		newFile = createMenuItem(newItem, "File");
		newFile.addActionListener(newListener);
		newFolder = createMenuItem(newFolder, "Folder");
		newFolder.addActionListener(newListener);
		//setting up accelerators for file and folder sub menu in new
		newFile.setAccelerator(KeyStroke.getKeyStroke("control alt N"));
		newFolder.setAccelerator(KeyStroke.getKeyStroke("control alt F"));
		inflateMenu(newItem,  newFile, newFolder);
		
		//Edit menu items
		cut = createMenuItem(cut, "Cut");
		copy = createMenuItem(copy, "Copy");
		paste = createMenuItem(paste, "Paste");
		//adding listener to cut, copy, paste
		addListener(editListener, cut, copy, paste);
		//setting up accelerators for cut, copy, paste
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));

		//window menu items
		theme = createMenu(theme, "Theme");
		inflateJMenuWithString(Value.themes, theme);
		windowMenu.add(theme);

		//terminal menu items
		showHideTerminal = createMenuItem(showHideTerminal, "Show/Hide Terminal");
				
		//Inflate Menu
		inflateMenu(file,  newItem, open, openFolder, save, saveAs);  //File menu
		inflateMenu(edit,  cut, copy, paste);  //Edit menu
		inflateMenu(terminal, showHideTerminal); //Terminal Menu
		
		//inflate menu bar
		inflateMenuBar(menuBar, file, edit, windowMenu, terminal, help);
		
		
		JScrollPane scrollPane = new JScrollPane(explorer);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
//		//test code----------->
//		JSplitPane tabAndConsolePane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabPane, consolePane);
//		tabAndConsolePane.setDividerLocation(800);
//		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, tabAndConsolePane);
//		//----------------------->
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, tabPane);
		
		splitPane.setDividerLocation(250);
		this.add(splitPane, BorderLayout.CENTER);
	//	this.add(new JToolBar(), BorderLayout.NORTH);
		this.setJMenuBar(menuBar);
		this.add(statusBar, BorderLayout.SOUTH);
		this.add(toolBar, BorderLayout.NORTH);
		//Initializing method should be called finally
		initWindow();
	}
	
	//Initialization
	private void initWindow() {
		this.setIconImages(new Icon().getImageList());
		this.setSize(900, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(Value.APP_TITLE);
		this.setVisible(true);
	}
	
	private JMenu createMenu(JMenu menu, String text) {
		menu = new JMenu(text);
		
		return menu;
	}
	
	private  JMenuItem createMenuItem(JMenuItem menuItem, String text) {
		menuItem = new JMenuItem(text);
		
		return menuItem;
	}
	
	//To inflate Menu
	private void inflateMenu(JMenu menu, JMenuItem ...menuItems) {
		
		for(JMenuItem item : menuItems) {
			menu.add(item);
		}
	}
	
	//To inflate MenuBar with menus
	private void inflateMenuBar(JMenuBar menuBar, JMenu ...menus) {
		
		for(JMenu menu : menus) {
			menuBar.add(menu);
		}
	}
	
	private void addListener(ActionListener listener, JMenuItem ...menuItems) {
		for(JMenuItem menuItem : menuItems) {
			menuItem.addActionListener(listener);
		}
	}
	
	//getter for tabbedpane
	public TabbedPane getTabbedPane() {
		return this.tabPane;
	}
	
	//getter for status bar
	public StatusBar getStatusBar() {
		return this.statusBar;
	}

	//inflate the JMenu with the JMenuItem of string values
	public void inflateJMenuWithString(String values[], JMenu menu) {
		JMenuItem menuItem;

		for(String value : values) {
			menuItem = new JMenuItem(value);
			menuItem.addActionListener(themeHandler);
			menu.add(menuItem);
		}
	}
 }
