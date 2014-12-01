package hfe;

import java.awt.Point;
import java.util.Random;

public class Ant {
	private boolean goHome;
	private Point max, point, foodPoint, homePoint;
	private Random random;

	public Ant(Point max) {
		this.max = max;
		this.random = new Random();
		this.point = this.homePoint = new Point(max.x/2, max.y/2);
	}

	public void setGoHome(boolean goHome) {
		this.goHome = goHome;
	}

	private Point navigate(Point target) {
		int x = target.x - point.x;
		int y = target.y - point.y;
		return target;
	}

	public Point newPoint() {

		if(goHome) {

			return homePoint;
		}
		if(foodPoint != null) {

		}

		int x = random.nextBoolean() ? (point.x + random.nextInt(2)) : (point.x - random.nextInt(2));
		int y = random.nextBoolean() ? (point.y + random.nextInt(2)) : (point.y - random.nextInt(2));
		x = x > max.x ? max.x : x;
		y = y > max.y ? max.y : y;
		x = x < 0 ? 0 : x;
		y = y < 0 ? 0 : y;
		point = new Point(x,y);
		return point;
	}

	public Point getPoint() {
		return point;
	}
}
