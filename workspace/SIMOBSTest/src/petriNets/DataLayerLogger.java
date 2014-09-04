package petriNets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;

import pipe.common.dataLayer.DataLayer;
import pipe.common.dataLayer.DataLayerWriter;

public class DataLayerLogger {

    private File logFile;
    private DataLayer dataLayer;
    private int counter;
    private File dataLayerDir;
// Hopefully the code will speed up when the logger does not log
    public DataLayerLogger(DataLayer dataLayer) throws IOException {
//        counter = 0;
//        this.dataLayer = dataLayer;
//        String logFileName = String.valueOf(dataLayer.hashCode());
//        logFileName += ".log";
//
//        String dataLayerDirName = String.valueOf(dataLayer.hashCode());
//        dataLayerDirName += dataLayer.getClass().getSimpleName();
//        dataLayerDirName = "logs/" + dataLayerDirName;
//
//        dataLayerDir = new File(dataLayerDirName);
//        dataLayerDir.mkdirs();
//        logFile = new File(dataLayerDir, logFileName);
//        System.out.println("Creating logfile named: " + logFileName);
//        logFile.createNewFile();
//
    }

    public void log(String string) throws IOException {
//        writeToLog(string);
//
//        File dataLayerFile = new File(dataLayerDir, counter + ".xml");
//        dataLayerFile.createNewFile();
//
//        DataLayerWriter dataLayerWriter = new DataLayerWriter(dataLayer);
//
//        try {
//            dataLayerWriter.savePNML(dataLayerFile);
//        } catch (NullPointerException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (DOMException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (TransformerConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        counter++;

    }

    private void writeToLog(String string) throws IOException {
        Writer logWriter = new FileWriter(logFile, true);

        logWriter.write(string + "\n");
        logWriter.close();

    }

    /*
     * public void close() { try { writer.close(); } catch (IOException e) { // TODO Auto-generated
     * catch block e.printStackTrace(); } }
     */

}
