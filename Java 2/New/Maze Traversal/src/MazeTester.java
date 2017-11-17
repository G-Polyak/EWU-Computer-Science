//George Polyak
//CSCD 211-02

public class MazeTester {

    private static int[][] maze = {
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    public static void main(String[] args) {
        maze = solveMaze(maze);
    }

    private static int[][] solveMaze(int[][] newMaze) {

        maze = newMaze;
        try {

            if (!mazeRunner(0, 0)) {
                System.out.println("Maze is unsolvable");
            }

            printMaze(maze);

        } catch (StackOverflowError e) {
            System.out.println("Maze too large");
        } catch (NullPointerException e) {
            System.out.println("Maze is null");
        }
        return maze;

    }

    private static void printMaze(int[][] grid) {

        for (int[] row : grid) {

            for (int x : row) {
                System.out.print(x);
            }
            System.out.println();

        }

    }

    private static boolean mazeRunner(int x, int y) {

        try {

            if (maze[y][x] == 0 || maze[y][x] == 3) {
                return false;
            }

            maze[y][x] = 3;
            if (y == maze.length - 1 && x == maze[y].length - 1) {

                maze[y][x] = 7;
                return true;

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        //Move up
        if (mazeRunner(x, y - 1)) {

            maze[y][x] = 7;
            return true;

        }

        //Move right
        if (mazeRunner(x + 1, y)) {

            maze[y][x] = 7;
            return true;

        }

        //Move down
        if (mazeRunner(x, y + 1)) {

            maze[y][x] = 7;
            return true;

        }

        //Move left
        if (mazeRunner(x - 1, y)) {

            maze[y][x] = 7;
            return true;

        }
        return false;

    }

}
