package wayfair;

/*
Imagine we have an image. We'll represent this image as a simple 2D array where every pixel is a 1 or a 0.

The image you get is known to have potentially many distinct rectangles of 0s on a background of 1's. Write a function that takes in the image and returns the coordinates of all the 0 rectangles -- top-left and bottom-right; or top-left, width and height.

image1 = [
  [0, 1, 1, 1, 1, 1, 1],
  [1, 1, 1, 1, 1, 1, 1],
  [0, 1, 1, 0, 0, 0, 1],
  [1, 0, 1, 0, 0, 0, 1],
  [1, 0, 1, 1, 1, 1, 1],
  [1, 0, 1, 0, 0, 1, 1],
  [1, 1, 1, 0, 0, 1, 1],
  [1, 1, 1, 1, 1, 1, 0],
]

Sample output variations (only one is necessary):

findRectangles(image1) =>
  // (using top-left-row-column and bottom-right):
  [
    [[0,0],[0,0]],
    [[2,0],[2,0]],
    [[2,3],[3,5]],
    [[3,1],[5,1]],
    [[5,3],[6,4]],
    [[7,6],[7,6]],
  ]
  // (using top-left-row-column and width/height):
  [
    [[0,0],[1,1]],
    [[2,0],[1,1]],
    [[2,3],[3,2]],
    [[3,1],[1,3]],
    [[5,3],[2,2]],
    [[7,6],[1,1]],
  ]

Other test cases:

image2 = [
  [0],
]

findRectangles(image2) =>
  // (using top-left-row-column and bottom-right):
  [
    [[0,0],[0,0]],
  ]

  // (using top-left-row-column and width/height):
  [
    [[0,0],[1,1]],
  ]

image3 = [
  [1],
]

findRectangles(image3) => []

image4 = [
  [1, 1, 1, 1, 1],
  [1, 0, 0, 0, 1],
  [1, 0, 0, 0, 1],
  [1, 0, 0, 0, 1],
  [1, 1, 1, 1, 1],
]

findRectangles(image4) =>
  // (using top-left-row-column, and bottom-right or width/height):
  [
    [[1,1],[3,3]],
  ]

n: number of rows in the input image
m: number of columns in the input image


*/

import java.util.*;

public class Solution {
  public static void main(String[] argv) {
    int[][] image1 = {
      {0, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1},
      {0, 1, 1, 0, 0, 0, 1},
      {1, 0, 1, 0, 0, 0, 1},
      {1, 0, 1, 1, 1, 1, 1},
      {1, 0, 1, 0, 0, 1, 1},
      {1, 1, 1, 0, 0, 1, 1},
      {1, 1, 1, 1, 1, 1, 0},
    };

    int[][] image2 = {
      {0},
    };

    int[][] image3 = {
      {1},
    };

    int[][] image4 = {
      {1, 1, 1, 1, 1},
      {1, 0, 0, 0, 1},
      {1, 0, 0, 0, 1},
      {1, 0, 0, 0, 1},
      {1, 1, 1, 1, 1},
    };
    
    List<Cordinates> l1 = findRectangles(image1);
    List<Cordinates> l2 = findRectangles(image2);
    List<Cordinates> l3 = findRectangles(image3);
    List<Cordinates> l4 = findRectangles(image4);
    
    printR(l1);
    printR(l2);
    printR(l3);
    printR(l4);
  }
  
  public static void printR(List<Cordinates> list) {
    System.out.println(""); // Empty line
    System.out.println("### SOLUTION BEGIN ###");
    for(int i=0; i<list.size(); i++) {
      Cordinates c = list.get(i);
      System.out.println(c.x + " " + c.y + " " + c.l + " " + c.h);
    }
    System.out.println("### SOLUTION END ###");
    System.out.println(""); // Empty line
  }
  
  public static List<Cordinates> findRectangles(int[][] image) {
    
    List<Cordinates> result = new ArrayList<>();
    
    for(int i=0; i<image.length; i++) {
      for(int j=0; j<image[i].length; j++) {
        if(image[i][j] == 0) {
          int row = 1;
          for(int k=i+1; k<image.length; k++) {
            if(image[k][j] != 0) {
              row = k-i;
              break;
            }
          }
          
          int col = 1;
          for(int k=j+1; k<image[i].length; k++) {
            if(image[i][k] != 0) {
              col = k-j;
              break;
            }
          }
          
          Cordinates c = new Cordinates(i,j,row,col);
          flipToOne(image ,c);
          result.add(c);
        }
      }
    }
    
    return result;
  }

  private static void flipToOne(int[][] image, Cordinates c) {
    for(int i=c.x; i< c.x+c.h; i++) {
      for(int j=c.y; j< c.y+c.l; j++) {
        image[i][j] = 1;
      }
    }
  }
}

class Cordinates {
    
  public int x;
  public int y;
  public int h;
  public int l;
  
  public Cordinates(int x, int y, int h, int l) {
    this.x = x;
    this.y =y;
    this.h =h;
    this.l =l;
  }
}
