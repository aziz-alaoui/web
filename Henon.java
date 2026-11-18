import java.awt.*;
import java.applet.*;
public class Henon extends java.applet.Applet {
        
final int MAX = 2000;                           // number of dots in each orbit //
        int x;                                                  // window pixel: 0 to 400 //
        int y;                                                  // window pixel: 0 to 400 //
        int curr = 0;                                   // orbit number //
        int NUM = 6;                                    // number of orbits //
        int temp = 0;
        double co = 0.22;                               // cos(alpha) //
        double si = 0.9754998719;               // sin(alpha) //
        double ptemp;
        double qtemp;
        double pptemp;
        double p[] = new double[MAX*NUM];
        double q[] = new double[MAX*NUM];
        
        public void init() {            
                setBackground(Color.black);
        }
        
        public boolean handleEvent(Event evt) {
                switch (evt.id) {
                        case Event.MOUSE_DOWN:
                                if (curr == NUM) curr = 0;
                                x = evt.x;
                                y = evt.y;
                                p[curr*MAX] = (double) (x/100.0 - 2.0);         // -2 to 2 //
                                q[curr*MAX] = (double) (-y/100.0 + 2.0);        // -2 to 2 //
                                adddots();
                                ++curr;
                                repaint();
                                return true;
                
                        default:
                                return false;
                }
        }

        void adddots()   {
                for (int i = 0; i < MAX-1 ; i++) {
                        temp = curr*MAX + i;
                        ptemp = p[temp];
                        qtemp = q[temp];
                        pptemp = ptemp*ptemp;
                        p[temp+1] = co*ptemp - si*qtemp + si*pptemp;
                        q[temp+1] = si*ptemp + co*qtemp - co*pptemp;
                }
        }
        
        public void paint(Graphics g) {
                g.drawRect(0, 0, size().width - 1, size().height - 1);
                // draw existing orbits
                for (int j = 0; j < curr; j++) {
                        if (j == 0) g.setColor(Color.red);
                        if (j == 1) g.setColor(Color.green);
                        if (j == 2) g.setColor(Color.blue);
                        if (j == 3) g.setColor(Color.magenta);
                        if (j == 4) g.setColor(Color.cyan);
                        if (j == 5) g.setColor(Color.orange);
                        temp = j*MAX;
                        for (int i = 0; i < MAX; i++) {
                                x = (int) (100.0*(p[temp+i] + 2.0));
                                y = (int) (100.0*(2.0 - q[temp+i]));
                                g.drawLine (x,y,x,y);
                        }
                }
                //  draw current orbit
//              if (curr == 1) g.setColor(Color.red);
//              if (curr == 2) g.setColor(Color.green);
//              if (curr == 3) g.setColor(Color.blue);
//              if (curr == 4) g.setColor(Color.magenta);
//              if (curr == 5) g.setColor(Color.cyan);
//              if (curr == 6) g.setColor(Color.orange);
//              temp = (curr-1)*MAX;
//              for (int i = 0; i < MAX; i++) {
//                      x = (int) (100.0*(p[temp+i] + 2.0));
//                      y = (int) (100.0*(2.0 - q[temp+i]));
//                      g.drawLine (x,y,x,y);
//              }
        }
                        
}
