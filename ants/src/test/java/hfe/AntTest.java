package hfe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AntTest {
	
	private Ant ant;
	
	@Before
	public void init() {
		ant = new Ant(new Point(10,10));
	}
	
	@Test
	public void Given_InititalAnt_With_MaxPoint_10_10_Then_HomePoint_Is_5_5() {
		assertEquals(new Point(5,5), ant.getHomePoint());
	}
	@Test
	public void Given_InititalAnt_With_MaxPoint_10_10_Then_Route_Is_Empty() {
		assertTrue(ant.getRoute().isEmpty());
	}
	@Test
	public void Given_Ant_At_Point_10_10_When_Go_Home_Then_NewPoint_Is_9_9() {
		ant.setPoint(new Point(10,10));
		ant.goHome();
		assertEquals(new Point(9,9), ant.calculateNewPoint());
	}
	@Test
	public void Given_Ant_At_Point_6_6_And_Go_Home_True_Then_NewPoint_Is_5_5() {
		ant.setPoint(new Point(6,6));
		ant.goHome();
		assertEquals(new Point(5,5), ant.calculateNewPoint());
	}
	@Test
	public void Given_Ant_At_Point_5_6_When_Go_Home_Then_NewPoint_Is_5_5() {
		ant.setPoint(new Point(5,6));
		ant.goHome();
		assertEquals(new Point(5,5), ant.calculateNewPoint());
	}
	
	@Test
	public void Given_Ant_At_Point_6_6_When_Go_Home_And_NewPoint_Then_LastRoutePoint_Is_6_6() {
		ant.setPoint(new Point(6,6));
		ant.goHome();
		ant.calculateNewPoint();
		assertEquals(new Point(6,6), ant.getLastRoutePoint());
	}
	
	@Test
	public void Given_InititalAnt_With_MaxPoint_10_10_Then_NewPoint_IsNot_5_5() {
		assertNotEquals(new Point(5,5), ant.calculateNewPoint());
	}
	@Test
	public void Given_InititalAnt_With_MaxPoint_10_10_Then_NewPoint_Is_InRange_4_6() {
		assertTrue(pointsArround(new Point(5,5), 10).contains(ant.calculateNewPoint()));
	}
	@Test
	public void Given_Ant_At_Point_9_8_When_Route_To_Point_7_7_Then_Route_Has_2_Points() {
		ant.setPoint(new Point(9,8));
		ant.route(new Point(7,7));
		assertEquals(2, ant.getRoute().size());
	}
	@Test
	public void Given_Ant_At_Point_2_2_When_Food_At_7_8_Then_Route_Has_6_Points() {
		ant.setPoint(new Point(2,2));
		ant.food(new Point(7,8));
		assertEquals(6, ant.getRoute().size());
	}
	@Test
	public void Given_Ant_At_Point_2_2_When_Food_At_7_8_Then_LastRoutePoint_Is_7_8() {
		ant.setPoint(new Point(2,2));
		ant.food(new Point(7,8));
		assertEquals(new Point(7,8), ant.getLastRoutePoint());
	}
	@Test
	public void Given_InititalAnt_When_Food_At_7_8_Then_Ant_KnowsFoodPoint() {
		ant.food(new Point(7,8));
		assertTrue(ant.knowsFoodPoint());
	}
	@Test
	public void Given_InititalAnt_Then_Ant_DontKnowFoodPoint() {
		assertFalse(ant.knowsFoodPoint());
	}
	@Test
	public void Given_InititalAnt_When_Food_At_7_8_Then_FoodPoint_At_7_8() {
		ant.food(new Point(7,8));
		assertEquals(new Point(7,8), ant.getFoodPoint());
	}
	@Test
	public void Given_Ant_At_Point_7_7_When_Food_At_7_8_Then_NewPoint_7_8() {
		ant.setPoint(new Point(7,7));
		ant.food(new Point(7,8));
		assertEquals(new Point(7,8), ant.calculateNewPoint());
	}
	@Test
	public void Given_Ant_At_Point_7_7_And_Food_At_7_8_When_Ant_Reach_FoodPoint_Then_Ant_OnWayHome() {
		ant.setPoint(new Point(7,7));
		ant.food(new Point(7,8));
		ant.goHome();
		assertTrue(ant.isOnWayHome());
	}
	
	@Test
	public void Given_Ant_At_Point_7_7_And_Food_At_7_8_When_CalculateNewPoint_And_Ant_ReachedFoodPoint_Then_Ant_KnowsFoodPoint() {
		ant.setPoint(new Point(7,7));
		ant.food(new Point(7,8));
		ant.calculateNewPoint();
		ant.reachedFoodPoint();
		assertTrue(ant.knowsFoodPoint());
	}
	
	private List<Point> pointsArround(Point p, int grid) {
		List<Point> ll = Arrays.asList(new Point(p.x-1, p.y-1), new Point(p.x-1, p.y), new Point(p.x-1, p.y+1), 
				new Point(p.x, p.y-1), new Point(p.x, p.y+1), new Point(p.x+1, p.y-1), new Point(p.x+1, p.y), new Point(p.x+1, p.y+1));
		for(Iterator<Point> iter = ll.iterator(); iter.hasNext();) {
			Point tt = iter.next();
			if(tt.x < 0 || tt.y < 0 || tt.x > grid || tt.y > grid) {
				iter.remove();
			}
		}
		return ll;
	}
}
