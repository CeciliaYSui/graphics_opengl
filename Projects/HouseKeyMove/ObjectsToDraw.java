/* --------------------------------------------------------------------------
 * 
 * Author -------------- Cecilia Y. Sui 
 * Assignment ---------- 3D House
 * Course -------------- Computer Graphics
 * Instructor ---------- Dr. Crawley 
 * Date of Submission -- November 13, 2019
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
    // Grass Plane 
    // ----------------------------------------------------------------------------
    public static ObjectsToDraw plane = new ObjectsToDraw(
        new double[][] {
            {5,-0.1,5},{-5,-0.1,5},{-5,-0.1,-5},{5,-0.1,-5},
            {5,-0.2,5},{-5,-0.2,5},{-5,-0.2,-5},{5,-0.2,-5}
        }, 
        new int[][] {
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw floor1 = new ObjectsToDraw(
        new double[][] {
            {3.1,0,3.1},{-3.1,0,3.1},{-3.1,0,-3.1},{3.1,0,-3.1},
            {3.1,-0.1,3.1},{-3.1,-0.1,3.1},{-3.1,-0.1,-3.1},{3.1,-0.1,-3.1}
        }, 
        new int[][]  {
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7}
        }
    ); 


    public static ObjectsToDraw tabletop = new ObjectsToDraw(
        new double[][] {
            {1.5,1.0,0.8},{-1.5,1.0,0.8},{-1.5,1.0,-0.8},{1.5,1.0,-0.8},
            {1.5,0.9,0.8},{-1.5,0.9,0.8},{-1.5,0.9,-0.8},{1.5,0.9,-0.8}
        },
        new int[][] {
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    );

    public static ObjectsToDraw leg1 = new ObjectsToDraw(
        new double[][]{
            {-1.3,0.9,-0.6},{-1.5,0.9,-0.6},{-1.5,0.9,-0.8},{-1.3,0.9,-0.8},
            {-1.3,0,-0.6},{-1.5,0,-0.6},{-1.5,0,-0.8},{-1.3,0,-0.8}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw leg2 = new ObjectsToDraw(
        new double[][]{
            {-1.3,0.9,0.8},{-1.5,0.9,0.8},{-1.5,0.9,0.6},{-1.3,0.9,0.6},
            {-1.3,0,0.8},{-1.5,0,0.8},{-1.5,0,0.6},{-1.3,0,0.6}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw leg3 = new ObjectsToDraw(
        new double[][]{
            {1.5,0.9,-0.6},{1.3,0.9,-0.6},{1.3,0.9,-0.8},{1.5,0.9,-0.8},
            {1.5,0,-0.6},{1.3,0,-0.6},{1.3,0,-0.8},{1.5,0,-0.8}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw leg4 = new ObjectsToDraw(
        new double[][]{
            {1.5,0.9,0.8},{1.3,0.9,0.8},{1.3,0.9,0.6},{1.5,0.9,0.6},
            {1.5,0,0.8},{1.3,0,0.8},{1.3,0,0.6},{1.5,0,0.6}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 


    public static ObjectsToDraw wall1 = new ObjectsToDraw(
        new double[][] {
            {-1.8,3,2.9},{-2.9,3,2.9},{-2.9,3,2.7},{-1.8,3,2.7},
            {-1.8,0,2.9},{-2.9,0,2.9},{-2.9,0,2.7},{-1.8,0,2.7}
        }, 
        new int[][] {
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw wall2 = new ObjectsToDraw(
        new double[][] {
            {2.9,3,2.9},{1.8,3,2.9},{1.8,3,2.7},{2.9,3,2.7},
            {2.9,0,2.9},{1.8,0,2.9},{1.8,0,2.7},{2.9,0,2.7}
        }, 
        new int[][] {
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw wall3 = new ObjectsToDraw(
        new double[][]{
            {1.8,0.7,2.9},{-1.8,0.7,2.9},{-1.8,0.7,2.7},{1.8,0.7,2.7},
            {1.8,0,2.9},{-1.8,0,2.9},{-1.8,0,2.7},{1.8,0,2.7}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7}
        }
    ); 

    public static ObjectsToDraw wall4 = new ObjectsToDraw(
        new double[][]{
            {1.8,3,2.9},{-1.8,3,2.9},{-1.8,3,2.7},{1.8,3,2.7},
            {1.8,2.3,2.9},{-1.8,2.3,2.9},{-1.8,2.3,2.7},{1.8,2.3,2.7}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7}
        }
    ); 


    public static ObjectsToDraw wall5 = new ObjectsToDraw(
        new double[][]{
            {0.5,2.3,2.9},{-0.5,2.3,2.9},{-0.5,2.3,2.7},{0.5,2.3,2.7}, 
            {0.5,0.7,2.9},{-0.5,0.7,2.9},{-0.5,0.7,2.7},{0.5,0.7,2.7}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 


    public static ObjectsToDraw sidewall = new ObjectsToDraw(
        new double[][]{
            {-2.9,3,2.7},{-2.9,3,-2.7},{-2.7,3,-2.7},{-2.7,3,2.7},
            {-2.9,0,2.7},{-2.9,0,-2.7},{-2.7,0,-2.7},{-2.7,0,2.7}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw sidedoor = new ObjectsToDraw(
        new double[][]{
            {-3,1.5,0.6},{-3,1.5,-0.6},{-2.9,1.5,-0.6},{-2.9,1.5,0.6},
            {-3,0,0.6},{-3,0,-0.6},{-2.9,0,-0.6},{-2.9,0,0.6}
        }, 
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw lightbar = new ObjectsToDraw(
        new double[][]{
            {0.05,6.1,0.05},{-0.05,6.1,0.05},{-0.05,6.1,-0.05},{0.05,6.1,-0.05},
            {0.05,5.5,0.05},{-0.05,5.5,0.05},{-0.05,5.5,-0.05},{0.05,5.5,-0.05}
        },
        new int[][]{
            {0,1,4},{5,4,1},{2,3,6},{7,6,3},{3,2,0},{1,0,2},
            {2,6,1},{5,1,6},{7,3,4},{0,4,3},{6,7,5},{4,5,7} 
        }
    ); 

    public static ObjectsToDraw bipyramid = new ObjectsToDraw(
        new double[][]{
            {0,0,4},{0,0,-4},{-2,0,0},{-1,1.73,0},{1,1.73,0},{2,0,0},{1,-1.73,0},{-1,-1.73,0}
        }, 
        new int[][]{
            {0,3,2},{0,4,3},{0,5,4},{0,6,5},{0,7,6},{0,2,7},{1,2,3},{1,3,4},{1,4,5},{1,5,6},{1,6,7},{1,7,2}
        }
    ); 
}