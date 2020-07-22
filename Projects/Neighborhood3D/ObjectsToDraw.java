/* --------------------------------------------------------------------------
 * 
 * Author -------------- Cecilia Y. Sui 
 * Assignment ---------- 3D House
 * Course -------------- Computer Graphics
 * Instructor ---------- Dr. Crawley 
 * Date of Submission -- November 2, 2019
 * Language Used ------- Java & OpenGL
 * Description --------- Storage of vertices and faces data 
 * 
--------------------------------------------------------------------------- */


public class ObjectsToDraw {
    // ----------------------------------------------------------------------------
    // Initialization
    // ----------------------------------------------------------------------------
    public double[][] vertices;
    public int[][] faces;
   
    // ----------------------------------------------------------------------------
    // Constructor 
    // ----------------------------------------------------------------------------
    public ObjectsToDraw(double[][] vertices, int[][] faces) {
        this.vertices = vertices;
        this.faces = faces;
    }
    

    // ----------------------------------------------------------------------------
    // Neighborhood Plane 
    // ----------------------------------------------------------------------------
    public static ObjectsToDraw plane = new ObjectsToDraw(
        new double[][] {
            {-20,-0.001,5},{20,-0.001,5},{20,-0.001,-10},{-20,-0.001,-10},
            {-20,-0.3,5},{20,-0.3,5},{20,-0.3,-10},{-20,-0.3,-10}
        }, 
        new int[][] {
            {0,2,1},{0,3,2},{4,1,5},{4,0,1},{1,6,5},{1,2,6},
            {2,7,6},{2,3,7},{4,3,0},{4,7,3},{5,7,4},{5,6,7}
        }
    ); 

    // ----------------------------------------------------------------------------
    // Neighborhood Road 
    // ----------------------------------------------------------------------------
    public static ObjectsToDraw road = new ObjectsToDraw(
        new double[][] {
            {-20,0.001,-1},{20,0.001,-1},{20,0.001,-2},{-20,0.001,-2},
            {-20,0,-1},{20,0,-1},{20,0,-2},{-20,0,-2}
        }, 
        new int[][] {
            {0,2,1},{0,3,2},{4,1,5},{4,0,1},{1,6,5},{1,2,6},
            {2,7,6},{2,3,7},{4,3,0},{4,7,3},{5,7,4},{5,6,7}
        }
    ); 

    // ----------------------------------------------------------------------------
    // house#1  
    // ----------------------------------------------------------------------------
    public static ObjectsToDraw house1_lower = new ObjectsToDraw(
        new double[][] {
            {0,0,0}, {1.3,0,0}, {0,1,0}, {1.3,1,0}, 
            {1.3,0,1}, {0,0,1}, {0,1,1}, {1.3,1,1}
        }, 
        new int[][] {
            {0,1,2},{3,2,1},{1,4,3},{7,3,4},{4,5,7},{6,7,5},{5,0,6},{2,6,0},{2,3,6},{7,6,3},{1,0,4},{5,4,0}
        }
    ); 

    public static ObjectsToDraw house1_upper = new ObjectsToDraw(
        new double[][] {
            {0,1.1,0}, {1.3,1.1,0}, {0,2.1,0}, {1.3,2.1,0}, 
            {1.3,1.1,1}, {0,1.1,1}, {0,2.1,1}, {1.3,2.1,1}
        }, 
        new int[][]{
            {0,1,2},{3,2,1},{1,4,3},{7,3,4},{4,5,7},{6,7,5},{5,0,6},{2,6,0},{2,3,6},{7,6,3},{1,0,4},{5,4,0}
        }
    ); 

    public static ObjectsToDraw house1_door = new ObjectsToDraw(
        new double[][]{
            {0.85,0,-0.05},{0.45,0,-0.05},{0.45,0.5,-0.05},{0.85,0.5,-0.05}, 
            {0.85,0,0},{0.45,0,0},{0.45,0.5,0},{0.85,0.5,0}
        }, 
        new int[][]{
            {0,1,2},{3,2,1},{1,4,3},{7,3,4},{4,5,7},{6,7,5},{5,0,6},{2,6,0},{2,3,6},{7,6,3},{1,0,4},{5,4,0}
        }
    ); 

    public static ObjectsToDraw house1_floor1 = new ObjectsToDraw(
        new double[][] {
            {-0.1,1,-0.1}, {1.4,1,-0.1},{-0.1,1.1,-0.1}, {1.4,1.1,-0.1},
            {1.4,1,1.1}, {-0.1,1,1.1}, {-0.1,1.1,1.1}, {1.4,1.1,1.1} 
        }, 
        new int[][]  {
            {0,1,2},{3,2,1},{1,4,3},{7,3,4},{4,5,7},{6,7,5},{5,0,6},{2,6,0},{2,3,6},{7,6,3},{1,0,4},{5,4,0}
        }
    ); 

    public static ObjectsToDraw house1_floor2 = new ObjectsToDraw(
        new double[][] {
            {-0.1,2.1,-0.1}, {1.4,2.1,-0.1},{-0.1,2.2,-0.1}, {1.4,2.2,-0.1},
            {1.4,2.1,1.1}, {-0.1,2.1,1.1}, {-0.1,2.2,1.1}, {1.4,2.2,1.1} 
        }, 
        new int[][] {
            {0,1,2},{3,2,1},{1,4,3},{7,3,4},{4,5,7},{6,7,5},{5,0,6},{2,6,0},{2,3,6},{7,6,3},{1,0,4},{5,4,0}
        }
    ); 


    public static ObjectsToDraw house2_roof = new ObjectsToDraw(
        new double[][]{
            {0,2,-5}, {2,2,-5}, {2,2,-3}, {0,2,-3}, {1,3,-4}
        }, 
        new int[][] {
            {4,0,1}, {4,1,2}, {4,2,3}, {4,3,0}
        }
    ); 

    public static ObjectsToDraw house2_base = new ObjectsToDraw(
        new double[][] {
            {0,0,-5}, {2,0,-5}, {0,2,-5}, {2,2,-5},
            {2,0,-3}, {0,0,-3}, {0,2,-3}, {2,2,-3}  
        }, 
        new int[][] {
            {0,1,2},{3,2,1},{1,4,3},{7,3,4},{4,5,7},{6,7,5},{5,0,6},{2,6,0},{2,3,6},{7,6,3},{1,0,4},{5,4,0}
        }
    ); 

    public static ObjectsToDraw house2_door = new ObjectsToDraw(
        new double[][] {
            {0.7,0,-5.1}, {1.3,0,-5.1}, {0.7,1,-5.1}, {1.3,1,-5.1},
            {1.3,0,-5}, {0.7,0,-5}, {0.7,1,-5}, {1.3,1,-5}    
        }, 
        new int[][] {
            {0,1,2},{3,2,1},{1,4,3},{7,3,4},{4,5,7},{6,7,5},{5,0,6},{2,6,0},{2,3,6},{7,6,3},{1,0,4},{5,4,0}
        }
    ); 

}