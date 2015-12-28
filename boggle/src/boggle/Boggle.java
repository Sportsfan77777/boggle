package boggle;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Boggle extends JFrame {
	
	public Boggle() {
		this.initialize();
	}
	
	/**
	 * set things up
	 */
	private void initialize() {
		add(new Display());
		
		setResizable(false);
	    pack();
	        
	    setTitle("Boggle");
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
            public void run() {                
                Boggle boggle = new Boggle();
                boggle.setVisible(true);                
            }
        });
	}
}
