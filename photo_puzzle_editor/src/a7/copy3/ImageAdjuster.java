package a7.copy3;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class ImageAdjuster {
	public static void main(String[] args) throws IOException {
		Frame f = A7Helper.readFromURL("http://p.qpic.cn/carforum/0/carforum_forum_201402_17_000036f93jy9b9t511hz6z.jpg/0");
		f.setTitle("Ferrari 458");
		ImageAdjusterWidget image_adjuster = new ImageAdjusterWidget(f);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Image Adjuster View");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(image_adjuster, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}