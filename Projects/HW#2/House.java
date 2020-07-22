//-----------------------------------------------------------------------------------------
// Author -------------- Cecilia Y. Sui
// Course -------------- Computer Graphics 
// Instructor ---------- Dr. Crawley
// Date of Submission -- September 19, 2019
// Assignment ---------- Use Java Graphics2D to draw a house with roof, door, windows
// --------------------- trees or shrub, sun or moon. 
//-----------------------------------------------------------------------------------------

//-----------------------------------------------------------------------------------------
// Import 
//-----------------------------------------------------------------------------------------
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;


//-----------------------------------------------------------------------------------------
// House Class extends JPanel
//-----------------------------------------------------------------------------------------
public class House extends JPanel{
    public static void main (String[] args) throws IOException{
        JFrame window; 
        window = new JFrame("The Happy House");
        window.setContentPane(new House()); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        window.pack(); 
        window.setResizable(false); 
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(
            (screen.width - window.getWidth())/2,
            (screen.height - window.getHeight())/2);
        window.setVisible(true); 
    }

    private float pixelSize;

    //-----------------------------------------------------------------------------------------
    // Constructor 
    //-----------------------------------------------------------------------------------------
    public House(){
        setPreferredSize( new Dimension(1200,700));
    }

    //-----------------------------------------------------------------------------------------
    // paintComponent Function 
    //-----------------------------------------------------------------------------------------
    protected void paintComponent(Graphics g) {
    
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight()); 
        applyapplyWindowToViewportTransformation(g2, -100, 100, -100, 100, true);

        //-----------------------------------------------------------------------------------------
        // Draw the chimney 
        //-----------------------------------------------------------------------------------------
        Rectangle2D chim = new Rectangle2D.Double(-120,25,12,30);
        g2.setPaint(new Color(128,64,0,255));
        g2.fill(chim);
        g2.setStroke(new BasicStroke(4*pixelSize));
        g2.setPaint(new Color(190,188,193));
        g2.draw(chim);

        //-----------------------------------------------------------------------------------------
        // Draw the smoke from chimney
        //-----------------------------------------------------------------------------------------
        AffineTransform savedChim = g2.getTransform(); 
        Ellipse2D smoke = new Ellipse2D.Double(-120,58,10,8); 
        g2.setPaint(new Color(190,188,193)); 
        g2.fill(smoke);  
        g2.scale(1.1,1.1); 
        g2.translate(-1,1); 
        g2.fill(smoke); 
        g2.setTransform(savedChim); 
        g2.scale(1.5,1.5);
        g2.translate(19,-9);  
        g2.fill(smoke); 
        g2.setTransform(savedChim); 

        //-----------------------------------------------------------------------------------------
        // Draw Triangle House Roof 
        //-----------------------------------------------------------------------------------------
        Path2D p = new Path2D.Double(); 
        p.moveTo(-150,0);
        p.lineTo(-60,80);
        p.lineTo(30,0);
        p.closePath(); 
        g2.setPaint(new Color(190,188,193)); 
        g2.fill(p);

        //-----------------------------------------------------------------------------------------
        // Print the image "Happy" on house roof 
        //-----------------------------------------------------------------------------------------
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Happy.png"));
        } 
        catch (IOException e) {
        }
        g2.drawImage(img, -80,10,48,44,null); 

        //-----------------------------------------------------------------------------------------
        // Draw the House body rectangle 
        //-----------------------------------------------------------------------------------------
        g2.setPaint(new Color(172,229,238));
        g2.fill( new Rectangle2D.Double(-150,-80,180,80) );

        //-----------------------------------------------------------------------------------------
        // Draw the door in the middle
        //-----------------------------------------------------------------------------------------
        g2.setPaint(new Color(33,46,83,200)); 
        g2.fill(new Rectangle2D.Double(-80,-80,40,35));
        // Door knobs 
        g2.setPaint(Color.pink);
        g2.fill(new Ellipse2D.Double(-58,-65,2,2));
        g2.fill(new Ellipse2D.Double(-64,-65,2,2));
        // Door Line
        g2.setStroke(new BasicStroke(2*pixelSize));
        g2.draw(new Line2D.Double(-60,-80,-60,-45));
        // Filled arc on top 
        Path2D p2 = new Path2D.Double(); 
        p2.moveTo(-80,-45);
        p2.quadTo(-60,-20,-40,-45);
        p2.closePath(); 
        g2.setPaint(Color.pink);
        g2.fill(p2);

        //-----------------------------------------------------------------------------------------
        // Draw the concrete floor
        //-----------------------------------------------------------------------------------------
        g2.setPaint(new Color(190,188,193)); 
        g2.setStroke(new BasicStroke(20*pixelSize)); 
        g2.draw(new Line2D.Double(-155,-82,35,-82)); 

        //-----------------------------------------------------------------------------------------
        // Draw the windows
        //-----------------------------------------------------------------------------------------
        Rectangle2D wind = new Rectangle2D.Double(-135,-45,30,30);
        g2.setPaint(new Color(33,46,83,200));
        g2.fill(wind);  
        AffineTransform savedWind = g2.getTransform(); 
        g2.translate(120,0); 
        g2.fill(wind); 
        g2.setTransform(savedWind); 
        g2.setPaint(Color.pink); 
        g2.setStroke(new BasicStroke(4*pixelSize)); 
        g2.draw(new Line2D.Double(-120,-45,-120,-15)); 
        g2.draw(new Line2D.Double(-135,-30,-105,-30)); 
        g2.draw(new Line2D.Double(0,-45,0,-15)); 
        g2.draw(new Line2D.Double(-15,-30,15,-30));
        g2.setStroke(new BasicStroke(10*pixelSize)); 
        g2.draw(new Line2D.Double(-135,-45,-105,-45));  
        g2.draw(new Line2D.Double(-15,-45,15,-45)); 

        //-----------------------------------------------------------------------------------------
        // Draw the sun 
        //-----------------------------------------------------------------------------------------
        Ellipse2D sun = new Ellipse2D.Double(100,40,32,32);
        g2.setPaint(new Color(255,247,0,220)); 
        g2.fill(sun); 
        Rectangle2D light = new Rectangle2D.Double(116,56,11,3.5);  
        g2.setStroke( new BasicStroke(2*pixelSize) );
        for (int i = 0; i < 10; i++) {
            AffineTransform savedTransform = g2.getTransform();  
            double angle = (2*Math.PI/10) * i;
            g2.rotate(angle, 116,56); 
            g2.translate(20,0); 
            g2.setPaint( new Color(255,255,51) ); 
            g2.fill(light); 
            g2.setPaint(Color.yellow);
            g2.draw(light);
            g2.setTransform(savedTransform); 
        }

        //-----------------------------------------------------------------------------------------
        // Draw the Trees 
        //-----------------------------------------------------------------------------------------
        Rectangle2D trunk = new Rectangle2D.Double(57,-80,6,68); 
        g2.setPaint(new Color(101,67,33)); 
        g2.fill(trunk); 
        Ellipse2D tree = new Ellipse2D.Double(40,-20,20,15); 
        g2.setPaint(new Color(144,151,0)); 
        for (int i = 0; i < 8; i++){
            AffineTransform savedTree = g2.getTransform(); 
            double angle2 = (2*Math.PI/8) * i; 
            g2.rotate(angle2, 60,-15); 
            g2.fill(tree); 
            g2.setTransform(savedTree); 
        }
        Rectangle2D trunk2 = new Rectangle2D.Double(103,-80,4,51); 
        g2.setPaint(new Color(101,67,33)); 
        g2.fill(trunk2); 
        Ellipse2D tree2 = new Ellipse2D.Double(90,-40,15,10.5); 
        g2.setPaint(new Color(68,75,9)); 
        for (int i = 0; i < 8; i++){
            AffineTransform savedTree = g2.getTransform(); 
            double angle2 = (2*Math.PI/8) * i; 
            g2.rotate(angle2, 105,-35); 
            g2.translate(0,0); 
            g2.fill(tree2); 
            g2.setTransform(savedTree); 
        }

        //-----------------------------------------------------------------------------------------
        // Print Welcome Note 
        //-----------------------------------------------------------------------------------------
        g.setColor(new Color(140,190,214)); 
        g.setFont(new Font("Courier", Font.PLAIN, 25));
        g.drawString("Welcome to The Happy House!", 195, 50);

    }

    //-----------------------------------------------------------------------------------------
    // applyapplyWindowToViewportTransformation 
    //-----------------------------------------------------------------------------------------
    private void applyapplyWindowToViewportTransformation(Graphics2D g2,
            double left, double right, double bottom, double top, 
            boolean preserveAspect) {
        int width = getWidth();   
        int height = getHeight(); 
        if (preserveAspect) {
            double displayAspect = Math.abs((double)height / width);
            double requestedAspect = Math.abs(( bottom-top ) / ( right-left ));
            if (displayAspect > requestedAspect) {
                double excess = (bottom-top) * (displayAspect/requestedAspect - 1);
                bottom += excess/2;
                top -= excess/2;
            }
            else if (displayAspect < requestedAspect) {
                double excess = (right-left) * (requestedAspect/displayAspect - 1);
                right += excess/2;
                left -= excess/2;
            }
        }
        g2.scale( width / (right-left), height / (bottom-top) );
        g2.translate( -left, -top );
        double pixelWidth = Math.abs(( right - left ) / width);
        double pixelHeight = Math.abs(( bottom - top ) / height);
        pixelSize = (float)Math.max(pixelWidth,pixelHeight);
    }
}