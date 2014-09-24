package situations;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import needs.NeedsManager;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import protobuf.ActionMessage.Action;
import time.IClock;
import time.StepClock;

public class PedestrianSim implements Runnable {
    // TODO: Make port configurable
    public static final int DEFAULT_PORT = 3333;
    public static final int MAX_CONNECTIONS = 10;
    public static final String DEFAULT_CONFIG_FILE = "situations.xml";
    private boolean killed = false;

    private SituationManager situationManager;
    private PedestrianManager pedestrianManager;
    private NeedsManager needsManager;
    // private PetriNetManager petriNetManager;
    private ServerSocket listener;
    private Executor threadPool;
    // TODO: Find a better solution for context
    private Map<String, Object> context;

    private IClock clock;

    public PedestrianSim() {
        this(DEFAULT_CONFIG_FILE);
    }

    public PedestrianSim(String configFile) {
    	killed = false;
        clock = new StepClock();
        Document config = getConfigDocument(configFile);
        // situationManager = new SituationManager(config);
        pedestrianManager = new PedestrianManager(config, clock);
        needsManager = new NeedsManager(config);

        // TODO: Make port configurable
        try {
            listener = new ServerSocket(DEFAULT_PORT, 0, InetAddress.getLocalHost());
            listener.setReuseAddress(true);
            threadPool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private Document getConfigDocument(String configFile) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document config = null;
        try {
            config = dbFactory.newDocumentBuilder().parse(configFile);
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return config;
    }

    public void start() {
        System.out.println("Starting PedestrianSim server");
        Socket server;

        try {
            while (!isKilled()) {
                System.out.println("Waiting for pedestrian...");
                System.out.println("Server inetaddress: " + listener.getInetAddress());
                server = listener.accept();
                System.out.println("Connected with pedestrian");
                threadPool.execute(new SimulationConnectionHandler(server, pedestrianManager));
                System.out.println("Thread pool size: "
                        + ((ThreadPoolExecutor) threadPool).getPoolSize());

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public boolean isKilled(){
    	return killed;
    }
    public void kill(){
    	killed = true;
    }

    public static void main(String[] args) {
        PedestrianSim pedestrianSim = new PedestrianSim();
        pedestrianSim.start();

    }

    public class SimulationConnectionHandler implements Runnable {

        private Socket server;
        private PedestrianManager pedestrianManager;

        private SimulationConnectionHandler(Socket server, PedestrianManager pedestrianManager) {
            this.server = server;
            this.pedestrianManager = pedestrianManager;

        }

        @Override
        public void run() {
            while (!server.isClosed()) {
                handlePedestrian();
            }
        }

        /**
         * Handles a single request from a single pedestrian and sends back an action to the
         * pedestrian.
         */
        public void handlePedestrian() {

            try {
                System.out.println("Handling connection with pedestrian...");
                InputStream in = server.getInputStream();
                // in = checkInput(in);
                OutputStream out = server.getOutputStream();
                System.out.println("Trying to read observation from client...");
                protobuf.ObservationMessage.Observation observation = protobuf.ObservationMessage.Observation
                        .parseDelimitedFrom(in);
                System.out.println("Read observation: " + observation);
                /*
                 * PedestrianManager pedestrianManager = (PedestrianManager) context
                 * .get("pedestrianManager"); SituationManager situationManager = (SituationManager)
                 * context .get("situationManager"); PetriNetManager petriNetManager =
                 * (PetriNetManager) context.get("petriNetManager");
                 * 
                 * List<ISituation> currentSituations = situationManager.getSituations(
                 * observation.getLocationX(), observation.getLocationY());
                 */
                /*
                 * pedestrianManager .updateSituations(observation.getPedestrianId(),
                 * currentSituations);
                 */
                // Should only be done when needNewAction is true I think?
                Action action = buildAction(pedestrianManager.step(observation));
                if (action.getAction() != "wander") {
                    System.out.println("CurrentAction: " + action);
                }
                // Send response back to client
                action.writeDelimitedTo(out);
                // endOutput(out);
                System.out.println("Done handling connection with pedestrian");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        /*
         * private InputStream checkInput(InputStream in) { BufferedReader reader = new
         * BufferedReader(new InputStreamReader(in)); InputStream newIn = null; try { String line =
         * reader.readLine(); System.out.println("Line from input: " + line); newIn = new
         * ByteArrayInputStream(line.getBytes("UTF-8")); } catch (IOException e) { // TODO
         * Auto-generated catch block e.printStackTrace(); } return newIn; }
         */

        private Action buildAction(String actionDescription) {
            Action.Builder action = Action.newBuilder();
            action.setAction(actionDescription);
            return action.build();
        }
    }

	@Override
	public void run() {
		start();
		
	}

    /*
     * private class SimulationConnectionReceiver implements IConnectionReceiver{
     * 
     * //private Socket server; private Map<String, Object> context;
     * 
     * private SimulationConnectionReceiver(Map<String, Object> context){ //this.server = server;
     * this.context = context; }
     * 
     * @Override public void receiveMessage(byte[] data, String senderIp) { try {
     * System.out.println("Handling connection with pedestrian...");
     * protobuf.ObservationMessage.Observation observation =
     * protobuf.ObservationMessage.Observation.parseFrom(data); PedestrianManager pedestrianManager
     * = (PedestrianManager)context.get("pedestrianManager"); SituationManager situationManager =
     * (SituationManager)context.get("situationManager"); List<ISituation> currentSituations =
     * situationManager.getSituations(observation.getLocationX(), observation.getLocationY());
     * pedestrianManager.updateSituations(observation.getPedestrianId(),currentSituations); Action
     * action = buildAction(pedestrianManager.step(observation));
     * 
     * // Send response back to client //action.writeTo(server.getOutputStream());
     * 
     * System.out.println("Done handling connection with pedestrian");
     * 
     * } catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
     * 
     * }
     * 
     * private Action buildAction(String actionDescription){ Action.Builder action =
     * Action.newBuilder(); action.setAction(actionDescription); return action.build(); }
     * 
     * }
     */

}
