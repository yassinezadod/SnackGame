package com.yassine;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
public class SnakeGame {
	
	private static final int GRID_SIZE = 10;

    private LinkedList<Point> snake;
    private Point food;
    private Direction direction;

    public SnakeGame() {
        this.snake = new LinkedList<>();
        this.snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2));
        this.food = createFood();
        this.direction = Direction.RIGHT;
    }
    
    
    

    public SnakeGame(LinkedList<Point> snake, Point food, Direction direction) {
		super();
		this.snake = snake;
		this.food = food;
		this.direction = direction;
	}
    
    




	public LinkedList<Point> getSnake() {
		return snake;
	}




	public void setSnake(LinkedList<Point> snake) {
		this.snake = snake;
	}




	public Point getFood() {
		return food;
	}




	public void setFood(Point food) {
		this.food = food;
	}




	public Direction getDirection() {
		return direction;
	}




	public void setDirection(Direction direction) {
		this.direction = direction;
	}




	public static int getGridSize() {
		return GRID_SIZE;
	}




	public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printGameBoard();
            System.out.print("Enter a direction (W/A/S/D): ");
            char input = scanner.next().charAt(0);
            updateDirection(input);

            move();
            if (isGameOver()) {
                System.out.println("Game Over!");
                break;
            }

            if (snake.peekFirst().equals(food)) {
                snake.addFirst(food);
                food = createFood();
            }

            clearConsole();
        }

        scanner.close();
    }
    
    
    

    private void move() {
        Point head = snake.getFirst();
        Point newHead;

        switch (direction) {
            case UP:
                newHead = new Point(head.x, (head.y - 1 + GRID_SIZE) % GRID_SIZE);
                break;
            case DOWN:
                newHead = new Point(head.x, (head.y + 1) % GRID_SIZE);
                break;
            case LEFT:
                newHead = new Point((head.x - 1 + GRID_SIZE) % GRID_SIZE, head.y);
                break;
            case RIGHT:
                newHead = new Point((head.x + 1) % GRID_SIZE, head.y);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

        snake.addFirst(newHead);
        if (snake.size() > 1) {
            snake.removeLast();
        }
    }

    private boolean isGameOver() {
        Point head = snake.getFirst();
        // Check for collision with the snake's body
        for (Point bodyPart : snake.subList(1, snake.size())) {
            if (head.equals(bodyPart)) {
                return true;
            }
        }
        return false;
    }

    private Point createFood() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(GRID_SIZE);
            y = random.nextInt(GRID_SIZE);
        } while (snake.contains(new Point(x, y)));
        return new Point(x, y);
    }

    private void printGameBoard() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Point currentPoint = new Point(j, i);
                if (snake.contains(currentPoint)) {
                    System.out.print("O ");
                } else if (currentPoint.equals(food)) {
                    System.out.print("F ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private void updateDirection(char input) {
        switch (input) {
            case 'W':
            case 'w':
                direction = Direction.UP;
                break;
            case 'S':
            case 's':
                direction = Direction.DOWN;
                break;
            case 'A':
            case 'a':
                direction = Direction.LEFT;
                break;
            case 'D':
            case 'd':
                direction = Direction.RIGHT;
                break;
            default:
              
        }
    }

    private void clearConsole() {
       
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

   

}
