package hfe;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant {
	private Point max, point, homePoint, lastFoodPoint;
	private Random random;
	private List<Point> route;
	private boolean onWayHome;
	
	public Ant(Point max) {
		route = new ArrayList<Point>();
		this.max = max;
		this.random = new Random();
		this.point = this.homePoint = new Point(max.x / 2, max.y / 2);
		this.onWayHome = false;
	}

	Ant goHome() {
		lastFoodPoint = point;
		route(homePoint);
		this.onWayHome = true;
		return this;
	}
	
	public Ant food(Point foodPoint) {
		route(foodPoint);
		return this;
	}
	
	public void reachedFoodPoint() {
		if(!onWayHome || lastFoodPoint != null) {
			goHome();
		}
	}

	void route(Point toThisPoint) {
		route.clear();
		int xdiff = point.x - toThisPoint.x;
		int ydiff = point.y - toThisPoint.y;

		for (int i = 1; i <= Math.max(Math.abs(xdiff), Math.abs(ydiff)); i++) {
			int x = point.x - (xdiff < 0 ? -i : i);
			int y = point.y - (ydiff < 0 ? -i : i);
			Point routePoint = null;
			if (route.isEmpty()) {
				routePoint = new Point(point.x == toThisPoint.x ? point.x : x, point.y == toThisPoint.y ? point.y : y);
			} else {
				Point lastRoutePoint = getLastRoutePoint();
				routePoint = new Point(lastRoutePoint.x == toThisPoint.x ? lastRoutePoint.x : x,
						lastRoutePoint.y == toThisPoint.y ? lastRoutePoint.y : y);
			}
			route.add(routePoint);
		}
	}
	
	Point getLastRoutePoint() {
		return getRoute().get(getRoute().size()-1);
	}

	private Point nextRandom() {
		Point next;
		do {
			int x = random.nextInt(2);
			int y = random.nextInt(2);
			next = new Point(x, y);
		} while (next.equals(new Point(0, 0)));
		return next;
	}

	public Point calculateNewPoint() {
		if (!route.isEmpty()) {
			point = route.get(0);
			route.remove(0);
			if(route.isEmpty()) {
				if(point.equals(homePoint) && onWayHome) {
					onWayHome = false;
					route(lastFoodPoint);
				}
				if(!onWayHome && lastFoodPoint != null) {
					lastFoodPoint = null;
				}
			}
		} else {
			Point binaerPoint = nextRandom();
			int x = random.nextBoolean() ? (point.x + binaerPoint.x) : (point.x - binaerPoint.x);
			int y = random.nextBoolean() ? (point.y + binaerPoint.y) : (point.y - binaerPoint.y);
			x = x > max.x ? max.x : x;
			y = y > max.y ? max.y : y;
			x = x < 0 ? 0 : x;
			y = y < 0 ? 0 : y;
			point = new Point(x, y);
		}
		return point;
	}

	Point getPoint() {
		return point;
	}

	public Ant setPoint(Point point) {
		this.point = point;
		return this;
	}

	Point getHomePoint() {
		return homePoint;
	}

	List<Point> getRoute() {
		return route;
	}

	public boolean knowsFoodPoint() {
		return getFoodPoint() != null;
	}
	
	private boolean hasRoute() {
		return !route.isEmpty();
	}

	public Point getFoodPoint() {
		return lastFoodPoint == null ? (!hasRoute() ? null : getLastRoutePoint()) : lastFoodPoint;
	}
	
	boolean isOnWayHome() {
		return onWayHome;
	}
}
