/* --------------------------------------------------------------------------
 * 
 * Author -------------- Cecilia Y. Sui 
 * Assignment ---------- Final Exam Project 
 * Course -------------- Computer Graphics
 * Instructor ---------- Dr. Crawley 
 * Date of Submission -- December 6, 2019
 * Language Used ------- Java & OpenGL
 * Description --------- Storage of vertices and faces data 
 * 
 * Hint ---------------- Counterclockwise of vertices gives the front  
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
    //  Plane 
    // ----------------------------------------------------------------------------
    public static ObjectsToDraw plane = new ObjectsToDraw(
        new double[][] {
            {-10,-0.001,10},{10,-0.001,10},{10,-0.001,-10},{-20,-0.001,-10},
            {-10,-0.3,10},{10,-0.3,10},{10,-0.3,-10},{-20,-0.3,-10}
        }, 
        new int[][] {
            {0,2,1},{0,3,2},{4,1,5},{4,0,1},{1,6,5},{1,2,6},
            {2,7,6},{2,3,7},{4,3,0},{4,7,3},{5,7,4},{5,6,7}
        }
    ); 

    public static ObjectsToDraw pyramid = new ObjectsToDraw(
        new double[][]{
            {0,1.3,0},{1,0,1},{-1,0,1},{-1,0,-1},{1,0,-1}
        }, 
        new int[][]{
            {0,2,3},{0,1,2},{0,3,4},{0,4,1},{4,3,2,1}
        }
    ); 
}