import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> route = new ArrayList<>();
    StationIndex stationIndex = new StationIndex();
    RouteCalculator calc = new RouteCalculator(stationIndex);

    @Override
    protected void setUp() throws Exception {

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");


        Station s1 = new Station("Зябликово", line1);
        route.add(s1);
        line1.addStation(s1);
        Station s2 = new Station("Марьина роща", line1);
        route.add(s2);
        line1.addStation(s2);
        Station s3 = new Station("Достоевская", line2);
        route.add(s3);
        line2.addStation(s3);
        Station s4 = new Station("Савеловская", line2);
        route.add(s4);
        line2.addStation(s4);
        Station s5 = new Station("Первостепенная", line3);
        route.add(s5);
        line3.addStation(s5);
        Station s6 = new Station("Новоглинная", line3);
        route.add(s6);
        line3.addStation(s6);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(s1);
        stationIndex.addStation(s2);
        stationIndex.addStation(s3);
        stationIndex.addStation(s4);
        stationIndex.addStation(s5);
        stationIndex.addStation(s6);

        List<Station> connectionStations = new ArrayList<>();
        connectionStations.add(s2);
        connectionStations.add(s3);
        stationIndex.addConnection(connectionStations);
        connectionStations = new ArrayList<>();
        connectionStations.add(s3);
        connectionStations.add(s4);
        stationIndex.addConnection(connectionStations);
        connectionStations = new ArrayList<>();
        connectionStations.add(s4);
        connectionStations.add(s5);
        stationIndex.addConnection(connectionStations);
    }

    @Test
    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 14.5;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRouteLength() {
        int actual = route.size();
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetShortestRoute() {
        List<Station> actual = calc.getShortestRoute(stationIndex.getStation("Зябликово"), stationIndex.getStation("Новоглинная"));
        assertEquals(route, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
