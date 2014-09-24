package mason;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import mason.rotterdam.CheckinMasonAction;
import mason.rotterdam.GoToCheckinDeskMasonAction;
import mason.rotterdam.GoToNearestPillarMasonAction;
import mason.rotterdam.GoToToiletMasonAction;
import mason.rotterdam.WaitForFriendMasonAction;
import mason.rotterdam.WaitInQueueMasonAction;
import protobuf.ActionMessage;
import protobuf.ActionMessage.Action;
import protobuf.ObservationMessage.Observation;
import protobuf.PedestrianSim.ObservationMessage;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.field.continuous.Continuous2D;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.Inspector;
import sim.portrayal.LocationWrapper;
import sim.portrayal.Oriented2D;
import sim.portrayal.SimplePortrayal2D;
import sim.util.Double2D;
import situations.IPedestrianBrain;
import situations.RandomBrain;

public class PedestrianAgent extends SimplePortrayal2D implements Steppable,
		Oriented2D {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7934765928667255066L;
	private static double DEFAULT_ORIENTATION = 0;
	private static double DEFAULT_SPEED = 2;
	private static int DEFAULT_PORT = 3333;
	private static double DIAMETER = 1;
	private static final IMasonAction DEFAULT_ACTION = new IdleMasonAction();
	private static final String ACTION_LOG_FILE_PATH = "actionlog.csv";

	private double orientation;
	private IPedestrianBrain brain;
	private Double2D currentLocation;
	private Socket pedestrianSimSocket;
	private Map<String, IMasonAction> actions;
	private SimState state;
	private IMasonAction currentAction;
	private Map<String, Color> actionColors;
	private Stoppable stoppable;

	// Only for debugging

	public PedestrianAgent(double orientation, SimState state) {
		this.state = state;
		System.out.println("Initializing pedestrian agent");
		this.orientation = orientation;
		brain = new RandomBrain();
		currentAction = DEFAULT_ACTION;
		listActions();

		try {
			pedestrianSimSocket = new Socket(InetAddress.getLocalHost(),
					DEFAULT_PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void listActions() {
		actions = new HashMap<String, IMasonAction>();
		actions.put("walkInCircle",
				(IMasonAction) (new CircleWalkMasonAction()));
		actions.put("wander", new WanderMasonAction());
		actions.put("standstill", new StandStillMasonAction());
		actions.put("standstill2", new DifferentStandStillMasonAction());
		actions.put("gotogoal", new GoToGoalMasonAction());
		actions.put("goToheckinDesk", new GoToCheckinDeskMasonAction());
		actions.put("askQuestion", new CheckinMasonAction());
		actions.put("waitInQueue", new WaitInQueueMasonAction());
		actions.put("goToToilet", new GoToToiletMasonAction());
		actions.put("waitForFriend", new WaitForFriendMasonAction());
		actions.put("waitForFriend2", new WaitForFriendMasonAction());
		actions.put("goToNearestPillar", new GoToNearestPillarMasonAction());
		actions.put("leanAgainstPillar", new StandStillMasonAction());
		actions.put("slowWander", new SlowWanderMasonAction());
		actions.put("fastWander", new FastWanderMasonAction());

	}

	public PedestrianAgent(SimState state) {
		this(DEFAULT_ORIENTATION, state);
	}

	@Override
	public void step(SimState state) {
		IMasonAction masonAction = null;
		setState(state);
		ActionMessage.Action action = null;
		PedestrianSimState sim = (PedestrianSimState) state;
		// if(currentLocation == null){
		currentLocation = sim.agents.getObjectLocation(this);
		// }
		System.out.println("Field dimensions: " + sim.agents.getDimensions());
		System.out.println("Current location: " + currentLocation);

		if (getCurrentAction() == null || getCurrentAction().isFinished()) {
			action = retrieveNewAction();

			if (action.getAction().equals("respawn")) {
				// respawn((PedestrianSimState) state);
			}

			masonAction = getMasonAction(action);
		} else {
			syncSituationsClock();
			masonAction = getCurrentAction();
		}
		if (masonAction == null) {
			System.out.println("MasonAction is null");
		}
		executeAction(masonAction, state, sim.agents);
		log(masonAction.toString());

	}

	private void respawn(PedestrianSimState state) {
		state.agents.remove(this);
		stoppable.stop();

		state.spawnAgent();
	}

	private Action retrieveNewAction() {
		Action action = null;
		try {
			// pedestrianSimSocket.setReuseAddress(true);
			OutputStream out = pedestrianSimSocket.getOutputStream();
			InputStream in = pedestrianSimSocket.getInputStream();
			Observation observation = buildObservation(
					currentLocation.getX(), currentLocation.getY(),
					this.toString(), true);
			// Send observation to server
			// System.out.println("Writing observation to server...");
			observation.writeDelimitedTo(out);
			// observation.writeTo(out);
			// endOutput(out);
			// out.close();
			// System.out.println("Done writing observation to server");

			// Read response from server
			action = ActionMessage.Action.parseDelimitedFrom(in);

			// pedestrianSimSocket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return action;
	}

	private void syncSituationsClock() {
		try {
			// pedestrianSimSocket.setReuseAddress(true);
			OutputStream out = pedestrianSimSocket.getOutputStream();
			InputStream in = pedestrianSimSocket.getInputStream();
			Observation observation = buildObservation(currentLocation.getX(), currentLocation.getY(),
					this.toString(), false);
			// Send observation to server
			// System.out.println("Writing observation to server...");
			observation.writeDelimitedTo(out);
			// observation.writeTo(out);
			// endOutput(out);
			// out.close();
			// System.out.println("Done writing observation to server");

			// Read response from server
			Action action = ActionMessage.Action.parseDelimitedFrom(in);
			//setCurrentAction(getMasonAction(action));
			// pedestrianSimSocket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// private void endOutput(OutputStream out) throws IOException {
	// out.write("\r\n".getBytes());
	// out.flush();
	// }

	private Observation buildObservation(double x, double y, String id, boolean needNewAction) {
		Observation.Builder observation = Observation
				.newBuilder();
		observation.setLocationX(x);
		observation.setLocationY(y);
		observation.setPedestrianId(id);
		observation.setNeedNewAction(needNewAction);

		return observation.build();
	}

	private void executeAction(IMasonAction action, SimState state,
			Continuous2D agents) {
		System.out.println("Executing action: " + action);

		// IMasonAction iMasonAction = actions.get(action.getAction());
		action.act((PedestrianSimState) state, this);
		setCurrentAction(action);

	}

	private IMasonAction getMasonAction(Action action) {
		IMasonAction masonAction = actions.get(action.getAction());
		if (masonAction == null) {
			masonAction = DEFAULT_ACTION;
		}
		return masonAction;

	}

	@Override
	public double orientation2D() {
		return orientation;
	}

	public void setOrientation2D(double orientation) {
		this.orientation = orientation;
	}

	private Double2D getRelativeLocation(double rotation, double speed) {
		return new Double2D(Math.cos(rotation) * speed, Math.sin(rotation)
				* speed);
	}

	public Double2D getLocation() {
		return currentLocation;
	}

	private void setState(SimState state) {
		this.state = state;
	}

	private SimState getState() {
		return state;
	}

	public void setLocation(Double2D newLocation) {
		currentLocation = newLocation;
		((PedestrianSimState) state).agents
				.setObjectLocation(this, newLocation);
	}

	public final void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
		double diamx = info.draw.width * DIAMETER;
		double diamy = info.draw.height * DIAMETER;

		graphics.setColor(Color.BLACK);
		graphics.drawString(currentAction.toString(), (int) info.draw.x,
				(int) info.draw.y);
		graphics.setColor(getColor(currentAction));
		graphics.fillOval((int) (info.draw.x - diamx / 2),
				(int) (info.draw.y - diamy / 2), (int) (diamx), (int) (diamy));
		// graphics.setColor( goodMarkColor );
		// graphics.fillRect((int)(info.draw.x-diamx/3),(int)(info.draw.y-diamy/16),(int)(diamx/1.5),(int)(diamy/8));
		// graphics.fillRect((int)(info.draw.x-diamx/16),(int)(info.draw.y-diamy/3),(int)(diamx/8),(int)(diamy/1.5));
		//
		// graphics.setColor(currentAction.getColor());

	}

	private void log(String action) {
		// Check whether the toString method used can distinguish between
		// instances.
		// TODO:For now I have to clear the log by hand every time I run the
		// simulation. I probably have to fix this in the future.
		String logLine = this.toString() + ", " + action + "\n";

		try {
			FileWriter log = new FileWriter(ACTION_LOG_FILE_PATH, true);
			log.write(logLine);
			log.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Generates a Color instance based on the type (subclass) of action, so the
	 * UI can use this to color the agents dependent on their action.
	 * 
	 * @param object
	 *            The object for which a color should be generated.
	 * 
	 * @return A color based on which subclass the action is.
	 */
	public Color getColor(Object object) {

		String actionName = object.getClass().getName();
		Color color = new Color(actionName.hashCode());

		return color;
	}

	private void setCurrentAction(IMasonAction currentAction) {
		this.currentAction = currentAction;
	}

	private IMasonAction getCurrentAction() {
		return currentAction;
	}

	public Stoppable getStoppable() {
		return stoppable;
	}

	public void setStoppable(Stoppable stoppable) {
		this.stoppable = stoppable;
	}

	@Override
	public Inspector getInspector(LocationWrapper wrapper, GUIState state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(LocationWrapper wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStatus(LocationWrapper wrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setSelected(LocationWrapper wrapper, boolean selected) {
		// TODO Auto-generated method stub
		return false;
	}

}
