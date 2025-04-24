package assignment9;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int count;
	
	public Snake() {
		//FIXME - set up the segments instance variable
		deltaX = 0;
		deltaY = 0;
		segments = new LinkedList<>();
		BodySegment segment = new BodySegment(.5, .5, SEGMENT_SIZE);
		segments.add(segment);
		count = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		//FIXME
		BodySegment head = segments.getFirst();
		double newX = head.getX()+ deltaX; //moves x and y
		double newY = head.getY()+ deltaY;
		segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE)); //adds new segment to front of the list
		segments.removeLast(); //removes last segment
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		//FIXME
		for (BodySegment segment : segments) {//for each segment in list segments
			segment.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		//FIXME
		BodySegment head = segments.getFirst();
		if (Math.abs(head.getX() - f.getX()) < .04 && Math.abs(head.getY() - f.getY())< .04) {
			double lastX = segments.getLast().getX();
			double lastY = segments.getLast().getY();
			segments.add(new BodySegment(lastX, lastY, SEGMENT_SIZE));//adds a new segment in the position of the last segment
			this.count++;
			StdDraw.text(0.9,0.9, "Score: " + count);
			return true;
		}
		return false;
	}
	public int getCount() {
		return count;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		//FIXME
		BodySegment head = segments.getFirst();
		return head.getX() >= 0 && head.getX() <= 1 && head.getY() >= 0 && head.getY() <= 1; //return true or false
	}
}
