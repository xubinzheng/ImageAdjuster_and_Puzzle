package editor_puzzle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class A7Helper {

	public static ColorFrame readFromURL(String url) throws IOException {
		BufferedImage bi = ImageIO.read(new URL(url));
		ColorFrame cf = new ColorFrame(bi.getWidth(), bi.getHeight());
		for (int x=0; x<bi.getWidth(); x++) {
			for (int y=0; y<bi.getHeight(); y++) {
				cf.setPixel(x, y, rgbToPixel(bi.getRGB(x, y)));
			}
		}
		return cf;
	}
	
	public static int pixelToRGB(Pixel p) {
		return ((((int) (p.getRed() * 255.0 + 0.5)) << 16) |
				(((int) (p.getGreen() * 255.0 + 0.5)) << 8) |
				(((int) (p.getBlue() * 255.0 + 0.5))));
	}

	public static Pixel rgbToPixel(int RGB) {
		double red = ((double) ((RGB >> 16) & 0xff)) / 255.0;
		double green = ((double) ((RGB >> 8) & 0xff)) / 255.0;
		double blue = ((double) (RGB & 0xff)) / 255.0;
		
		return new ColorPixel(red, green, blue);
	}


}
