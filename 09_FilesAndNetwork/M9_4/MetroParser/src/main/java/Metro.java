import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metro {

    private List<Line> lines;
    private List<Station> stations;
    private Map<String, Integer> countStationsOnLine;

    public Metro() {
        super();
    }

    public Metro(List<Line> lines, List<Station> stations) {
        this.lines = lines;
        this.stations = stations;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public void getNumberOfStationsOnLine() {
        countStationsOnLine = new HashMap<>();
        lines.forEach(line -> {
            countStationsOnLine.put(line.getName(), 0);
            stations.forEach(station -> {
                if (station.getNumberOfLine().equals(line.getNumber())) {
                    countStationsOnLine.replace(line.getName(), countStationsOnLine.get(line.getName()) + 1);
                }
            });
        });
        countStationsOnLine.forEach((key, value) -> System.out.println("Линия - " + key + ": количество станций - " + value));
    }
}
