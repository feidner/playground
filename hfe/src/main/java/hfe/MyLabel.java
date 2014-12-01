package hfe;

import java.awt.Point;

import javax.swing.JLabel;

public class MyLabel extends JLabel {

	private Point point;

	public MyLabel(Point point) {
		super();
		this.point = point;
	}

	public Point getPoint() {
		return point;
	}
}
