package editor_puzzle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class PixelInspectorWidget extends JPanel implements MouseListener {

	private FrameView frame_view;
	private Frame f;
	JPanel rp;
	JLabel redStatus, blueStatus,greenStatus,xStatus,yStatus,brightnessStatus;
	
	public PixelInspectorWidget(Frame f) {
		setLayout(new BorderLayout());
		this.f=f;
		
		frame_view = new FrameView(f);
		frame_view.addMouseListener(this);
		add(frame_view, BorderLayout.CENTER);
		
		JLabel title_label = new JLabel(f.getTitle());
		add(title_label, BorderLayout.SOUTH);
	
		rp = new JPanel();
		rp.setPreferredSize(new Dimension(120, 120));
		add(rp, BorderLayout.WEST);
	
		xStatus = new JLabel("X : null ");
		yStatus = new JLabel("Y : null ");
		redStatus = new JLabel("Red : null"); 
		greenStatus = new JLabel("Green : null");
		blueStatus = new JLabel("Blue : null");
		brightnessStatus = new JLabel("Brightness : null"); 
		
		
		rp.add(xStatus, BorderLayout.WEST);
		rp.add(yStatus, BorderLayout.WEST);
		rp.add(redStatus, BorderLayout.WEST);
		rp.add(greenStatus, BorderLayout.WEST);
		rp.add(blueStatus, BorderLayout.WEST);
		rp.add(brightnessStatus, BorderLayout.WEST);
	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.println("You clicked on the frame at: " + e.getX() + "," + e.getY());
		System.out.println("X:" + e.getX());
		System.out.println("Y:" + e.getY());
		
		double redvalue = f.getPixel(e.getX(),e.getY()).getRed();
		double bluevalue = f.getPixel(e.getX(),e.getY()).getBlue();
		double greenvalue =  f.getPixel(e.getX(),e.getY()).getGreen();
		String redcolor = String.format("%03.2f", redvalue);
		String bluecolor = String.format("%03.2f", bluevalue);
		String greencolor = String.format("%03.2f", greenvalue);
		double brightnessvalue =  f.getPixel(e.getX(),e.getY()).getBrightness();
		String brightnesscolor = String.format("%03.2f", brightnessvalue);
		
		System.out.println("Red:" + redcolor);
		System.out.println("Blue:" + bluecolor);
		System.out.println("Green:" + greencolor);
		System.out.println("Brightness:" + brightnesscolor);
		

		xStatus.setText("X: "+e.getX());
		yStatus.setText("Y: "+e.getY());	
		redStatus.setText("RED: "+redcolor);	
		greenStatus.setText("GREEN: "+greencolor);	
		blueStatus.setText("BLUE: "+bluecolor);	
		brightnessStatus.setText("BRIGHTNESS: "+brightnesscolor);	
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}