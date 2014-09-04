package needs;

import java.util.Calendar;

public class AbstractNeed implements INeed {

    private INeedIntensityCurve needIntensityCurve;

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public double getIntensity(Calendar time) {
        return needIntensityCurve.getNeedIntensity(time);
    }

    @Override
    public void setIntensityCurve(INeedIntensityCurve curve) {
        needIntensityCurve = curve;
    }

}
