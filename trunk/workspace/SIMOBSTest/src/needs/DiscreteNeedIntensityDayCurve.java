package needs;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author smitsds
 * 
 *         DiscreteNeedIntensityDayCurve is an implementation of INeedIntensityCurve that
 *         discretizes time over a day, and will specify an intensity for every bin. Intensities can
 *         be specified per bin, or by defining peak intensities. These peak intensities will be
 *         interpolated by converting them to normal distributions and filling the bins by sampling
 *         this function.
 */
public class DiscreteNeedIntensityDayCurve implements INeedIntensityCurve {

    private static final int DEFAULT_NUMBER_BINS = 24000;
    private static final long DEFAULT_STANDARD_DEVIATION = 100000;
    private static final long MAX_TIME_OF_DAY = 86400000;
    private double[] intensities;
    private boolean modified;
    private long binWidth;
    private double totalSurface;

    public DiscreteNeedIntensityDayCurve() {
        this(DEFAULT_NUMBER_BINS);
    }

    public DiscreteNeedIntensityDayCurve(int bins) {
        intensities = new double[bins];
        modified = false;
        binWidth = MAX_TIME_OF_DAY / intensities.length;
    }

    public void addPeakTime(Calendar time) {
        addPeakTime(time, DEFAULT_STANDARD_DEVIATION);
    }

    /**
     * @param time
     *            Time of the peak intensity
     * @param standardDev
     *            Standard deviation in milliseconds.
     */
    public void addPeakTime(Calendar time, long standardDev) {
        long peakTimeOfDay = getTimeOfDay(time);
        double y;
        long t = (long) (0.5 * binWidth);

        for (int i = 0; i < intensities.length; i++) {
            y = sampleGaussian(peakTimeOfDay, standardDev, t);
            System.out.println(y);

            if (intensities[i] < y) {
                totalSurface += (y - intensities[i]) * binWidth;
                intensities[i] = y;
            }
            t += binWidth;
        }

        modified = true;
        // int bin = (int) (((double) timeOfDay) / ((double) MAX_TIME_OF_DAY) * intensities.length);

    }

    /**
     * Computes the time of the day in milliseconds
     * 
     * @param time
     *            The current time
     * @return current time in milliseconds
     */
    private long getTimeOfDay(Calendar time) {
        // round to whole hour
        long timeOfDay = time.get(Calendar.HOUR_OF_DAY) * 3600000;
        // add minutes
        timeOfDay += time.get(Calendar.MINUTE) * 60000;
        // add seconds
        timeOfDay += time.get(Calendar.SECOND) * 1000;
        // add milliseconds
        timeOfDay += time.get(Calendar.MILLISECOND);

        return timeOfDay;
    }

    // TODO: Maybe possible to use gaussian incrementally?
    /**
     * Computes the Gaussian distribution value for a specified x. The Gaussian distribution has the
     * specified mean and standarddeviation.
     * 
     * @param mean
     *            The mean of the Gaussian function
     * @param standarddev
     *            Standard deviation of the Gaussian function
     * @param x
     *            The x value which has to be the input for the Gaussian function.
     * @return The corresponding y value.
     */
    private double sampleGaussian(double mean, double standarddev, double x) {

        double y = (1 / Math.sqrt(2 * Math.PI * Math.pow(standarddev, 2)))
                * Math.exp(-1 * (Math.pow(x - mean, 2) / Math.pow(2 * standarddev, 2)));

        return y;
    }

    @Override
    public double getNeedIntensity(Calendar time) {
        if (modified) {
            normalizeIntensities();
        }
        long timeOfDay = getTimeOfDay(time);
        return intensities[(int) (timeOfDay / binWidth)];
    }

    private void normalizeIntensities() {
        for (int i = 0; i < intensities.length; i++) {
            intensities[i] = intensities[i] / totalSurface;
        }
        totalSurface = 1;

    }

    public static void main(String args[]) {
        DiscreteNeedIntensityDayCurve curve = new DiscreteNeedIntensityDayCurve();
        Calendar time = new GregorianCalendar();
        curve.addPeakTime(time);
        System.out.println(curve.getNeedIntensity(new GregorianCalendar()));
    }
}
