/* --------------------------------------------------------------------------
 * Author -------------- Cecilia Y. Sui 
 * Assignment ---------- Exam #1 Part II
 * Course -------------- Computer Graphics
 * Instructor ---------- Dr. Crawley 
 * Date of Submission -- October 5, 2019
 * Language Used ------- Java & OpenGL
 * Class Imported ------ Camera from Camera.java
 * Description --------- Use JOGL to draw a hexagon bipyramid with 
 * --------------------- an indexed face set (IFS)
-------------------------------------------------------------------------- */

// ----------------------------------------------------------------------------
// Imports 
// ----------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

// ----------------------------------------------------------------------------
// Bipyramid class 
// ----------------------------------------------------------------------------
public class Bipyramid extends GLJPanel implements GLEventListener {
    public static void main(String[] args) {
        JFrame window = new JFrame("Hexagonal Bipyramid -- Rotate With Mouse! ");
        Bipyramid panel = new Bipyramid();
        window.setContentPane(panel);
        window.setJMenuBar(panel.createMenuBar());
        window.pack();
        window.setLocation(50,50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.requestFocusInWindow();
    }

    // ----------------------------------------------------------------------------
    // Constructor 
    // ----------------------------------------------------------------------------
    public Bipyramid() {
        setPreferredSize( new Dimension(800,800) );
        addGLEventListener(this);
    }

    // ----------------------------------------------------------------------------
    // Declare private variables
    // ----------------------------------------------------------------------------
    private Camera camera;
    private JRadioButtonMenuItem orthographic, drawEdges, drawFaces, drawBoth, coloredFaces;


    // ----------------------------------------------------------------------------
    // display method 
    // ----------------------------------------------------------------------------
    public void display(GLAutoDrawable drawable) {    
        GL2 gl2 = drawable.getGL().getGL2();
        gl2.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );

        // ----------------------------------------------------------------------------
        // Set vertices, faces, colors for hexagonal bipyramid 
        // ----------------------------------------------------------------------------
        double[][] vertexList = {{0,0,4},{0,0,-4},{-2,0,0},{-1,1.73,0},{1,1.73,0},{2,0,0},{1,-1.73,0},{-1,-1.73,0}};
        int[][] faceList = {{0,2,3},{0,3,4},{0,4,5},{0,5,6},{0,6,7},{0,7,2},{1,2,3},{1,3,4},{1,4,5},{1,5,6},{1,6,7},{1,7,2}};
        double[][] faceColors = {{0.93,0.44,0.39},{0.36,0.68,0.89},{0.96,0.82,0.25},
                                {0.1,0.74,0.61},{0.63,0.82,0.91},{0.95,0.61,0.07},
                                {0.96,0.82,0.25},{0.1,0.74,0.61},{0.63,0.82,0.91},
                                {0.95,0.61,0.07},{0.93,0.44,0.39},{0.36,0.68,0.89}};

        // ----------------------------------------------------------------------------
        // Control Render Options: faces | edges | both
        // ----------------------------------------------------------------------------
        camera.apply(gl2);
        gl2.glPushMatrix();
        boolean colored = coloredFaces.isSelected();
        int i,j;
        if (drawFaces.isSelected() || drawBoth.isSelected()) {
            if (drawBoth.isSelected()) {
                gl2.glEnable(GL2.GL_POLYGON_OFFSET_FILL);
            }
            for (i = 0; i < faceList.length; i++) {
                if (colored) {
                    gl2.glColor3dv(faceColors[i], 0 );
                }
                gl2.glBegin(GL2.GL_TRIANGLE_FAN);
                for (j = 0; j < faceList[i].length; j++) {
                    int vertexNum = faceList[i][j];
                    gl2.glVertex3dv( vertexList[vertexNum], 0 );
                }
                gl2.glEnd();
            }
            gl2.glDisable(GL2.GL_POLYGON_OFFSET_FILL); 
        }
        if (drawEdges.isSelected() || drawBoth.isSelected()) {
            if (drawBoth.isSelected()) {
                gl2.glColor3f(0,0,0);
            }
            else {
                gl2.glColor3f(1,1,1);
            }
            for (i = 0; i < faceList.length; i++) {
                gl2.glBegin(GL2.GL_LINE_LOOP);
                for (j = 0; j < faceList[i].length; j++) {
                    int vertexNum = faceList[i][j];
                    gl2.glVertex3dv( vertexList[vertexNum], 0 );
                }
                gl2.glEnd();
            }
        }
        gl2.glPopMatrix();
    } 

    // ----------------------------------------------------------------------------
    // init method 
    // ----------------------------------------------------------------------------
    public void init(GLAutoDrawable graphics) {
        GL2 gl2 = graphics.getGL().getGL2();
        gl2.glEnable(GL2.GL_DEPTH_TEST);
        gl2.glLineWidth(2);
        gl2.glPolygonOffset(1,2);
        camera = new Camera(); 
        // Camera class is used exactly as the author created with no modification.
        // It is downloaded from the website of the textbook. 
        camera.lookAt(4,15,6, 0,0,0, 0,1,0);
        camera.installTrackball(this);
    }

    public void dispose(GLAutoDrawable graphics) {
    }
    public void reshape(GLAutoDrawable graphics, int x, int y, int width, int height) {
    }

    // ----------------------------------------------------------------------------
    // create the Menu Bar for Render Options 
    // ----------------------------------------------------------------------------
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu render = new JMenu("Render Options");
        menuBar.add(render);
        
        // ----------------------------------------------------------------------------
        // repaint() method for repainting 
        // ----------------------------------------------------------------------------
        ActionListener repainter = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                repaint();
            }
        };

        // ----------------------------------------------------------------------------
        // Menu Botton Control: 
        // ----------------------------------------------------------------------------
        JRadioButtonMenuItem[] items;
        items = createRadioMenuGroup(new String[] {"Colored Faces", "WhiteFaces"}, render, repainter);
        coloredFaces = items[0];
        coloredFaces.setSelected(true);
        render.addSeparator();
        items = createRadioMenuGroup(new String[] {"Draw Faces Only", "Draw Edges Only", "Draw Both"}, 
                                                            render, repainter);
        drawFaces = items[0];
        drawEdges = items[1];
        drawBoth = items[2];
        drawBoth.setSelected(true);
        render.addSeparator();
        items = createRadioMenuGroup(new String[] {"Perspective Projetion", "Orthographics Projection"}, render, repainter);
        orthographic = items[1];
        items[0].setSelected(true);
        return menuBar;
    }

    // ----------------------------------------------------------------------------
    // create Radio Menu Group 
    // ----------------------------------------------------------------------------
    private JRadioButtonMenuItem[] createRadioMenuGroup(String[] itemNames, JMenu menu, ActionListener listener) {
        JRadioButtonMenuItem[] items = new JRadioButtonMenuItem[itemNames.length];
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < itemNames.length; i++) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(itemNames[i]);
            group.add(item);
            items[i] = item;
            menu.add(item);
            if (listener != null) {
                item.addActionListener(listener);
            }
        }
        return items;
    }
}