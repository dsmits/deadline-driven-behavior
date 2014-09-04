package mason;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.field.grid.IntGrid2D;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.FieldPortrayal2D;
import sim.portrayal.Portrayal;
import sim.portrayal.continuous.ContinuousPortrayal2D;
import sim.portrayal.grid.FastValueGridPortrayal2D;
import sim.util.gui.ColorMap;
import situations.SituationArea;

/**
 * @author smitsds
 * 
 */
public class PedestrianSimWithUI extends GUIState {
	public Display2D display;
	public JFrame displayFrame;
	private Random random;
	private Document config;
	private static final double BACKGROUND_WIDTH = 58;
	private static final double BACKGROUND_HEIGHT = 29;
	

	ContinuousPortrayal2D agentsPortrayal = new ContinuousPortrayal2D();

	protected PedestrianSimWithUI(SimState state) {
		super(state);
		random = new Random();

	}

	public PedestrianSimWithUI() {
		this(
				new PedestrianSimState(System.currentTimeMillis(), BACKGROUND_WIDTH,
						BACKGROUND_HEIGHT));
	}

	public void init(Controller c) {
		super.init(c);

		// make the displayer
		// TODO: Retrieve size from xml file
		// For some reason, we need to subtract 17 from the width, and 42 from the height to get the
		// right proportions.
		// display = new Display2D(483, 458, this, 1);
		display = new Display2D(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, this, 1);
		display.setClipping(false);

		displayFrame = display.createFrame();
		displayFrame.setTitle("Pedestrians");
		c.registerFrame(displayFrame); // register the frame so it appears in the "Display" list
		displayFrame.setVisible(true);

		drawEnvironment();
		display.attach(agentsPortrayal, "pedestrianPortrayal");

	}

	public static void main(String[] args) {
		new PedestrianSimWithUI().createController(); // randomizes by currentTimeMillis
	}

	public static String getName() {
		return "The Pedestrian Simulator";
	}

	public void start() {
		super.start();
		setupPortrayals();
	}

	public void load(SimState state) {
		super.load(state);
		setupPortrayals();
	}

	public void setupPortrayals() {
		Node backgroundElement = (Element) config.getElementsByTagName("background").item(0);
		String backgroundFile = backgroundElement.getTextContent();
		backgroundFile.trim();

		PedestrianSimState pedestrianSimState = (PedestrianSimState) state;
		// obstacle portrayal needs no setup
		agentsPortrayal.setField(pedestrianSimState.agents);
		setBackgroundPortrayal(backgroundFile);
		setAgentPortrayals(agentsPortrayal, pedestrianSimState.agents);

		// reschedule the displayer
		display.reset();
		// display.setBackdrop(Color.white);

		// redraw the display
		display.repaint();

	}

	private void setAgentPortrayals(ContinuousPortrayal2D agentsPortrayal, Continuous2D agents) {

		for (Object agent : agents.allObjects) {
			agentsPortrayal.setPortrayalForObject(agent, (Portrayal) agent);
		}

	}

	private void setBackgroundPortrayal(String backgroundFileName) {

		FieldPortrayal2D backgroundPortrayal = new BackgroundPortrayal(backgroundFileName);

		display.attach(backgroundPortrayal, "background");
		// display.setComponentZOrder(arg0, arg1)
	}

	/**
	 * Loads situations.xml and draws rectangles on the screen where the different situations are
	 * located.
	 */
	public void drawEnvironment() {

		config = getConfigDocument("situations.xml");
		NodeList situations = config.getElementsByTagName("situations").item(0).getChildNodes();
		Node currentSituationNode;
		SituationArea situationArea;

		for (int i = 0; i < situations.getLength(); i++) {
			currentSituationNode = situations.item(i);
			// If statement is needed to ignore empty Text nodes
			if (currentSituationNode.getNodeType() == Node.ELEMENT_NODE) {
				situationArea = getSituationArea((Element) currentSituationNode);
				//drawSituation(situationArea);
			}

			// currentSituationClass = Class.forName(arg0)
		}
		// loadBackgroundImage(config);
	}

	private void loadBackgroundImage(Document config) {
		Node backgroundElement = (Element) config.getElementsByTagName("background").item(0);
		File backgroundFile = new File(backgroundElement.getTextContent());
		try {
			System.out.println("Loading backgroundImage: " + backgroundFile.getAbsolutePath());
			BufferedImage backgroundImage = ImageIO.read(backgroundFile);
			JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
			backgroundLabel.setSize(display.getSize());
			display.add(backgroundLabel);
			display.reset();
			display.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void drawSituation(SituationArea situationArea) {
		int gridColor = (int) (random.nextDouble() * 0x00ffffff);
		FastValueGridPortrayal2D situationPortrayal = new FastValueGridPortrayal2D(
				situationArea.toString(), true);
		situationPortrayal.setMap(new AlphaColorMap());
		IntGrid2D situationGrid = new IntGrid2D(display.getWidth(), display.getHeight(), -1);

		fillGrid(situationGrid, situationArea.getTopLeftX(), situationArea.getTopLeftY(),
				situationArea.getBottomRightX(), situationArea.getBottomRightY(), gridColor);
		situationPortrayal.setField(situationGrid);
		display.attach(situationPortrayal, situationArea.toString());

	}

	private void fillGrid(IntGrid2D situationGrid, int topLeftX, int topLeftY, int bottomRightX,
			int bottomRightY, int color) {

		for (int x = topLeftX; x < bottomRightX; x++) {
			for (int y = topLeftY; y < bottomRightY; y++) {
				situationGrid.set(x, y, color);
			}
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

	private SituationArea getSituationArea(Element currentSituationNode) {
		Element areaNode = (Element) currentSituationNode.getElementsByTagName("situationArea")
		.item(0);
		SituationArea area = new SituationArea();
		int topLeftX = Integer.parseInt(areaNode.getElementsByTagName("x1").item(0)
				.getTextContent());
		area.setTopLeftX(topLeftX);
		int upperLeftY = Integer.parseInt(areaNode.getElementsByTagName("y1").item(0)
				.getTextContent());
		area.setTopLeftY(upperLeftY);
		int bottomRightX = Integer.parseInt(areaNode.getElementsByTagName("x2").item(0)
				.getTextContent());
		area.setBottomRightX(bottomRightX);
		int bottomRightY = Integer.parseInt(areaNode.getElementsByTagName("y2").item(0)
				.getTextContent());
		area.setBottomRightY(bottomRightY);
		return area;
	}

	public class AlphaColorMap implements ColorMap {

		private Color createColor(double level) {
			int colorValue = (int) level;
			boolean hasAlpha = false;
			if (colorValue < 0) {
				colorValue = 0;
				hasAlpha = true;
			}
			// System.out.println("Color value: " + Integer.toHexString(colorValue));
			Color color = new Color(colorValue, hasAlpha);
			return color;
		}

		@Override
		public Color getColor(double level) {
			return createColor(level);

		}

		@Override
		public int getRGB(double level) {
			return createColor(level).getRGB();
		}

		@Override
		public int getAlpha(double level) {
			return createColor(level).getAlpha();
		}

		@Override
		public boolean validLevel(double level) {
			return true;
		}

		@Override
		public double defaultValue() {
			return 0;
		}

	}

	public class BackgroundPortrayal extends FastValueGridPortrayal2D {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		BufferedImage backgroundImage;

		public BackgroundPortrayal(String backgroundFileName) {
			this(new File(backgroundFileName));
		}

		public BackgroundPortrayal(File backgroundFile) {
			super(true);
			try {
				System.out.println("Background file: " + backgroundFile);
				backgroundImage = ImageIO.read(backgroundFile);
				/*
				 * Continuous2D field = new Continuous2D(1, backgroundImage.getWidth(),
				 * backgroundImage.getHeight()); setField(field);
				 */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
			graphics.drawImage(backgroundImage, (int) info.draw.x, (int) info.draw.y,
					(int) info.draw.width, (int) info.draw.height, null);
		}
	}

	/*
	 * public static void main(String args[]){ PedestrianSimWithUI ui = new PedestrianSimWithUI();
	 * PedestrianSimWithUI.AlphaColorMap map = ui.new AlphaColorMap(); System.out.println("Alpha: "
	 * + map.getAlpha(-1));
	 * 
	 * }
	 */

}
