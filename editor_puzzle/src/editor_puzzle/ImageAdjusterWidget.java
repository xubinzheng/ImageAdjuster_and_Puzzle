package editor_puzzle;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterWidget extends JPanel implements ChangeListener{
	private FrameView frame_view;
	private Frame f;
	JPanel rp;
	private JSlider blur_slider;
	private JSlider saturation_slider;
	private JSlider brightness_slider;
	JLabel blurlabel, saturationlabel, brightnesslabel;
	private ColorFrame frame;
	
	public ImageAdjusterWidget(Frame f) {
		this.f=f;
		
		frame = new ColorFrame(f.getWidth(),f.getHeight());
		for(int i=0;i<f.getWidth();i++){
			for(int j=0;j<f.getHeight();j++){
				 frame.setPixel(i, j, f.getPixel(i, j));                  
			}
		}
		
		setLayout(new BorderLayout());
		frame_view = new FrameView(f);
		add(frame_view, BorderLayout.CENTER);
	
		rp = new JPanel();
		rp.setPreferredSize(new Dimension(700, 250));
		
		blur_slider = new JSlider(0,5,0);
		blur_slider.setPaintTicks(true);
		blur_slider.setSnapToTicks(true);
		blur_slider.setPaintLabels(true);
		blur_slider.setMajorTickSpacing(1);
		
		saturation_slider = new JSlider(-100,100,0);
		saturation_slider.setPaintTicks(true);
		//saturation_slider.setSnapToTicks(true);
		saturation_slider.setPaintLabels(true);
		saturation_slider.setMajorTickSpacing(25);
		
		brightness_slider = new JSlider(-100,100,0);
		brightness_slider.setPaintTicks(true);
		//brightness_slider.setSnapToTicks(true);
		brightness_slider.setPaintLabels(true);
		brightness_slider.setMajorTickSpacing(25);
		
		Dimension slider_dim = new Dimension(500,50);
		
		blurlabel = new JLabel("Blur");
		blur_slider.setPreferredSize(slider_dim);
		saturationlabel = new JLabel("Saturation");
		saturation_slider.setPreferredSize(slider_dim);
		brightnesslabel = new JLabel("Brightness");
		brightness_slider.setPreferredSize(slider_dim);
		
		
		rp.add(blur_slider);
		rp.add(blurlabel);
		rp.add(saturation_slider);
		rp.add(saturationlabel);
		rp.add(brightness_slider);
		rp.add(brightnesslabel);
		
		
		add(rp, BorderLayout.SOUTH);
		
		blur_slider.addChangeListener(this);
		saturation_slider.addChangeListener(this);
		brightness_slider.addChangeListener(this);
	
		
	}
	public void stateChanged(ChangeEvent e) {
		
		
		int blur_value = blur_slider.getValue();
		int saturation_value = saturation_slider.getValue();
		int brightness_value = brightness_slider.getValue();
		
		
		imageStatus(blur_value, saturation_value, brightness_value);
		
		
		System.out.println("BLUR: "+blur_value);
		System.out.println("SATURATION: "+saturation_value);
		System.out.println("BRIGHTNESS: "+brightness_value);
		
	}
	
  
	 
	public void imageStatus(int blur_value, int saturation_value,int brightness_value){

		
		
		int blurvalue = blur_value;
		int saturationvalue = saturation_value;
		int brightnessvalue= brightness_value;
		
		double redvalue,greenvalue,bluevalue;
		double redaverage = 0,greenaverage = 0,blueaverage = 0;
		
		
		for (int y = 0; y<f.getHeight(); y++) {
			for (int x = 0; x<f.getWidth(); x++) {
				
				 redaverage = 0;
				 greenaverage = 0;
				 blueaverage = 0;
				 int n=0;
				 
				for(int horvalue = -blurvalue; horvalue <= blurvalue; horvalue++){
					for(int vervalue = -blurvalue; vervalue <= blurvalue; vervalue++){
						
						if(x+horvalue >= 0 && y+vervalue >= 0 && x+horvalue<=f.getWidth()-1 && y+vervalue<=f.getHeight()-1){
							redaverage=redaverage + frame.getPixel(x+horvalue, y+vervalue).getRed();
							blueaverage=blueaverage +frame.getPixel(x+horvalue, y+vervalue).getBlue();
							greenaverage=greenaverage + frame.getPixel(x+horvalue, y+vervalue).getGreen();
						}
						else{
							redaverage=redaverage + 0;
							blueaverage=blueaverage + 0;
							greenaverage=greenaverage + 0;
							n=n+1;
						}
					}
				}
				redvalue=redaverage/((blurvalue*2+1)*(blurvalue*2+1)-n);
				bluevalue=blueaverage/((blurvalue*2+1)*(blurvalue*2+1)-n);
				greenvalue=greenaverage/((blurvalue*2+1)*(blurvalue*2+1)-n);
				
				ColorPixel p = new ColorPixel(redvalue, greenvalue, bluevalue);
				f.setPixel(x, y, p);
				
				
				if(brightnessvalue>=0){
					
					
					
					double newred = f.getPixel(x, y).getRed() * (1.0 - brightnessvalue/100.0) + brightnessvalue/100.0;
					double newgreen = f.getPixel(x, y).getGreen() * (1.0 - brightnessvalue/100.0) + brightnessvalue/100.0;
					double newblue = f.getPixel(x, y).getBlue() * (1.0 - brightnessvalue/100.0) + brightnessvalue/100.0;
					
					ColorPixel p2 = new ColorPixel(newred, newgreen, newblue);
					f.setPixel(x, y, p2);
					
			
			
				}
				else{
					double newred = f.getPixel(x, y).getRed() * (1.0 - brightnessvalue/100.0);
					double newgreen = f.getPixel(x, y).getGreen() * (1.0 - brightnessvalue/100.0);
					double newblue = f.getPixel(x, y).getBlue() * (1.0 - brightnessvalue/100.0);
					
					ColorPixel p2 = new ColorPixel(newred, newgreen, newblue);
					f.setPixel(x, y, p2);
					
		
				}
				
				if(saturationvalue<=0){
				
							double newred = f.getPixel(x, y).getRed() * (1.0  + (saturationvalue / 100.0 )) - (frame.getPixel(x, y).getBrightness() * saturationvalue / 100.0);
							double newgreen = f.getPixel(x, y).getGreen() * (1.0  + (saturationvalue / 100.0 )) - (frame.getPixel(x, y).getBrightness() * saturationvalue / 100.0);
							double newblue = f.getPixel(x, y).getBlue() * (1.0  + (saturationvalue / 100.0 )) - (frame.getPixel(x, y).getBrightness() * saturationvalue / 100.0);
							ColorPixel p3 = new ColorPixel(newred, newgreen, newblue);
							f.setPixel(x, y, p3);
				
					
					
					
				}
				else{
					
							double large =0;
							
							if(f.getPixel(x, y).getRed()>=f.getPixel(x, y).getGreen()){
								large =f.getPixel(x, y).getRed();
							}
							else{
								large = f.getPixel(x, y).getGreen();
							}
							
							if(large>=f.getPixel(x, y).getBlue()){	
							}
							else{
								large = f.getPixel(x, y).getBlue();
							}
							
							
							double gain = (large + ((1.0 - large) * (saturationvalue / 100.0))) / large;
							double newred = f.getPixel(x, y).getRed()*gain;
							double newgreen = f.getPixel(x, y).getGreen()*gain;
							double newblue = f.getPixel(x, y).getBlue() *gain;
							
							ColorPixel p3 = new ColorPixel(newred, newgreen, newblue);
							f.setPixel(x, y, p3);
				
				}
			}
		}
	}
	
	

}
