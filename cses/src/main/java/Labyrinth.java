/*
Solution to CSES Problem: https://cses.fi/problemset/task/1193
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Labyrinth {

    private static final int[][] NEIGHBOURS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final char[] DIRECTIONS = {'R', 'L', 'D', 'U'};
    private static int ROWS;
    private static int COLS;

    public static void main(String... args) {

        Scanner sc = new Scanner(System.in);
        ROWS = sc.nextInt();
        COLS = sc.nextInt();

        Boolean[][] visited = new Boolean[ROWS][COLS];

        for (Boolean[] row : visited) {
            Arrays.fill(row, Boolean.FALSE);
        }

        char[][] housemap = new char[ROWS][COLS];

        int totalRooms = 0;

        for (int row = 0; row < ROWS; row++) {
            String line = sc.next();
            for (int col = 0; col < COLS; col++) {
                housemap[row][col] = line.charAt(col);
            }
        }

        FootStep footStep = new FootStep();
        int totalSteps = 0;
        Result result = null;

        search:
        {
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if (housemap[row][col] == 'A') {
                        footStep.cellType = 'A';
                        footStep.row = row;
                        footStep.col = col;
                        result = findDestination(footStep, visited, housemap);
                        break search;
                    }
                }
            }
        }
        if (result.destination == null) {
            // we haven't found the path
            System.out.println("NO");
        } else {



            LinkedList<Character> footSteps = new LinkedList();
            FootStep backtrackFootStep = result.destination;

            while (backtrackFootStep != null) {
                footSteps.add(backtrackFootStep.cameFromDirection);
                backtrackFootStep = backtrackFootStep.previous;
            }

            Collections.reverse(footSteps);
            StringBuilder sb = new StringBuilder();
            for (char c: footSteps)
                sb.append(c);

            // display the result
            System.out.print("YES\n"+result.steps+"\n"+sb.toString().trim());
        }
    }

    public static Result findDestination(FootStep begin, Boolean[][] visited, char[][] housemap) {

        Queue<FootStep> queue = new LinkedList<>();
        queue.add(begin);
        visited[begin.row][begin.col] = Boolean.TRUE;
        int steps = 1;

        int rows = visited.length;
        int cols = visited[0].length;
        Result result = new Result();

        while (!queue.isEmpty()) {

            LinkedList<FootStep> next = new LinkedList<>();
            while (!queue.isEmpty()) {

                FootStep currentFootStep = queue.poll();

                for (int idx = 0; idx < NEIGHBOURS.length; idx++) {

                    int nextRow = currentFootStep.row + NEIGHBOURS[idx][0];
                    int nextCol = currentFootStep.col + NEIGHBOURS[idx][1];

                    if (isSafeCellToExplore(nextRow, nextCol, housemap, visited)) {

                        if (visited[nextRow][nextCol]) {
                            continue;
                        }

                        // mark the cell as visited
                        visited[nextRow][nextCol] = true;

                        FootStep nextFootStep = new FootStep();
                        nextFootStep.previous = currentFootStep;
                        nextFootStep.cameFromDirection = DIRECTIONS[idx];
                        nextFootStep.row = nextRow;
                        nextFootStep.col = nextCol;
                        nextFootStep.cellType = housemap[nextRow][nextCol];

                        if (housemap[nextFootStep.row][nextFootStep.col] == 'B') {
                            // we have reached the destination, lets exit
                            result.destination = nextFootStep;
                            result.steps = steps;
                            return result;
                        }

                        // 1. add it to the next set of nodes to be visited
                        // 2. add the current next pointer to nextFootStep
                        next.add(nextFootStep);
                    }
                }
            }

            // clear the older queue, add new level steps and increment the tier
            queue.clear();
            queue.addAll(next);
            steps += 1;
        }

        return result;
    }

    public static boolean isSafeCellToExplore(int row, int col, char[][] housemap, Boolean[][] visited) {

        return row >= 0 && row < ROWS && col >= 0 && col < COLS && housemap[row][col] != '#' && !visited[row][col];
    }

    static class FootStep {

        char cellType;
        char cameFromDirection;
        int row;
        int col;
        private FootStep previous;

    }

    static class Result {

        int steps;
        FootStep destination;
    }
}
