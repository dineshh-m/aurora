package app;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.formdev.flatlaf.icons.FlatClearIcon;

public class Icon {
	
	public static FlatClearIcon clearIcon = new FlatClearIcon();

	public List<? extends Image> getImageList() {
		
		final List<Image> icons = new ArrayList<Image>();
		try {
			icons.add(ImageIO.read(getClass().getResourceAsStream("/icons/aurora16.png")));
			icons.add(ImageIO.read(getClass().getResourceAsStream("/icons/aurora32.png")));
			icons.add(ImageIO.read(getClass().getResourceAsStream("/icons/aurora64.png")));
			icons.add(ImageIO.read(getClass().getResourceAsStream("/icons/aurora128.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return icons;
	}
}
