import java.util.Date;
import java.util.TreeSet;

public class WorkTime {

    private TreeSet<TimePeriod> periods;
    private int station;

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    /**
     * Set of TimePeriod objects
     */
    public WorkTime() {
        periods = new TreeSet<>();
    }

    public void addVisitTime(long visitTime) {
        Date visit = new Date(visitTime);
        TimePeriod newPeriod = new TimePeriod(visit, visit);
        for (TimePeriod period : periods) {
            if (period.compareTo(newPeriod) == 0) {
                period.appendTime(visit);
                return;
            }
        }
        periods.add(new TimePeriod(visit, visit));
    }

    public String toString() {
        String line = "";
        for (TimePeriod period : periods) {
            if (!line.isEmpty()) {
                line += ", ";
            }
            line += period;
        }
        return line;
    }
}
