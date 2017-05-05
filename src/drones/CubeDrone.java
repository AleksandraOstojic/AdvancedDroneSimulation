package drones;

import java.util.List;

public class CubeDrone extends Drone{
	
	Cube drone = null;
	Cube newCube = null;
	private boolean obstacleHit = false;
	private int moveCounter;
		
	public CubeDrone(Cube outerBoundaries, Cube innerBoundaries, int [] droneStartCoordinates, int dronSide)
	{
		super(outerBoundaries, innerBoundaries);
		this.drone = new Cube(droneStartCoordinates, dronSide);
	}
	
	public CubeDrone(Cube outerBoundaries, Cube innerBoundaries, int [] droneStartCoordinates, int dronSide, List<Cube> obstacles)
	{
		super(outerBoundaries, innerBoundaries, obstacles);
		this.drone = new Cube(droneStartCoordinates, dronSide);
	}

	@Override
	protected boolean validateDronePositionAfter(String command)
	{
		int[] minCoordinates = drone.getMinCoordinates();
		int droneSide = drone.getCubeSideLength();
		newCube = new Cube(minCoordinates, droneSide);

		switch (command) {
		case "up":
			newCube.increaseY(1);
			break;
		case "down":
			newCube.decreaseY(1);
			break;
		case "left":
			newCube.decreaseX(1);
			break;
		case "right":
			newCube.increaseX(1);
			break;
		case "back":
			newCube.increaseZ(1);
			break;
		case "forth":
			newCube.decreaseZ(1);
			break;
		}
		//ako nema prepreka onda proverava samo da li dron udara o granice
		if(super.flySpace.getObstacles().isEmpty())
		{
			return !super.flySpace.getInnerBoundaries().checkCubeIntersection(newCube) ||
					   !super.flySpace.getOuterBoundaries().checkCubeIntersection(newCube);
		}
		else
		{
			return (!super.flySpace.getInnerBoundaries().checkCubeIntersection(newCube) ||
					   !super.flySpace.getOuterBoundaries().checkCubeIntersection(newCube))
					&& validateDronePositionForObstacles(newCube);
		}
		
	}
	
	private boolean validateDronePositionForObstacles(Cube cube)
	{
		for(int i=0; i<super.flySpace.getObstacles().size(); i++)
		{
			if(super.flySpace.getObstacles().get(i).checkCubeIntersection(newCube))
			{
				obstacleHit = true;
				return false;
			}
		}
		obstacleHit = false;
		return true;
		
	}
	private boolean getAroundObstacle(String initialCommand)
	{		
		switch(initialCommand)
		{
		case("up"):
			if(validateDronePositionAfter("left"))
				moveLeft();
			else if(validateDronePositionAfter("right"))
				moveRight();
			else if(validateDronePositionAfter("back"))
				moveBack();
			else if(validateDronePositionAfter("forth"))
				moveForth();
			else if(validateDronePositionAfter("down"))
				moveDown();
			else
				return false;
			break;
		case("down"):
			if(validateDronePositionAfter("left"))
				moveLeft();
			else if(validateDronePositionAfter("right"))
				moveRight();
			else if(validateDronePositionAfter("back"))
				moveBack();
			else if(validateDronePositionAfter("forth"))
				moveForth();
			else if(validateDronePositionAfter("up"))
				moveUp();
			else
				return false;
			break;
		case("left"):
			if(validateDronePositionAfter("down"))
				moveDown();
			else if(validateDronePositionAfter("right"))
				moveRight();
			else if(validateDronePositionAfter("back"))
				moveBack();
			else if(validateDronePositionAfter("forth"))
				moveForth();
			else if(validateDronePositionAfter("up"))
				moveUp();
			else
				return false;
			break;
			
		case("right"):
			if(validateDronePositionAfter("left"))
				moveLeft();
			else if(validateDronePositionAfter("up"))
				moveUp();
			else if(validateDronePositionAfter("back"))
				moveBack();
			else if(validateDronePositionAfter("forth"))
				moveForth();
			else if(validateDronePositionAfter("down"))
				moveDown();
			else
				return false;
			break;
		case("back"):
			if(validateDronePositionAfter("left"))
				moveLeft();
			else if(validateDronePositionAfter("right"))
				moveRight();
			else if(validateDronePositionAfter("down"))
				moveDown();
			else if(validateDronePositionAfter("forth"))
				moveForth();
			else if(validateDronePositionAfter("up"))
				moveUp();
			else
				return false;
			break;
		case("forth"):
			if(validateDronePositionAfter("left"))
				moveLeft();
			else if(validateDronePositionAfter("right"))
				moveRight();
			else if(validateDronePositionAfter("back"))
				moveBack();
			else if(validateDronePositionAfter("up"))
				moveUp();
			else if(validateDronePositionAfter("down"))
				moveDown();
			else
				return false;
			break;
			
		}
		return true;
		

	}
	
	
	@Override
	public String moveUp() {
		if(validateDronePositionAfter("up"))
		{
			drone.getMinCoordinates()[1] += 1;
			drone.getMaxCoordinates()[1] += 1;
		}
		else
		{
			if(getAroundObstacle("up"))
				moveUp();
		}
		return drone.toString();
	}

	@Override
	public String moveDown() {
		if(validateDronePositionAfter("down"))
		{
			drone.getMinCoordinates()[1] -= 1;
			drone.getMaxCoordinates()[1] -= 1;
		}
		else
		{
			if(getAroundObstacle("down"))
				moveDown();
		}
		return drone.toString();
	}

	@Override
	public String moveLeft() {
		if(validateDronePositionAfter("left"))
		{
			drone.getMinCoordinates()[0] -= 1;
			drone.getMaxCoordinates()[0] -= 1;
		}
		else
		{
			if(getAroundObstacle("left"))
				moveLeft();
		}
		return drone.toString();
	}

	@Override
	public String moveRight() {
		if(validateDronePositionAfter("right"))
		{
			drone.getMinCoordinates()[0] += 1;
			drone.getMaxCoordinates()[0] += 1;
		}
		else
		{
			if(getAroundObstacle("right"))
				moveRight();
		}
		return drone.toString();
	}

	@Override
	public String moveBack() {
		if(validateDronePositionAfter("back"))
		{
			drone.getMinCoordinates()[2] += 1;
			drone.getMaxCoordinates()[2] += 1;
		}
		else
		{
			if(getAroundObstacle("back"))
				moveBack();
		}
		return drone.toString();
	}

	@Override
	public String moveForth() {
		if(validateDronePositionAfter("forth"))
		{
			drone.getMinCoordinates()[2] -= 1;
			drone.getMaxCoordinates()[2] -= 1;
		}
		else
		{
			if(getAroundObstacle("forth"))
				moveForth();
		}
		return drone.toString();
	}
	
}
