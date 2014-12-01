package hfe;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AntsUi {

	private static final int GRID = 50;
    private JFrame f;

    public static void main(String[] args) throws Exception {
        AntsUi ui = new AntsUi();
        ui.createFrame();

        Set<Ant> ants = new HashSet<>();
        for(int i = 0; i < 50; i++) {
        	Ant ant = new Ant(new Point(GRID, GRID));
        	ants.add(ant);
        }
        Set<Point> points = new HashSet<>();
        while(true) {
            Thread.sleep(100);
            points.clear();
            for(Ant ant : ants) {
            	points.add(ant.newPoint());
            }
            ui.update(points);

        }
    }

    private void update(Set<Point> p) {
        for(Component component : f.getContentPane().getComponents()) {
        	MyLabel label = (MyLabel)component;
            label.setText("");
            for(Point point : p ) {
            	if(label.getPoint().equals(point)) {
            		label.setText("X");
            	}
            }
        }
    }

    private void createFrame() {
        f = new JFrame("Ants");
        f.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				System.out.println("dede");
				System.exit(0);
			}
		});
        f.setContentPane(createContentPane(GRID));
        f.setSize(1024, 860);
        f.setVisible(true);
    }

    private Container createContentPane(int dimension) {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(dimension, dimension));
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                MyLabel label = new MyLabel(new Point(i,j));
                p.add(label);

            }
        }
        return p;
    }
}
