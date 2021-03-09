//package graph;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class CountingRooms {

    public static void main(String... args) {

        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        Boolean[][] visited = new Boolean[rows][cols];
        for(Boolean row[]: visited)
            Arrays.fill(row, Boolean.FALSE);

        char housemap[][] = new char[rows][cols];

        int totalRooms = 0;

        for (int row = 0; row < rows; row++) {
                String line = sc.next();
                for (int col=0;col<cols;col++) {
                    housemap[row][col] = line.charAt(col);
                }
            }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (housemap[row][col] == '.' && !visited[row][col]) {
                    dfsExplore(housemap, row, col, visited);
                    totalRooms += 1;
                }
            }
        }

        System.out.println(totalRooms);
    }

    static class Coordinate {

        int row;
        int col;

        Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void dfsExplore(char[][] housemap, int row, int col, Boolean[][] visited) {

        Stack<Coordinate> stck = new Stack<Coordinate>();
        stck.push(new Coordinate(row, col));

        while (!stck.isEmpty()){

            Coordinate c = stck.pop();

            if (visited[c.row][c.col])
                continue;
            else{
                visited[c.row][c.col] = Boolean.TRUE;
                int[][] neighbours = {{0,1},{0,-1},{1,0},{-1,0}};
                for(int[] neighbour: neighbours){
                    int newRow = c.row + neighbour[0];
                    int newCol = c.col + neighbour[1];
                    if (isValidCell(housemap, visited, newRow, newCol, housemap.length, housemap[0].length)){
                        stck.push(new Coordinate(newRow, newCol));
                    }
                }
            }
        }
    }

    public static boolean isValidCell(char[][] housemap, Boolean[][] visited, int row, int col, int rows, int cols){
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] ||
            housemap[row][col] == '#'){
            return false;
        }
        return true;
    }
}
