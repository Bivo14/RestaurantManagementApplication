package presentation;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageClass 
{
	public ImageClass()
	{
		
	}
	
	public JLabel addImage(String path)
	{
		JLabel picLabel = new JLabel();
		picLabel.setOpaque(false);
		ImageIcon icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(295, 400, Image.SCALE_SMOOTH));
		picLabel.setIcon(icon);
		return picLabel;
	}
}
