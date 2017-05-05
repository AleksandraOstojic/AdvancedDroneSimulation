package tests;
import org.junit.Test;

import drones.Cube;
import drones.CubeDrone;
import drones.Drone;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import space.FlySpace;

public class CubeDroneTest {

	CubeDrone drone;
	FlySpace flySpace;
	Cube cube;
	List<Cube> obstacles;
	
	@Test
	public void testInitialMoveUp()
	{
		Cube outerCube = new Cube(new int[] {0,0,0}, 50);
		Cube innerCube = new Cube(new int[] {10,10,10}, 30);
		Cube testDrone = new Cube(new int[] {30,1,30}, 1);
		Cube expectedDronePosition = new Cube(new int[] {30,2,30}, 1);
		drone = new CubeDrone(outerCube, innerCube, new int[] {30,1,30}, 1);
		String expectedResponse = expectedDronePosition.toString();
		
		assertEquals(expectedResponse, drone.moveUp());
	}

	@Test
	public void testDroneMoveUpHitsObstacleTryToGetAround()
	{
		obstacles = new ArrayList<>();
		Cube outerCube = new Cube(new int[] {0,0,0}, 50);
		Cube innerCube = new Cube(new int[] {10,10,10}, 30);
		Cube expectedDronePosition = new Cube(new int[] {29,2,30}, 1);
		obstacles.add(new Cube(new int[] {30,2,30}, 5));
		drone = new CubeDrone(outerCube, innerCube, new int[] {30,1,30}, 1, obstacles);
		String expectedResponse = expectedDronePosition.toString();
		assertEquals(expectedResponse, drone.moveUp());
	}
	@Test
	public void testDroneMoveDownHitsObstacleTryToGetAround()
	{
		obstacles = new ArrayList<>();
		Cube outerCube = new Cube(new int[] {0,0,0}, 50);
		Cube innerCube = new Cube(new int[] {10,10,10}, 30);
		Cube expectedDronePosition = new Cube(new int[] {30,0,30}, 1);
		obstacles.add(new Cube(new int[] {30,0,30}, 5));
		drone = new CubeDrone(outerCube, innerCube, new int[] {30,1,30}, 1, obstacles);
		String expectedResponse = expectedDronePosition.toString();
		assertEquals(expectedResponse, drone.moveDown());
	}
	@Test
	public void testDroneMoveLeftHitsObstacleTryToGetAround()
	{
		obstacles = new ArrayList<>();
		Cube outerCube = new Cube(new int[] {0,0,0}, 50);
		Cube innerCube = new Cube(new int[] {10,10,10}, 30);
		Cube expectedDronePosition = new Cube(new int[] {0,2,30}, 1);
		obstacles.add(new Cube(new int[] {0,2,30}, 5));
		drone = new CubeDrone(outerCube, innerCube, new int[] {0,1,30}, 1, obstacles);
		String expectedResponse = expectedDronePosition.toString();
		assertEquals(expectedResponse, drone.moveLeft());
	}
	@Test
	public void testDroneMoveRightHitsObstacleTryToGetAround()
	{
		obstacles = new ArrayList<>();
		Cube outerCube = new Cube(new int[] {0,0,0}, 50);
		Cube innerCube = new Cube(new int[] {10,10,10}, 30);
		Cube expectedDronePosition = new Cube(new int[] {31,1,30}, 1);
		obstacles.add(new Cube(new int[] {30,0,30}, 5));
		drone = new CubeDrone(outerCube, innerCube, new int[] {30,1,30}, 1, obstacles);
		String expectedResponse = expectedDronePosition.toString();
		assertEquals(expectedResponse, drone.moveRight());
	}
	@Test
	public void testDroneMoveForthHitsObstacleTryToGetAround()
	{
		obstacles = new ArrayList<>();
		Cube outerCube = new Cube(new int[] {0,0,0}, 50);
		Cube innerCube = new Cube(new int[] {10,10,10}, 30);
		Cube expectedDronePosition = new Cube(new int[] {29,2,30}, 1);
		obstacles.add(new Cube(new int[] {30,2,30}, 5));
		drone = new CubeDrone(outerCube, innerCube, new int[] {30,1,30}, 1, obstacles);
		String expectedResponse = expectedDronePosition.toString();
		assertEquals(expectedResponse, drone.moveForth());
	}
	@Test
	public void testDroneMoveBackHitsObstacleTryToGetAround()
	{
		obstacles = new ArrayList<>();
		Cube outerCube = new Cube(new int[] {0,0,0}, 50);
		Cube innerCube = new Cube(new int[] {10,10,10}, 30);
		Cube expectedDronePosition = new Cube(new int[] {29,2,30}, 1);
		obstacles.add(new Cube(new int[] {30,2,30}, 5));
		drone = new CubeDrone(outerCube, innerCube, new int[] {30,1,30}, 1, obstacles);
		String expectedResponse = expectedDronePosition.toString();
		assertEquals(expectedResponse, drone.moveBack());
	}
}
