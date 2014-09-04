package mason;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.Oriented2D;
import sim.portrayal.SimplePortrayal2D;
import sim.util.Double2D;

import com.csvreader.CsvReader;

public class TestDataAgent extends SimplePortrayal2D implements Steppable, Oriented2D {

    /**
     * 
     */
    private static final long serialVersionUID = -43334062653898673L;
    CsvReader trackReader;
    double X_OFFSET = -4.975;
    double Y_OFFSET = 9.075;
    private static double DIAMETER = 1;
    public double UNIVERSAL_START_TIME = 734422.5697338773;
    public double TIME_STEP = 0.0000012616;
    private double agentStartingTime;
    private double currentTime;

    public TestDataAgent(String file) {

        try {
            this.trackReader = new CsvReader(file);
            this.trackReader.readRecord();
            currentTime = UNIVERSAL_START_TIME;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public double orientation2D() {
        return 0;
    }

    @Override
    public void step(SimState state) {
        currentTime += TIME_STEP;
        System.out.println("Time: " + currentTime + " agentStartingTime: " + agentStartingTime);
        PedestrianSimState sim = (PedestrianSimState) state;
        if (agentStartingTime == 0 || currentTime >= agentStartingTime) {
            System.out.println("Bla");
            try {
                trackReader.readRecord();
                double xLocation = Double.parseDouble(trackReader.get(0));
                double yLocation = Double.parseDouble(trackReader.get(1));
                String timeString = trackReader.get(2);
                double time = Double.parseDouble(timeString);

                Double2D location;
                if (agentStartingTime == 0) {
                    agentStartingTime = time;
                    location = new Double2D(0, 0);
                } else {

                    xLocation -= X_OFFSET;
                    yLocation -= Y_OFFSET;
                    yLocation *= -1;

                    System.out.println("Agent currently at: " + xLocation + ", " + yLocation);

                    location = new Double2D(xLocation, yLocation);
                }

                sim.agents.setObjectLocation(this, location);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NumberFormatException e) {
                sim.agents.setObjectLocation(this, new Double2D());
            }
        }
    }

    public final void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
        double diamx = info.draw.width * DIAMETER;
        double diamy = info.draw.height * DIAMETER;

        graphics.setColor(Color.RED);
        graphics.fillOval((int) (info.draw.x - diamx / 2), (int) (info.draw.y - diamy / 2),
                (int) (diamx), (int) (diamy));
        // graphics.setColor( goodMarkColor );
        // graphics.fillRect((int)(info.draw.x-diamx/3),(int)(info.draw.y-diamy/16),(int)(diamx/1.5),(int)(diamy/8));
        // graphics.fillRect((int)(info.draw.x-diamx/16),(int)(info.draw.y-diamy/3),(int)(diamx/8),(int)(diamy/1.5));
        //
        // graphics.setColor(currentAction.getColor());

    }

}
