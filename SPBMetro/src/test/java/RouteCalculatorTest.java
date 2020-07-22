import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> routeWithTwoConnections;
    List<Station> routeWithNoConnections;
    List<Station> routeWithOneConnection;
    StationIndex stationIndex;
    Station pokrovskaya;
    Station lenina;
    Station svetlaya;
    Station kamishenskaya;
    Station kirova;
    Station geroev;
    Station andreeva;
    Station gonchaya;
    Station pobedi;

    public void setUp() throws Exception {

        stationIndex = new StationIndex();
        routeWithNoConnections = new ArrayList<>();
        routeWithOneConnection = new ArrayList<>();
        routeWithTwoConnections = new ArrayList<>();

        Line line1 = new Line(1, "Первая");                  // Lines
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        pokrovskaya = new Station("Покровская", line1);     // Stations
        lenina = new Station("Ленина", line1);
        svetlaya = new Station("Светлая", line1);
        kamishenskaya = new Station("Камышенская", line2);
        kirova = new Station("Кирова", line2);
        geroev = new Station("Героев", line2);
        andreeva = new Station("Андреева", line3);
        gonchaya = new Station("Гончая", line3);
        pobedi = new Station("Победы", line3);

        line1.addStation(pokrovskaya);
        line1.addStation(lenina);
        line1.addStation(svetlaya);
        line2.addStation(kamishenskaya);
        line2.addStation(kirova);
        line2.addStation(geroev);
        line3.addStation(andreeva);
        line3.addStation(gonchaya);
        line3.addStation(pobedi);

        stationIndex.addStation(pokrovskaya);
        stationIndex.addStation(lenina);
        stationIndex.addStation(svetlaya);
        stationIndex.addStation(kamishenskaya);
        stationIndex.addStation(kirova);
        stationIndex.addStation(geroev);
        stationIndex.addStation(andreeva);
        stationIndex.addStation(gonchaya);
        stationIndex.addStation(pobedi);

        stationIndex.addConnection(new ArrayList<Station>() {{     // Connections
            add(svetlaya);
            add(kamishenskaya);
        }});
        stationIndex.addConnection(new ArrayList<Station>() {{
            add(geroev);
            add(andreeva);
        }});

        routeWithNoConnections.add(pokrovskaya);
        routeWithNoConnections.add(lenina);
        routeWithNoConnections.add(svetlaya);

        routeWithOneConnection.add(pokrovskaya);
        routeWithOneConnection.add(lenina);
        routeWithOneConnection.add(svetlaya);
        routeWithOneConnection.add(kamishenskaya);
        routeWithOneConnection.add(kirova);

        routeWithTwoConnections.add(pokrovskaya);
        routeWithTwoConnections.add(lenina);
        routeWithTwoConnections.add(svetlaya);
        routeWithTwoConnections.add(kamishenskaya);
        routeWithTwoConnections.add(kirova);
        routeWithTwoConnections.add(geroev);
        routeWithTwoConnections.add(andreeva);
        routeWithTwoConnections.add(gonchaya);
        routeWithTwoConnections.add(pobedi);
    }

    public void testCalculateDuration() {

        double actual = RouteCalculator.calculateDuration(routeWithNoConnections);
        double expected = 5;
        assertEquals(expected, actual);

        double actual1 = RouteCalculator.calculateDuration(routeWithOneConnection);
        double expected1 = 11;
        assertEquals(expected1, actual1);

        double actual2 = RouteCalculator.calculateDuration(routeWithTwoConnections);
        double expected2 = 22;
        assertEquals(expected2, actual2);
    }

    public void testGetShortestRoute() {

        RouteCalculator calculator = new RouteCalculator(stationIndex);
        List<Station> actual = calculator.getShortestRoute(pokrovskaya, svetlaya);
        assertEquals(routeWithNoConnections, actual);

        List<Station> actual1 = calculator.getShortestRoute(pokrovskaya, kirova);
        assertEquals(routeWithOneConnection, actual1);

        List<Station> actual2 = calculator.getShortestRoute(pokrovskaya, pobedi);
        assertEquals(routeWithTwoConnections, actual2);

    }

    public void tearDown() throws Exception {

    }
}
