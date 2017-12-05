package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private Maze maze;
    private Ghost[] ghosts;
    private int x;
    private int y;
    private final int pixel = 20;
    private String direction = "";
    private boolean mouthOpen;
    public int cakesEaten;
    public boolean isDead;

    public Player(Maze maze, Ghost[] ghosts) {

        this.maze = maze;
        this.ghosts = ghosts;

    }

    public void drawPlayer(Canvas canvas) {

        int randX = ThreadLocalRandom.current().nextInt(0, 20);
        int randY = ThreadLocalRandom.current().nextInt(0, 20);
        while (maze.selectedMaze[randY][randX] != 1 || isOnGhost(randX, randY)) {

            randX = ThreadLocalRandom.current().nextInt(0, 20);
            randY = ThreadLocalRandom.current().nextInt(0, 20);

        }
        double pixelSize = canvas.getWidth() / pixel;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(randX * pixelSize, randY * pixelSize, pixelSize, pixelSize);
        x = randX;
        y = randY;
        maze.selectedMaze[y][x] = 2;
        mouthOpen = false;
        isDead = false;
        maze.cakesLeft--;
        cakesEaten++;

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
            return;
        }
        double pixelSize = canvas.getWidth() / pixel;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int next = maze.selectedMaze[y][x];
        switch (next) {

            case 0: return;

            case 1: eatCake(canvas, x, y); break;

            case 3: isDead = true; break;

            case 4:
                gc.setFill(Color.GREEN);
                gc.fillOval(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                gc.setFill(Color.LIGHTBLUE);
                gc.fillRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
                maze.selectedMaze[y][x] = 2;
                maze.selectedMaze[this.y][this.x] = 4;
                this.x = x;
                this.y = y;
                closeMouth(canvas, pixelSize);

        }

    }

    private void eatCake(Canvas canvas, int x, int y) {

        double pixelSize = canvas.getWidth() / pixel;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
        gc.setFill(Color.TRANSPARENT);
        gc.fillOval(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
        gc = Main.pieces.getGraphicsContext2D();
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(this.x * pixelSize, this.y * pixelSize, pixelSize, pixelSize);
        maze.selectedMaze[y][x] = 2;
        maze.selectedMaze[this.y][this.x] = 4;
        this.x = x;
        this.y = y;
        if (mouthOpen) {
            closeMouth(canvas, pixelSize);
        } else {
            openMouth(canvas, pixelSize);
        }
        maze.cakesLeft--;
        cakesEaten++;

    }

    private void openMouth(Canvas canvas, double pixelSize) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTBLUE);
        double[] Xs = null;
        double[] Ys = null;
        double p = pixelSize;
        switch (direction) {

            case "up":
                Xs = new double[] {x * p, (x * p) + p/2, (x * p) + p};
                Ys = new double[] {y * p, (y * p) + p/2, y * p};
                break;

            case "down":
                Xs = new double[] {x * p, (x * p) + p/2, (x * p) + p};
                Ys = new double[] {(y * p) + p, (y * p) + p/2, (y * p) + p};
                break;

            case "left":
                Xs = new double[] {x * p, (x * p) + p/2, x * p};
                Ys = new double[] {y * p, (y * p) + p/2, (y * p) + p};
                break;

            case "right":
                Xs = new double[] {(x * p) + p, (x * p) + p/2, (x * p) + p};
                Ys = new double[] {y * p, (y * p) + p/2, (y * p) + p};
                break;

        }
        gc.fillPolygon(Xs, Ys, 3);
        mouthOpen = true;

    }

    private void closeMouth(Canvas canvas, double pixelSize) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillOval(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
        mouthOpen = false;

    }

    public boolean isOnGhost(int x, int y) {

        for (Ghost ghost : ghosts) {

            if(x == ghost.getX() && y == ghost.getY()) {
                return true;
            }

        }
        return false;

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

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
