package explorer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import app.Window;
import com.formdev.flatlaf.icons.FlatFileChooserNewFolderIcon;
import com.formdev.flatlaf.icons.FlatFileChooserUpFolderIcon;
import com.formdev.flatlaf.icons.FlatFileViewFileIcon;
import explorer.popup.ExplorerPopupMenu;

public class FileExplorer extends JPanel implements ActionListener{

	String dirPath;
	File directory;
	public DefaultMutableTreeNode rootNode;
	public DefaultTreeModel model;
	public JTree tree;
	JButton openFolder;
	boolean fileOpened = false;
	ClickListener clickListener;
	Window window;
	boolean folderOpened = false;
	ExplorerPopupMenu popupMenu;

	FlatFileViewFileIcon fileIcon;
	FlatFileChooserUpFolderIcon upFolder;
	
	public FileExplorer(Window window) {
		this.window = window;
		openFolder = new JButton("Open Folder");
		openFolder.setBackground(Color.decode("#4E86F9"));
		openFolder.setForeground(Color.white);

		openFolder.addActionListener(this);
//		this.setBackground(new Color(60, 56, 54));
		popupMenu = new ExplorerPopupMenu();

		fileIcon = new FlatFileViewFileIcon();
		upFolder = new FlatFileChooserUpFolderIcon();
		this.add(openFolder);

	}
	
	//Method to load files and folders CAUTION : Highly recursive
	private void displayFiles(DefaultMutableTreeNode root, File file) {
		File[] files = file.listFiles();
		
		for(File f : files) {
			
			if(f.isDirectory()) {
				DefaultMutableTreeNode sub = new DefaultMutableTreeNode(f.getName());
				sub.setAllowsChildren(true);      		//this line is not needed because it true by default but I added it for better recognition
				root.add(sub);
				displayFiles(sub, f);
			}else if(f.isFile()) {
				DefaultMutableTreeNode fi = new DefaultMutableTreeNode(f.getName());
				fi.setAllowsChildren(false);
				root.add(fi);
			}
		}
	}
	
	//
	private void openFolder() {
		
		JFileChooser chooser = new JFileChooser();
		int response;
		String path;
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		response = chooser.showOpenDialog(this);
		
		if(response == JFileChooser.APPROVE_OPTION) {
			
			directory = chooser.getSelectedFile();
			fileOpened = true;
		}else {
			
		}
		clickListener = null;
		clickListener = new ClickListener(directory, tree, window);
	}
	
	private void loadFiles() {
		rootNode = new DefaultMutableTreeNode(directory.getName());
		model = new DefaultTreeModel(rootNode, true);
		tree = new JTree();
		tree.addTreeSelectionListener(clickListener);
		tree.addMouseListener(clickListener);
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
			
			 @Override
	            public Component getTreeCellRendererComponent(JTree tree,
	                    Object value, boolean selected, boolean expanded,
	                    boolean isLeaf, int row, boolean focused) {
	                Component c = super.getTreeCellRendererComponent(tree, value,
	                        selected, expanded, isLeaf, row, focused);
	              DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
	              String str = (String) node.getUserObject();
	             // System.out.println(str);

//				 if(isLeaf) {
					 if(str.endsWith(".java")) {
						 setIcon(IconManager.getIcon("javac"));
					 }else if(str.endsWith(".py")) {
						 setIcon(IconManager.getIcon("python1"));
					 }else if(str.endsWith(".c")) {
						 setIcon(IconManager.getIcon("c"));
					 }else if(str.endsWith(".js")) {
						 setIcon(IconManager.getIcon("javascript"));
					 }else if(str.endsWith(".cpp")) {
						 setIcon(IconManager.getIcon("cpp"));
					 }else {
//						 setIcon(fileIcon);

					 }


	                return c;
	            }
			
		});
		displayFiles(rootNode, directory);
		tree.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if(actionCommand.equals("Open Folder")) {
			openFolder();
			if(directory != null) {
				loadFiles();
				this.remove(openFolder);
				this.setLayout(new BorderLayout());
				this.add(tree, BorderLayout.CENTER);
				this.updateUI();
				folderOpened = true;
			}
		}else if(actionCommand.equals("Open folder")) {
			openFolder();
			if(directory != null) {
				if(folderOpened)
					this.setNull();
				loadFiles();

				this.remove(openFolder);
				this.setLayout(new BorderLayout());
				this.add(tree, BorderLayout.CENTER);
				this.updateUI();
                folderOpened = true;
			}
		}

	}
	
	public JTree getTree() {
		return tree;
	}

	/*
	this method for resetting the jtree i.e file explorer
	 */
	public void setNull() {
		this.remove(tree);
	}

	public void refreshFileTree() {
		System.out.println(directory);
		if(directory != null) {
			loadFiles();
			System.out.println("Is directory" + directory.isDirectory());
			this.remove(tree);
			this.add(tree, BorderLayout.CENTER);
			this.updateUI();
		}
	}
}
