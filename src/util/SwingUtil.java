package util;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.net.URL;
import javax.swing.ImageIcon;
/*
 * Swing Help class
 */
public class SwingUtil {
	public static ImageIcon createAutoAdjustIcon(Image image, boolean constrained) {
     
		
		ImageIcon icon = new ImageIcon(image) {
            @Override
            public synchronized void paintIcon(java.awt.Component cmp, Graphics g, int x, int y) {
                Point startPoint = new Point(0, 0);
                Dimension cmpSize = cmp.getSize();
                Dimension imgSize = new Dimension(getIconWidth(), getIconHeight());
                

                if(constrained) {
                
                    double ratio = 1.0*imgSize.width/imgSize.height;
         
                    imgSize.width = (int) Math.min(cmpSize.width, ratio*cmpSize.height);
                    imgSize.height = (int) (imgSize.width/ratio);
                
                    startPoint.x = (int) 
                            (cmp.getAlignmentX()*(cmpSize.width - imgSize.width));
                    startPoint.y = (int) 
                            (cmp.getAlignmentY()*(cmpSize.height - imgSize.height));
                } else {
                    imgSize = cmpSize;
                }
                
                
                if(getImageObserver() == null) {
                    g.drawImage(getImage(), startPoint.x, startPoint.y,
                            imgSize.width, imgSize.height, cmp);
                 } else {
                    g.drawImage(getImage(), startPoint.x, startPoint.y,
                            imgSize.width, imgSize.height, getImageObserver());
                 }
            };
        };
        return icon;
    }
    
    public static ImageIcon createAutoAdjustIcon(String filename, boolean constrained) {
        return createAutoAdjustIcon(new ImageIcon(filename).getImage(), constrained);
    }
    

    public static ImageIcon createAutoAdjustIcon(URL url, boolean constrained) {
        return createAutoAdjustIcon(new ImageIcon(url).getImage(), constrained);
    }
}
