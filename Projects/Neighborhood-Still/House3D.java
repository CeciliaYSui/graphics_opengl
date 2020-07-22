/* --------------------------------------------------------------------------
 * 
 * Author -------------- Cecilia Y. Sui 
 * Assignment ---------- 3D House
 * Course -------------- Computer Graphics
 * Instructor ---------- Dr. Crawley 
 * Date of Submission -- November 2, 2019
 * Language Used ------- Java & OpenGL
 * Class Imported ------ Camera from Camera.java (written by Dr. Eck)
 * --------------------- ObjectsToDraw (written by Cecilia S.)
 * Description --------- Use JOGL to draw a neighborhood with houses 
 * Warning ------------- It takes a while to render the graphics. 
--------------------------------------------------------------------------- */

// ----------------------------------------------------------------------------
// Imports 
// ----------------------------------------------------------------------------
import com.jogamp.opengl.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.*;
import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

// ----------------------------------------------------------------------------
// House3D class 
// ----------------------------------------------------------------------------
public class House3D extends GLJPanel implements GLEventListener {
    public static void main(String[] args) {
        JFrame window = new JFrame("Lighted Neighborhood");
        House3D panel = new House3D();
        window.setContentPane(panel);
        window.pack();
        window.setLocation(100,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.requestFocusInWindow();
    }

    // ----------------------------------------------------------------------------
    // Constructor 
    // ----------------------------------------------------------------------------
    public House3D() {
        setPreferredSize( new Dimension(800,800) );
        addGLEventListener(this);
    }

    // ----------------------------------------------------------------------------
    // Declare private variables
    // ----------------------------------------------------------------------------
    private Camera camera;
    private ObjectsToDraw currObject; 
    private int currentTexture;
    private int angle; 

    private String[] textureFileNames = {
        "marble.jpg", // 0
        "grass.jpg", // 1
        "bluewall.jpg", // 2
        "brick001.jpg", // 3
        "teapot.jpg", // 4
        "road.jpg", // 5
        "bluewall.jpg", // 6
        "pinkwall.jpg", // 7
        "red.jpg", // 8
        "gold.jpg" // 9 
    };

    private Texture[] textures = new Texture[textureFileNames.length];

    // ----------------------------------------------------------------------------
    // display method 
    // ----------------------------------------------------------------------------
    public void display(GLAutoDrawable drawable) {    
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClearColor(0,0,0,0);
        gl2.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        camera.apply(gl2);
        gl2.glBindTexture(GL2.GL_TEXTURE_2D, 0);
        
        float amb[] = {0.1f,0,0,1.0f}; 
        float dif[] = {0,0,0.1f,1.0f};
        float spe[] = {0,0.8f,0,1.0f};  
        
        gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, amb,0); 
        gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, dif,0); 
        gl2.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, spe,0); 
        gl2.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 128.0f); 

        // ----------------------------------------------------------------------------
        // ----- EDIT HERE ------
        // Scale the scene to see details and overview 
        // ----------------------------------------------------------------------------
        gl2.glScaled(0.4,0.4,0.4); 

        // ----------------------------------------------------------------------------
        // draw objects 
        // ----------------------------------------------------------------------------
        gl2.glPushMatrix(); 
        currObject = ObjectsToDraw.plane; 
        textures[1].bind(gl2);
        drawobject(gl2, currObject); 
        currObject = ObjectsToDraw.road; 
        textures[5].bind(gl2); 
        drawobject(gl2, currObject); 
        gl2.glPopMatrix(); 

        gl2.glPushMatrix();
        currObject = ObjectsToDraw.road; 
        textures[5].bind(gl2); 
        gl2.glTranslated(0,0,4); 
        drawobject(gl2, currObject); 
        gl2.glTranslated(0,0,-9); 
        drawobject(gl2, currObject);  
        gl2.glTranslated(0,0,-6.5); 
        drawobject(gl2, currObject);
        gl2.glPopMatrix(); 


        for (int m = -6; m <= 6; m++){
            double n = m * 2.6; 
            double[] t = {n,0,0}; 
            transHouse1(gl2,t); 
        }
        for (int x = -4; x <= 4; x++){
            double y = x * 4; 
            double[] z = {y,0,0}; 
            transHouse2(gl2, z); 
        }
        gl2.glPushMatrix(); 
        gl2.glScaled(0.45,0.45,0.45); 
        for (int aa = -4; aa <= 4; aa++){
            double bb = aa * 9; 
            double[] cc = {bb,0.1,-22}; 
            transHouse3(gl2,cc); 
        } 
        gl2.glPopMatrix(); 

        // draw gates
        gl2.glPushMatrix();
        gl2.glScaled(0.8,0.8,0.8); 
        gl2.glTranslated(-3.5,0,10);  
        drawGate(gl2); 
        gl2.glScaled(0.6,0.6,0.6); 
        gl2.glPushMatrix(); 
        gl2.glTranslated(-18,0,0); 
        drawGate(gl2); 
        gl2.glPopMatrix(); 
        gl2.glPushMatrix(); 
        gl2.glTranslated(22.8,0,0); 
        drawGate(gl2);
        gl2.glPopMatrix(); 
        gl2.glPopMatrix(); 

        // draw teapot 
        GLUT glut = new GLUT();
        gl2.glTranslated(0,7.5,9);
        //gl2.glRotated(_angle,0,0); 
        glut.glutSolidTeapot(1.5);

        
    } 

    public void drawGate(GL2 gl2){
        gl2.glPushMatrix(); 
        textures[8].bind(gl2); 
        currObject = ObjectsToDraw.gate_bottom; 
        drawobject(gl2, currObject);
        gl2.glPushMatrix(); 
        gl2.glTranslated(5,0,0); 
        drawobject(gl2, currObject);
        gl2.glPopMatrix(); 
        currObject = ObjectsToDraw.gate_top; 
        drawobject(gl2, currObject); 
        currObject = ObjectsToDraw.gate_side1; 
        drawobject(gl2, currObject); 
        currObject = ObjectsToDraw.gate_side2; 
        drawobject(gl2, currObject); 
        
        gl2.glPushMatrix(); 
        textures[9].bind(gl2);
        GLUT glut = new GLUT();
        gl2.glTranslated(-2.5, 4.5, 1);
        glut.glutSolidSphere(0.5,25,25);
        gl2.glTranslated(0,0,1); 
        glut.glutSolidSphere(0.5,25,25);
        gl2.glTranslated(0,0,-2); 
        glut.glutSolidSphere(0.5,25,25);
        gl2.glTranslated(12,0,0);
        glut.glutSolidSphere(0.5,25,25);
        gl2.glTranslated(0,0,1); 
        glut.glutSolidSphere(0.5,25,25);
        gl2.glTranslated(0,0,1); 
        glut.glutSolidSphere(0.5,25,25);
        gl2.glPopMatrix(); 
        gl2.glTranslated(3.5,5,1); 
        glut.glutSolidSphere(0.5,25,25);
        gl2.glTranslated(1,0,0); 
        glut.glutSolidSphere(0.5,25,25);
        gl2.glTranslated(-2,0,0);
        glut.glutSolidSphere(0.5,25,25);

        gl2.glPopMatrix(); 

    }

    // ----------------------------------------------------------------------------
    // draw the house design #1 method with translation vectors 
    // ----------------------------------------------------------------------------
    public void transHouse1(GL2 gl2, double[] v){
        gl2.glPushMatrix(); 
        textures[3].bind(gl2); 

        gl2.glTranslated(v[0], v[1], v[2]); 

        currObject = ObjectsToDraw.house1_lower; 
        drawobject(gl2, currObject); 

        currObject = ObjectsToDraw.house1_upper; 
        drawobject(gl2, currObject);  

        textures[0].bind(gl2);
        currObject = ObjectsToDraw.house1_floor1; 
        gl2.glColor3d(1,1,1); 
        drawobject(gl2, currObject); 

        currObject = ObjectsToDraw.house1_floor2; 
        gl2.glColor3d(1,1,1); 
        drawobject(gl2, currObject); 

        currObject = ObjectsToDraw.house1_door; 
        gl2.glColor3d(1,1,1); 
        drawobject(gl2,currObject); 

        gl2.glPushMatrix(); 
        GLUT glut = new GLUT();
        gl2.glTranslated(0.65,2.55,0.5); 
        textures[4].bind(gl2);
        gl2.glFrontFace(GL2.GL_CW); 
        glut.glutSolidTeapot(0.5);
        gl2.glFrontFace(GL2.GL_CCW);  
        gl2.glPopMatrix(); 
        
        gl2.glPopMatrix(); 
    }


    // ----------------------------------------------------------------------------
    // draw the house design #2 method with translation vectors 
    // ----------------------------------------------------------------------------
    public void transHouse2(GL2 gl2, double[] v){
        gl2.glPushMatrix(); 
        textures[6].bind(gl2); 
        gl2.glTranslated(v[0], v[1], v[2]); 

        gl2.glPushMatrix(); 
        gl2.glColor4d(0.95,0.61,0.73,0.5); 
        currObject = ObjectsToDraw.house2_base; 
        drawobject(gl2, currObject); 
        gl2.glPopMatrix(); 

        gl2.glPushMatrix(); 
        textures[7].bind(gl2);
        currObject = ObjectsToDraw.house2_roof; 
        drawobject(gl2, currObject);
        gl2.glPopMatrix(); 

        gl2.glPushMatrix(); 
        textures[0].bind(gl2); 
        currObject = ObjectsToDraw.house2_door; 
        drawobject(gl2, currObject); 
        gl2.glPopMatrix(); 

        gl2.glPopMatrix(); 
    }

    public void transHouse3(GL2 gl2, double[] v){
        gl2.glPushMatrix(); 
        gl2.glTranslated(v[0], v[1], v[2]); 

        // drawfloors(gl2);
        textures[0].bind(gl2);
        currObject = ObjectsToDraw.floor1;
        drawobject(gl2, currObject);
        gl2.glPushMatrix();
        gl2.glTranslated(0, 3.1, 0);
        drawobject(gl2, currObject);
        gl2.glTranslated(0, 3.1, 0);
        drawobject(gl2, currObject);
        gl2.glPopMatrix();
        
        // drawtable(gl2);
        textures[6].bind(gl2);
        currObject = ObjectsToDraw.tabletop;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.leg1;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.leg2;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.leg3;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.leg4;
        drawobject(gl2, currObject);

        gl2.glPushMatrix();
        gl2.glTranslated(0, 3.1, 0);
        currObject = ObjectsToDraw.tabletop;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.leg1;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.leg2;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.leg3;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.leg4;
        drawobject(gl2, currObject);
        gl2.glPopMatrix();
        
        // drawteapot(gl2);
        gl2.glPushMatrix();
        GLUT glut = new GLUT();
        gl2.glTranslated(0, 1.22, 0);
        textures[4].bind(gl2);
        gl2.glFrontFace(GL2.GL_CW);
        glut.glutSolidTeapot(0.3);
        gl2.glFrontFace(GL2.GL_CCW);
        gl2.glPopMatrix();

        // draw walls
        drawwalls(gl2);
        gl2.glPushMatrix();
        gl2.glTranslated(0, 0, -5.6);
        drawwalls(gl2);
        gl2.glPopMatrix();
        gl2.glPushMatrix();
        gl2.glTranslated(0, 3.1, -5.6);
        drawwalls2(gl2);
        gl2.glTranslated(0, 0, 5.6);
        drawwalls2(gl2);
        gl2.glPopMatrix();
        
        // draw side walls
        currObject = ObjectsToDraw.sidewall;
        drawobject(gl2, currObject);
        gl2.glPushMatrix();
        gl2.glTranslated(5.6, 0, 0);
        drawobject(gl2, currObject);
        gl2.glTranslated(0, 3.1, 0);
        drawobject(gl2, currObject);
        gl2.glTranslated(-5.6, 0, 0);
        drawobject(gl2, currObject);
        gl2.glPopMatrix();
        
        // draw side door
        gl2.glPushMatrix();
        textures[4].bind(gl2);
        currObject = ObjectsToDraw.sidedoor;
        drawobject(gl2, currObject);
        gl2.glPopMatrix();

        gl2.glPopMatrix(); 
    }

    public void drawwalls(GL2 gl2) {
        gl2.glPushMatrix();
        textures[7].bind(gl2);
        currObject = ObjectsToDraw.wall1;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.wall2;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.wall3;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.wall4;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.wall5;
        drawobject(gl2, currObject);
        gl2.glPopMatrix();
    }

    public void drawwalls2(GL2 gl2) {
        gl2.glPushMatrix();
        currObject = ObjectsToDraw.wall1;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.wall2;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.wall3;
        drawobject(gl2, currObject);
        currObject = ObjectsToDraw.wall4;
        drawobject(gl2, currObject);
        gl2.glPopMatrix();
    }


    // ----------------------------------------------------------------------------
    // drawobject method (with normal vectors)
    // ----------------------------------------------------------------------------
    public void drawobject(GL2 gl2, ObjectsToDraw currObject){
        gl2.glPushMatrix();
        double Vx, Vy, Vz, Wx, Wy, Wz, Nx, Ny, Nz, x[], y[], z[]; 
        int i, j, vertexNum; 
        int[][] faces = currObject.faces; 
        double[][] vertices = currObject.vertices; 
        for(i = 0; i < faces.length; i++){
            gl2.glBegin(GL2.GL_TRIANGLE_FAN); 
            // get coordinates of 3 vertices 
            x = vertices[faces[i][0]]; 
            y = vertices[faces[i][1]]; 
            z = vertices[faces[i][2]]; 
            // convert to free vectors 
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
            // get normal vector 
            gl2.glNormal3d(Nx, Ny, Nz); 
            for (j = 0; j < faces[i].length; j++){
                vertexNum = faces[i][j]; 
                if (j == 0) {
                    gl2.glTexCoord2d(0,0);
                }
                else if (j == 1){
                    gl2.glTexCoord2d(1,0); 
                }
                else if (j == 2){
                    gl2.glTexCoord2d(0.5,0.5); 
                }
                gl2.glVertex3dv(vertices[vertexNum], 0); 
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
        // ----------------------------------------------------------------------------
        // ----- EDIT HERE ------
        // Change Camera setting to view the scene from different starting angles 
        // ----------------------------------------------------------------------------
        camera.lookAt(1,8,35, 0,0,1, 0,1,0);
        camera.installTrackball(this);
        
        
        // ----------------------------------------------------------------------------
        // Light source: LIGHT0 
        // ----------------------------------------------------------------------------
        gl2.glEnable(GL2.GL_LIGHT0);
        float diffuse[] = { 0.84f, 0.87f, 0.8f, 1.0f };
        float specular[] = { 0.1f, 0.3f, 0.5f, 1.0f };
        float ambient[] = { 0.54f, 0.57f, 0.5f, 1.0f };
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specular, 0);
        gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
  

        // ----------------------------------------------------------------------------
        // Textures
        // ----------------------------------------------------------------------------
        for (int i = 0; i < textureFileNames.length; i++) {
            try {
                URL textureURL;
                textureURL = getClass().getClassLoader().getResource("textures/" + textureFileNames[i]);
                if (textureURL != null) {
                    BufferedImage img = ImageIO.read(textureURL);
                    ImageUtil.flipImageVertically(img);
                    textures[i] = AWTTextureIO.newTexture(GLProfile.getDefault(), img, true);
                    textures[i].setTexParameteri(gl2, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
                    textures[i].setTexParameteri(gl2, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        textures[0].enable(gl2);
    }


    public void dispose(GLAutoDrawable graphics) {
    }
    public void reshape(GLAutoDrawable graphics, int x, int y, int width, int height) {
    }

}