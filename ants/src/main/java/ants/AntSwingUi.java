package ants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AntSwingUi {

    private JFrame f;
    private AntService service;
    

    public static void main(String[] args) throws Exception {
        AntSwingUi ui = new AntSwingUi();
        ui.start();  
    }
    
    public AntSwingUi() {
    	service = new AntService();
    	service.initGrid(300, 50);
	}
    
    private void start() throws InterruptedException {
    	createFrame();
        while(true) {
            Thread.sleep(100);
            update();
        }
    }

    void update() {
    	service.update();
        updateUi();
    }
    
    private void updateUi() {
    	for(Component component : f.getContentPane().getComponents()) {
        	AntLabel label = (AntLabel)component;
            label.setText("");            
            label.setBackground(service.containsFoodPoint(label.getPoint()) ? Color.red : null);
            if(label.isHome()) {
            	label.setBackground(Color.blue);
            }
            service.getPoints().forEach((point)-> {
            	if(label.getPoint().equals(point)) {
            		label.setText("o");
            	} 
            });     
        }
    }

    private void createFrame() {
        f = new JFrame("Ants");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(createContentPane(AntService.GRID));
        f.setSize(1024, 860);
        f.setVisible(true);
    }

    private Container createContentPane(int dimension) {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(dimension, dimension));
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                AntLabel label = new AntLabel(new Point(i,j));
                if(i == dimension/2 && j == dimension/2) {
                	label.setHome(true);
                }
                label.setOpaque(true);              
                p.add(label);        
                label.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent e) {
                		AntLabel l = (AntLabel)e.getSource();
                		service.addOrRemoveFoodPoint(l.getPoint());
                	}
				});
            }
        }
        return p;
    }
    
    
 
}
