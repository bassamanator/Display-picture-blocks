import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

class ShowPicture {
	public static int blockX = 33;
	public static int blockY = 24;
	public static int rangeX = 31;
	public static int rangeY = 32;
	
	public static BufferedImage drawBlock(int rx, int ry, BufferedImage imgIn, BufferedImage imgOut) {
		for (int x = 0;  x < blockX; x++) {
			for (int y = 0; y < blockY; y++) {
				int p=imgIn.getRGB((rx * blockX) + x,(ry * blockY)+y);
				imgOut.setRGB((rx * blockX) + x,(ry * blockY)+y,p);
			}
		} return imgOut;
	}
	
	public static void main(String args[]) {
		BufferedImage imgIn = null;
		BufferedImage imgOut = null;
		File f = null;
		ArrayList<Integer> masterX = new ArrayList<Integer>();
		ArrayList<Integer> masterY = new ArrayList<Integer>();
		
		//read image IN
		try{
			f = new File("/media/wd1tb/taj.jpg");
			imgIn = ImageIO.read(f);
		}catch(IOException e){
			System.out.println(e);
		}
		//read image OUT
		try{
			f = new File("/media/wd1tb/blank.jpg");
			imgOut = ImageIO.read(f);
		}catch(IOException e){
			System.out.println(e);
		}
		
		//add x, y coordinates to masterlist
		for (int x = 0; x < rangeX ;  x++) {			
			for (int y = 0; y < rangeY;  y++) {
				masterX.add(x) ;
				masterY.add(y) ;
			}
		} 
		
		//drawblocks
		for (int x = 0; x < 100; x++) {
			Random rand = new Random();
			int n = rand.nextInt(masterX.size());
			imgOut=drawBlock(masterX.get(n),masterY.get(n),imgIn, imgOut);
			masterX.remove(n);
			masterY.remove(n);
		}
	
		//write image
		try{
			f = new File("/media/wd1tb/Output.jpg");
			ImageIO.write(imgOut, "jpg", f);
		}catch(IOException e){
			System.out.println(e);
		}
		
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon("/media/wd1tb/Output.jpg");
		JLabel label = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		label.repaint();
		try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		
	}
}