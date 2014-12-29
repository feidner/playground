package ants;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import ants.Ant;
import ants.AntService;

public class AntServiceTest {

	private AntService fixture;
	
	@Before
	public void init() {
		fixture = new AntService();
	}
	
	@Test
	public void Given_InitUi_Then_Points_Are_Empty() {
		assertTrue(fixture.getPoints().isEmpty());
	}
	
	@Test
	public void Given_InitUi_Then_Ants_Are_Empty() {
		assertTrue(fixture.getAnts().isEmpty());
	}
	
	@Test
	public void Given_InitUi_When_InitGrid_With_Count_1_Then_AntsCollectionSize_Is_1() {
		fixture.initGrid(1, 50);
		assertEquals(1, fixture.getAnts().size());
	}
	
	@Test
	public void Given_Ui_With_2_Ants_Then_AntsCollectionSize_Is_2() {
		fixture.addAnt(new Ant(new Point(1,1)));
		fixture.addAnt(new Ant(new Point(0,0)));
		assertEquals(2, fixture.getAnts().size());
	}
	
	@Test
	public void Given_Ui_With_1_Ant_At_Point_1_1_And_FoodPoint_At_1_1_When_CalculateFood_Then_AntKnowsFood() {
		fixture.addAnt(new Ant(new Point(5,5)).setPoint(new Point(1,1)));
		fixture.addOrRemoveFoodPoint(new Point(1,1));
		fixture.calculateFood();;
		assertTrue(fixture.getAnts().iterator().next().knowsFoodPoint());
	}
	
	@Test
	public void Given_Ui_With_2_Ants_At_Point_1_1_And_One_Ant_KnowsFood_When_CalculateFood_Then_OtherAntAlsoKnowsFood() {
		fixture.addAnt(new Ant(new Point(1,1)).food(new Point(2,2)));
		Ant ant = new Ant(new Point(1,1));
		fixture.addAnt(ant);
		fixture.calculateFood();
		assertTrue(ant.knowsFoodPoint());
	}
}
