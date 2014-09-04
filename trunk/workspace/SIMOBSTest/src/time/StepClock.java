package time;

public class StepClock implements IClock {

    long currentTime;

    public StepClock() {
        this(0);
    }

    public StepClock(long defaultGoalTime) {
        currentTime = defaultGoalTime;
    }

    @Override
    public void step() {
        System.out.println("Changing time 1 step.");
        currentTime++;

    }

    @Override
    public void steps(long timeSteps) {
        currentTime += timeSteps;

    }

    @Override
    public long getTime() {
        return currentTime;
    }

    @Override
    public int compareTo(IClock arg0) {
        Long time1 = new Long(currentTime);
        return time1.compareTo(arg0.getTime());
    }

    @Override
    public String toString() {
        return "Current step time: " + currentTime;
    }

}
