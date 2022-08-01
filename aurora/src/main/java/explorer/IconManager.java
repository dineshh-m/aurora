package explorer;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;

public class IconManager {

	

	public static Icon getIcon(String path) {
		IconManager iconManager = new IconManager();
		ImageIcon icon = iconManager.getIconpri(path);

		return icon;
	}

	private ImageIcon getIconpri(String path) {
		
	    ImageIcon icon = null;
		try {
			InputStream stream = getClass().getResourceAsStream("/icons/"+path+".png");
			icon = new ImageIcon(ImageIO.read(stream));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return icon;
	}
}
