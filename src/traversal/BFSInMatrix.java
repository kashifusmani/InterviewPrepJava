package traversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSInMatrix {
    public void BFS(int[][] grid) {

        int h = grid.length;
        if (h == 0)
            return;
        int l = grid[0].length;

        boolean[][] visited = new boolean[h][l];

        Queue<String> queue = new LinkedList<>();

        queue.add(0 + "," + 0);

        System.out.println("Breadth-First Traversal: ");
        while (queue.isEmpty() == false) {

            String x = queue.remove();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);

            if (row < 0 || col < 0 || row >= h || col >= l || visited[row][col])
                continue;

            visited[row][col] = true;
            System.out.print(grid[row][col] + " ");
            //elements up, left, down , right are treated as "children"
            queue.add(row + "," + (col - 1)); //go left
            queue.add(row + "," + (col + 1)); //go right
            queue.add((row - 1) + "," + col); //go up
            queue.add((row + 1) + "," + col); //go down
        }
    }

    public void DFS(int[][] grid) {

        int h = grid.length;
        if (h == 0)
            return;
        int l = grid[0].length;

        boolean[][] visited = new boolean[h][l];

        Stack<String> stack = new Stack<>();

        stack.push(0 + "," + 0);

        System.out.println("Depth-First Traversal: ");
        while (stack.empty() == false) {

            String x = stack.pop();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);

            if(row<0 || col<0 || row>=h || col>=l || visited[row][col])
                continue;

            visited[row][col]=true;
            System.out.print(grid[row][col] + " ");
            stack.push(row + "," + (col-1)); //go left
            stack.push(row + "," + (col+1)); //go right
            stack.push((row-1) + "," + col); //go up
            stack.push((row+1) + "," + col); //go down
        }
    }

    public static void main(String[] args) {

        int [][] grid = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        BFSInMatrix d = new BFSInMatrix();
        d.BFS(grid);
        System.out.println();
        d.DFS(grid);
    }
}