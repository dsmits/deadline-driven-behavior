package needs;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Hunger extends AbstractNeed {

    /**
     * Instantiates Hunger need with default values (peaks around breakfast- lunch and dinner time).
     */
    public Hunger() {
        DiscreteNeedIntensityDayCurve curve = new DiscreteNeedIntensityDayCurve();

        // Breakfast
        Calendar breakfastTime = new GregorianCalendar();
        breakfastTime.set(Calendar.HOUR_OF_DAY, 9);
        curve.addPeakTime(breakfastTime);

        // Lunch
        Calendar lunchTime = new GregorianCalendar();
        lunchTime.set(Calendar.HOUR_OF_DAY, 12);
        curve.addPeakTime(lunchTime);

        // Dinner
        Calendar dinnerTime = new GregorianCalendar();
        lunchTime.set(Calendar.HOUR_OF_DAY, 6);
        curve.addPeakTime(dinnerTime);

        setIntensityCurve(curve);

    }

    public Hunger(INeedIntensityCurve curve) {
        setIntensityCurve(curve);
    }

}
