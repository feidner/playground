package ants;

import java.awt.Point;

import javax.swing.JLabel;

public class AntLabel extends JLabel {

	private Point point;
	private boolean home;

	public AntLabel(Point point) {
		super();
		this.point = point;
	}

	public Point getPoint() {
		return point;
	}
	
	public boolean isHome() {
		return home;
	}
	
	public void setHome(boolean home) {
		this.home = home;
	}
}