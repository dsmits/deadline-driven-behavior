package mason;

import java.io.File;

import sim.engine.SimState;
import sim.engine.Stoppable;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;

public class PedestrianSimState extends SimState {
    /**
	 * 
	 */
    private static final long serialVersionUID = 8175839357354808939L;
    public static final Double2D STARTING_LOCATION = new Double2D(50, 0);
    public Continuous2D agents;
    public Continuous2D pillars;
    public double width;
    public double height;
    public static final double DEFAULT_WIDTH = 500;
    public static final double DEFAULT_HEIGHT = 500;
    public static final int NUMBER_OF_PEDESTRIANS = 5;
    public boolean USE_SAMPLE_DATA = false;
    public String SAMPLE_DIR = "/home/djura/vakken/afstudeerstage/matlab/converted tracks";
    private int maxSteps;

    public PedestrianSimState(long seed) {
        this(seed, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public PedestrianSimState(long seed, double width, double height) {
        super(seed);
        this.width = width;
        this.height = height;
        this.maxSteps = maxSteps;
    }

    public void start() {
        super.start();

        // set up the agents field
        // TODO: Retrieve dimensions from situations.xml
        agents = new Continuous2D(width, width, height);
        pillars = new Continuous2D(width, width, height);
        createPillars();

        if (USE_SAMPLE_DATA) {
            File sampleDirectory = new File(SAMPLE_DIR);
            File csvFile;
            for (int i = 1; i < NUMBER_OF_PEDESTRIANS + 1; i++) {
                csvFile = new File(sampleDirectory, String.valueOf(i) + ".csv");
                TestDataAgent agent = new TestDataAgent(csvFile.getAbsolutePath());
                agents.setObjectLocation(agent, new Double2D());
                schedule.scheduleRepeating(agent);
            }
        } else {
            // make a bunch of agents and schedule 'em
            for (int x = 0; x < NUMBER_OF_PEDESTRIANS; x++) {
                spawnAgent();
            }
        }

    }

    public void spawnAgent() {
        PedestrianAgent agent = new PedestrianAgent(this);

        double xStart = 27;// random.nextDouble() * width;
        double yStart = 25;// random.nextDouble() * height;
        Double2D startLocation = new Double2D(xStart, yStart);
        // agents.setObjectLocation(agent, startLocation);
        agent.setLocation(startLocation);
        Stoppable stoppable = schedule.scheduleRepeating(agent);
        agent.setStoppable(stoppable);
    }

    private void createPillars() {
        // TODO: Figure out pillar locations
        pillars.setObjectLocation(new Pillar(), new Double2D(10.0820, 9.6014));
        //pillars.setObjectLocation(new Pillar(), new Double2D(20.0, 9.6014));
    }

    public static void main(String[] args) {
        doLoop(PedestrianSimState.class, args);
        System.exit(0);
    }

    public class Pillar {
        // Just an object to fill the pillars grid with.
    }

}
