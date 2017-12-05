package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

public class Ghost {

    private final int pixel = 20;
    private Maze maze;
    private int x;
    private int y;
    private String direction = "";
    private boolean isOnCake;
    public boolean isOnPlayer;

    public Ghost(Maze maze) {
        this.maze = maze;
    }

    public void drawGhost(Canvas canvas) {

        int randX = ThreadLocalRandom.current().nextInt(0, 20);
        int randY = ThreadLocalRandom.current().nextInt(0, 20);
        while (maze.selectedMaze[randY][randX] != 1) {

            randX = ThreadLocalRandom.current().nextInt(0, 20);
            randY = ThreadLocalRandom.current().nextInt(0, 20);

        }
        double pixelSize = canvas.getWidth() / pixel;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillOval(randX * pixelSize, randY * pixelSize, pixelSize, pixelSize);
        x = randX;
        y = randY;
        maze.selectedMaze[y][x] = 3;
        isOnCake = true;
        isOnPlayer = false;
        direction = randomDirection();

    }

    public void jump(Canvas canvas, String direction) {

        this.direction = direction;
        int x = -1;
        int y = -1;
        switch (direction) {

            case "up":
                x = this.x;
                y = this.y - 1;
                break;

            case "down":
                x = this.x;
                y = this.y + 1;
                break;

            case "left":
                x = this.x - 1;
                y = this.y;
                break;

            case "right":
                x = this.x + 1;
                y = this.y;
                break;

        }
        draw(canvas, x, y);

    }

    private void draw(Canvas canvas, int x, int y) {

        if (x < 0 || x > pixel - 1 || y < 0 || y > pixel - 1) {
            jump(canvas, randomDirection()); return;
        }
        double pixelSize = canvas.getWidth() / pixel;
        int next = maze.selectedMaze[y][x];
        switch (next) {

            case 0: jump(canvas, randomDirection()); break;

            case 1: oneOrFour(canvas, x, y, pixelSize, true); break;

            case 2: isOnPlayer = true; break;

            case 3: jump(canvas, getOppositeDirection()); break;

            case 4: oneOrFour(canvas, x, y, pixelSize, false); break;

        }

    }

    public String randomDirection() {

        int rand = ThreadLocalRandom.current().nextInt(0, 4);
        switch (rand) {

            case 0: return "up";
            case 1: return "down";
            case 2: return "left";
            case 3: return "right";
            default: return "right";

        }

    }

    private void oneOrFour(Canvas canvas, int x, int y, double pixelSize, boolean willBeOnCake) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillOval(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
        if (isOnCake) {

            maze.selectedMaze[this.y][this.x] = 1;
            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
            gc.setFill(Color.WHITE);
            gc.fillOval(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);

        } else {

            maze.selectedMaze[this.y][this.x] = 4;
            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);

        }
        isOnCake = willBeOnCake;
        maze.selectedMaze[y][x] = 3;
        this.x = x;
        this.y = y;

    }

    private String getOppositeDirection() {

        switch (direction) {

            case "up": return "down";
            case "down": return "up";
            case "left": return "right";
            case "right": return "left";
            default: return "";

        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

}
