import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metro {

    private List<Line> lines;
    private Map<String, List<Station>> stationsOnLines;

    public Metro() {
        super();
    }

    public Metro(List<Line> lines, Map<String, List<Station>> stationsOnLines) {
        this.lines = lines;
        this.stationsOnLines = stationsOnLines;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Map<String, List<Station>> getStationsOnLines() {
        return stationsOnLines;
    }

    public void setStationsOnLines(Map<String, List<Station>> stationsOnLines) {
        this.stationsOnLines = stationsOnLines;
    }

}
