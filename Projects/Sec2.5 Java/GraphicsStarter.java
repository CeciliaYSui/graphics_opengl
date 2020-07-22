import java.awt.*;       
import java.awt.geom.*;
import javax.swing.*;


public class GraphicsStarter extends JPanel {
    public static void main(String[] args) {
        JFrame window;
        window = new JFrame("Java Graphics");  // The parameter shows in the window title bar.
        window.setContentPane( new GraphicsStarter() ); // Show a Graphics starter in the window.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End program when window closes.
        window.pack();  // Set window size based on the preferred sizes of its contents.
        window.setResizable(false); // Don't let user resize window.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( // Center window on screen.
                (screen.width - window.getWidth())/2, 
                (screen.height - window.getHeight())/2 );
        window.setVisible(true); // Open the window, making it visible on the screen.
    }
    
    private float pixelSize;  
    
    public GraphicsStarter() {
        setPreferredSize( new Dimension(800,600) ); // Set size of drawing area, in pixels.
    }
    
    protected void paintComponent(Graphics g) {
    
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight()); 
        applyapplyWindowToViewportTransformation(g2, -5, 5, -5, 5, true);

        g2.setPaint(Color.YELLOW);
        g2.fill( new Ellipse2D.Double(-6,-1,12,2) );
        g2.setPaint(Color.BLUE);
        g2.setStroke( new BasicStroke(5*pixelSize) );
        g2.draw( new Line2D.Double( -5, -5, 5, 5) );
        g2.draw( new Line2D.Double( -5, 5, 5, -5) );
        
        Path2D poly = new Path2D.Double();
        poly.moveTo(3,0);
        for (int i = 1; i < 6; i++) {
            double angle = (2*Math.PI/6) * i;
            poly.lineTo( 3*Math.cos(angle), 3*Math.sin(angle) );
        }
        poly.closePath();
        
        g2.setPaint( new Color(255, 0, 0, 100) ); // red with alpha-transparency
        g2.fill(poly);
        g2.setPaint( Color.RED );
        g2.draw(poly);
    }
    
    private void applyapplyWindowToViewportTransformation(Graphics2D g2,
            double left, double right, double bottom, double top, 
            boolean preserveAspect) {
        int width = getWidth();   // The width of this drawing area, in pixels.
        int height = getHeight(); // The height of this drawing area, in pixels.
        if (preserveAspect) {
            // Adjust the limits to match the aspect ratio of the drawing area.
            double displayAspect = Math.abs((double)height / width);
            double requestedAspect = Math.abs(( bottom-top ) / ( right-left ));
            if (displayAspect > requestedAspect) {
                // Expand the viewport vertically.
                double excess = (bottom-top) * (displayAspect/requestedAspect - 1);
                bottom += excess/2;
                top -= excess/2;
            }
            else if (displayAspect < requestedAspect) {
                // Expand the viewport vertically.
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
