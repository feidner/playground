package ants;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class AntService {

	static final int GRID = 50;
	private static final int ANT_COUNT = 500;
	
	private Set<Point> points = new HashSet<>();
    private Set<Ant> ants = new HashSet<>();
    private Set<Point> foodPoints = new HashSet<>();
    
    void initGrid(int antCount, int gridSize) {
		for(int i = 0; i < antCount; i++) {
        	addAnt(new Ant(new Point(gridSize, gridSize)));
        }
	}
    
    Set<Point> getPoints() {
		return points;
	}
    
    Set<Ant> getAnts() {
		return ants;
	}
    
    

	void addAnt(Ant ant) {
    	ants.add(ant);
	}
    
    void addOrRemoveFoodPoint(Point foodPoint) {
    	if(foodPoints.contains(foodPoint)) {
    		foodPoints.remove(foodPoint);
    	} else {
    		foodPoints.add(foodPoint);
    	}
    }
    
    void calculateNewPoints() {
    	points.clear();
        for(Ant ant : ants) {
        	Point newPoint = ant.calculateNewPoint();
        	points.add(newPoint);
        }
    }
    
    void calculateFood() {
    	for(Ant ant : ants) {
    		if(foodPoints.contains(ant.getPoint())) {
    			ant.reachedFoodPoint();
    			continue;
    		}
    		for(Ant otherAnt : ants) {
    			if(ant != otherAnt && !ant.knowsFoodPoint() && otherAnt.knowsFoodPoint() && ant.getPoint().equals(otherAnt.getPoint())) {
    				ant.food(otherAnt.getFoodPoint());
    			}
         	}
        }
    }

	public void update() {
		calculateNewPoints();
    	calculateFood();
	}

	public boolean containsFoodPoint(Point point) {
		return foodPoints.contains(point);
	}
}
