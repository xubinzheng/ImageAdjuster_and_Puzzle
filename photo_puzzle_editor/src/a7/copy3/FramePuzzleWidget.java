package a7.copy3;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;



public class FramePuzzleWidget extends JPanel implements MouseListener,KeyListener {

	
	private Frame f;
	JPanel rp;
	FrameView[][] frame_view = new FrameView[5][5];
	private int blank_x=4, blank_y=4;
	
	
	public FramePuzzleWidget(Frame f) {
		this.f = f;
		
		setLayout(new BorderLayout());
		
		
		rp = new JPanel();
		rp.setPreferredSize(new Dimension(370, 315));
		add(rp, BorderLayout.CENTER);
		addKeyListener(this);
		for(int j=0;j<5;j++){
			for(int i=0;i<5;i++){ 
				if(i==4 && j==4){
					for(int x=0;x<f.getWidth()/5;x++){
						for(int y=0; y<f.getHeight()/5;y++){
							ColorPixel p = new ColorPixel(1,1,1);
							Frame colorframe = f.crop(i* f.getWidth()/5, j*f.getHeight()/5, f.getWidth()/5, f.getHeight()/5);
							colorframe.setPixel(x,y,p);
							frame_view[j][i] = new FrameView(colorframe);
						}
					}
					
				}
					
				frame_view[j][i]  = new FrameView(f.crop(i* f.getWidth()/5, j*f.getHeight()/5, f.getWidth()/5, f.getHeight()/5));
				frame_view[j][i].addMouseListener(this);
				frame_view[j][i].addKeyListener(this);
				rp.add(frame_view[j][i]);
			}
		}
		
	}
	


	@Override
	public void keyPressed(KeyEvent e) {
		
		int m=0,n=0;
		System.out.println(blank_x);
		System.out.println(blank_y);
		
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_DOWN:
				System.out.println("down");
				n=n-1;
				break;
			case KeyEvent.VK_UP:
				System.out.println("up");
				n=n+1;
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("left");
				m=m-1;
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("right");
				m=m+1;
				break;
		}
		int x = blank_x + m;
		int y = blank_y - n;
		
		if(x>=0 && x<=4 && y>=0 && y<=4){
		moveDirection(blank_x, blank_y, x, y);
		}
		//4,3 ---left --->2,4

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("clicked");
		
		int x=0,y=0;
		for(int j=0;j<5;j++){
			for(int i=0;i<5;i++){ 
				if(frame_view[j][i] == e.getSource()){
					x=i;
					y=j;
					
				}
			}
		}
		
			moveDirection(blank_x, blank_y, x, y);
		
	}
	
	
	public void moveDirection(int x_blank, int y_blank, int x_target, int y_target){
		
		if((x_target==x_blank && (y_blank-1==y_target||y_blank+1==y_target) )||(y_target==y_blank && (x_target==x_blank-1||x_target==x_blank+1))){
			
			System.out.println("moved");
			
			
			Frame temframe = frame_view[y_target][x_target].getFrame();
			Frame temframe2 = frame_view[y_blank][x_blank].getFrame();
			
			frame_view[y_target][x_target].setFrame(temframe2);
			frame_view[y_blank][x_blank].setFrame(temframe);
			
			
			
			blank_x = x_target;
			blank_y = y_target;
			
		}
		else if((x_target==x_blank && (y_blank-1!=y_target||y_blank+1!=y_target) )||(y_target==y_blank && (x_target!=x_blank-1||x_target!=x_blank+1))){
			
			if(x_target==x_blank){
				
				int num_changed = y_blank-y_target;
				
				if(num_changed>0){
					
					Frame[] changed_frame = new Frame[num_changed];
					for(int i=0;i<num_changed;i++){
						changed_frame[i] = frame_view[y_target+i][x_target].getFrame();
					}
					Frame temframe = frame_view[y_blank][x_blank].getFrame();
					frame_view[y_target][x_target].setFrame(temframe);
					for(int i=0;i<num_changed;i++){
						
						frame_view[y_target+1+i][x_target].setFrame(changed_frame[i]);
					}
					blank_x = x_target;
					blank_y = y_target;
					
				}else{
					num_changed = -num_changed;
					Frame[] changed_frame = new Frame[num_changed];
					for(int i=0;i<num_changed;i++){
						changed_frame[i] = frame_view[y_target-i][x_target].getFrame();
					}
					Frame temframe = frame_view[y_blank][x_blank].getFrame();
					frame_view[y_target][x_target].setFrame(temframe);
					for(int i=0;i<num_changed;i++){
						
						frame_view[y_target-1-i][x_target].setFrame(changed_frame[i]);
					}
					blank_x = x_target;
					blank_y = y_target;
				}
				
			}
			else{
				int num_changed = x_blank-x_target;
				
				if(num_changed>0){
					
					Frame[] changed_frame = new Frame[num_changed];
					for(int i=0;i<num_changed;i++){
						changed_frame[i] = frame_view[y_target][x_target+i].getFrame();
					}
					Frame temframe = frame_view[y_blank][x_blank].getFrame();
					frame_view[y_target][x_target].setFrame(temframe);
					for(int i=0;i<num_changed;i++){
						
						frame_view[y_target][x_target+i+1].setFrame(changed_frame[i]);
					}
					blank_x = x_target;
					blank_y = y_target;
					
				}else{
					num_changed = -num_changed;
					Frame[] changed_frame = new Frame[num_changed];
					for(int i=0;i<num_changed;i++){
						changed_frame[i] = frame_view[y_target][x_target-i].getFrame();
					}
					Frame temframe = frame_view[y_blank][x_blank].getFrame();
					frame_view[y_target][x_target].setFrame(temframe);
					for(int i=0;i<num_changed;i++){
						
						frame_view[y_target][x_target-1-i].setFrame(changed_frame[i]);
					}
					blank_x = x_target;
					blank_y = y_target;
				}
				
			}
			
		
		}
	
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

