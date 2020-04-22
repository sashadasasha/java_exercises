import core.Line;
import core.Station;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;


public class RouteCalculatorTest {
    private final double EPS = 1e-9;
    static List<Station> route = new ArrayList<>();
    static List<Station> routeOnOneLine = new ArrayList<>();
    static List<Station> routeOnTwoLines = new ArrayList<>();
    static StationIndex stationIndex = new StationIndex();
    RouteCalculator calc = new RouteCalculator(stationIndex);
    @BeforeClass
    public static void createRoute() throws Exception {

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");


        Station s1 = new Station("Зябликово", line1);
        route.add(s1);
        routeOnOneLine.add(s1);

        line1.addStation(s1);
        Station s2 = new Station("Марьина роща", line1);
        route.add(s2);
        routeOnOneLine.add(s2);
        line1.addStation(s2);

        Station s3 = new Station("Достоевская", line2);
        route.add(s3);
        routeOnTwoLines.add(s3);
        line2.addStation(s3);

        Station s4 = new Station("Савеловская", line2);
        route.add(s4);
        routeOnTwoLines.add(s4);
        line2.addStation(s4);

        Station s5 = new Station("Первостепенная", line3);
        route.add(s5);
        routeOnTwoLines.add(s5);
        line3.addStation(s5);

        Station s6 = new Station("Новоглинная", line3);
        route.add(s6);
        routeOnTwoLines.add(s6);
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

        stationIndex.addConnection(Arrays.asList(s2,s3));
        stationIndex.addConnection(Arrays.asList(s3,s4));
        stationIndex.addConnection(Arrays.asList(s4,s5));
    }

    @Test
    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 14.5;
        Assert.assertEquals(expected, actual, EPS);
    }

    /**
     * На самом деле здесь я хотела проверять, что маршрут выстраивается той длины, которой задумано))
     * Здесь по-хорошему, конечно, не у route надо size измерять, а у того метода, который вернет route.
     * В принципе, при проверки эквивалентности маршрутов, проверка не имеет смысла, поэтому просто оставила в каждом методе
     * под комментарием
     */
//    @Test
//    public void testGetRouteLength() {
//    }

    @Test
    public void shouldReturnShortestRouteWhenStationsOnOneLine() {
        List<Station> actual = calc.getShortestRoute(stationIndex.getStation("Зябликово"), stationIndex.getStation("Марьина роща"));
        Assert.assertThat(actual, is(routeOnOneLine));
        //Assert.assertEquals(actual.size(), routeOnOneLine.size(), EPS);
    }

    @Test
    public void shouldReturnShortestRouteWithOneConnect() {
        List<Station> actual = calc.getShortestRoute(stationIndex.getStation("Достоевская"), stationIndex.getStation("Новоглинная"));
        Assert.assertThat(actual, is(routeOnTwoLines));
        //Assert.assertEquals(actual.size(), routeOnTwoLines.size(), EPS);
    }

    @Test
    public void shouldReturnShortestRouteWithTwoConnect() {
        List<Station> actual = calc.getShortestRoute(stationIndex.getStation("Зябликово"), stationIndex.getStation("Новоглинная"));
        Assert.assertThat(actual, is(route));
        //Assert.assertEquals(actual.size(), route.size(), EPS);
    }

}
