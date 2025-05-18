import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Extend this class to handle some painting issues in Java. <br>
 */
public class FixFrame extends JFrame 
{

   public FixFrame(int width, int height, String title)
   {  
      super (title);
      center(width, height);

      addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent event) { System.exit(0);} });
      FixRepaint fix = new FixRepaint();
      addComponentListener(fix);
   }

   private class FixRepaint implements ComponentListener
   {
      public void componentResized(ComponentEvent ce)
      {
         repaint();
      }
      public void componentHidden(ComponentEvent ce)
      {}
      public void componentMoved(ComponentEvent ce)
      {}
      public void componentShown(ComponentEvent ce)
      { 
         repaint();
      }
   }

   private void center(int width, int height)
   {
      setSize(width, height);

      //center the window
      Dimension screenSize = getToolkit().getScreenSize();
      int screenWidth = screenSize.width;
      int screenHeight = screenSize.height;
      setLocation(screenWidth/2 - width/2, screenHeight/2 - height/2);
   }
}


