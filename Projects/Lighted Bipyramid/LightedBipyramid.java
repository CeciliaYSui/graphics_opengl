/* --------------------------------------------------------------------------
 * 
 * Author -------------- Cecilia Y. Sui 
 * Assignment ---------- Lighted Hexagonal Bipyramid
 * Course -------------- Computer Graphics
 * Instructor ---------- Dr. Crawley 
 * Date of Submission -- October 18, 2019
 * Language Used ------- Java & OpenGL
 * Class Imported ------ Camera from Camera.java (written by Dr. Eck)
 * Description --------- Use JOGL to draw a lighted hexagon bipyramid with 
 * --------------------- an indexed face set (IFS) and 1+ light sources
 * 
--------------------------------------------------------------------------- */

// ----------------------------------------------------------------------------
// Imports 
// ----------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

// ----------------------------------------------------------------------------
// LightedBipyramid class 
// ----------------------------------------------------------------------------
public class LightedBipyramid extends GLJPanel implements GLEventListener {
    public static void main(String[] args) {
        JFrame window = new JFrame("Lighted Hexagonal Bipyramid");
        LightedBipyramid panel = new LightedBipyramid();
        window.setContentPane(panel);
        window.pack();
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.requestFocusInWindow();
    }

    // ----------------------------------------------------------------------------
    // Constructor 
    // ----------------------------------------------------------------------------
    public LightedBipyramid() {
        setPreferredSize( new Dimension(800,800) );
        addGLEventListener(this);
    }

    // ----------------------------------------------------------------------------
    // Declare private variables
    // ----------------------------------------------------------------------------
    private Camera camera;
    private JRadioButtonMenuItem coloredFaces;

    // ----------------------------------------------------------------------------
    // display method 
    // ----------------------------------------------------------------------------
    public void display(GLAutoDrawable drawable) {    
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClearColor(0,0,0,0);
        gl2.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );

        // ----------------------------------------------------------------------------
        // Set vertices, faces, colors for hexagonal bipyramid 
        // ----------------------------------------------------------------------------
        double[][] vertexList = {{0,0,4},{0,0,-4},{-2,0,0},{-1,1.73,0},{1,1.73,0},{2,0,0},{1,-1.73,0},{-1,-1.73,0}};
        int[][] faceList = {{0,3,2},{0,4,3},{0,5,4},{0,6,5},{0,7,6},{0,2,7},{1,2,3},{1,3,4},{1,4,5},{1,5,6},{1,6,7},{1,7,2}};
        camera.apply(gl2);

        // ----------------------------------------------------------------------------
        // Specify Material Chosen
        // ---------------------------------------------------------------------------- 
        
        float amb[] = {0.2f,0,0,1.0f}; 
        float dif[] = {0,0,0.4f,1.0f};
        float spe[] = {0,0.8f,0,1.0f};  
        
        gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, amb,0); 
        gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, dif,0); 
        gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, spe,0); 
        gl2.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 128.0f);  // controls size of specular point 

        gl2.glPushMatrix();
        int i,j;
        gl2.glColor3d(1,1,1);  
        double Vx, Vy, Vz, Wx, Wy, Wz, Nx, Ny, Nz, x[], y[], z[]; 
        for (i = 0; i < faceList.length; i++) {
            gl2.glBegin(GL2.GL_TRIANGLE_FAN);
            // Compute Normal Vectors ----------------------------------------------------------
            // get coordinates of 3 vertices 
            x = vertexList[faceList[i][0]]; 
            y = vertexList[faceList[i][1]]; 
            z = vertexList[faceList[i][2]]; 
            // Convert bond vectors to free vectors (centered at origin)
            Vx = y[0] - x[0]; 
            Vy = y[1] - x[1]; 
            Vz = y[2] - x[2]; 
            Wx = z[0] - x[0]; 
            Wy = z[1] - x[1]; 
            Wz = z[2] - x[2]; 
            // Compute cross product of V & W
            Nx = Vy * Wz - Vz * Wy; 
            Ny = Vz * Wx - Vx * Wz; 
            Nz = Vx * Wy - Vy * Wx;
            // Declare Normal Vector for each face
            gl2.glNormal3d(Nx, Ny, Nz);   
            for (j = 0; j < faceList[i].length; j++) {
                int vertexNum = faceList[i][j];
                gl2.glVertex3dv(vertexList[vertexNum], 0);
            }

            gl2.glEnd();
        }
        gl2.glPopMatrix();
    } 

    // ----------------------------------------------------------------------------
    // init method 
    // ----------------------------------------------------------------------------
    public void init(GLAutoDrawable graphics) {
        GL2 gl2 = graphics.getGL().getGL2();
        gl2.glClearColor(0, 0, 0, 1); 
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glEnable(GL2.GL_LIGHTING);
        gl2.glEnable(GL2.GL_NORMALIZE);
        gl2.glEnable(GL2.GL_RESCALE_NORMAL); 
        gl2.glEnable(GL2.GL_COLOR_MATERIAL);
        // Disable smoothing to get flat surfaces (sharp edeges)
        gl2.glShadeModel(GL2.GL_FLAT); 
        gl2.glPolygonOffset(1,2);
        camera = new Camera(); 
        camera.lookAt(4,15,6, 0,0,0, 0,1,0);
        camera.installTrackball(this);
        
        // Light source: LIGHT0
        gl2.glEnable(GL2.GL_LIGHT0);
        float diffuse[] = {0f, 0f, 0.6f, 1.0f}; 
        float specular[] = {0f, 0.8f, 0f, 1.0f};
        float ambient[] = {0.4f, 0f, 0f, 1.0f}; 
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0); 
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specular, 0); 
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0); 
        
    }


    public void dispose(GLAutoDrawable graphics) {
    }
    public void reshape(GLAutoDrawable graphics, int x, int y, int width, int height) {
    }

}