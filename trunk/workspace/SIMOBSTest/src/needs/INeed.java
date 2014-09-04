package needs;

import java.util.Calendar;

public interface INeed {

    public String getName();

    public double getIntensity(Calendar time);

    public void setIntensityCurve(INeedIntensityCurve curve);

}
