import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    StationIndex stationIndex;
    RouteCalculator routeCalculator;
    List<Station> route = new ArrayList<>();

    Line line1;
    Line line2;
    Line line3;

    Station station1;
    Station station2;
    Station station3;
    Station station4;
    Station station5;
    Station station6;
    Station station7;
    Station station8;

    @Override
    public void setUp() throws Exception {
        line1 = new Line(1, "Первая");
        line2 = new Line(2, "Вторая");
        line3 = new Line(3, "Третья");

//        route.add(new Station("Петровская", line1));
//        route.add(new Station("Александровская", line1));
//        route.add(new Station("Ванеевская", line1));
//        route.add(new Station("Ильинская", line2));
//        route.add(new Station("Венская", line2));
//        route.add(new Station("Пролетарская", line2));
//        route.add(new Station("Липецская", line3));
//        route.add(new Station("Волжская", line3));


        station1 = new Station("Петровская", line1);
        station2 = new Station("Александровская", line1);
        station3 = new Station("Ванеевская", line1);
        station4 = new Station("Ильинская", line2);
        station5 = new Station("Венская", line2);
        station6 = new Station("Пролетарская", line2);
        station7 = new Station("Липецская", line3);
        station8 = new Station("Волжская", line3);

        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line2.addStation(station4);
        line2.addStation(station5);
        line2.addStation(station6);
        line3.addStation(station7);
        line3.addStation(station8);

        stationIndex = new StationIndex();

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);
        stationIndex.addStation(station7);
        stationIndex.addStation(station8);

        Station[] stations_1 = {stationIndex.getStation("Петровская"),
                stationIndex.getStation("Александровская"),
        };
        List<Station> stationsList_1 = Arrays.asList(stations_1);
        stationIndex.addConnection(stationsList_1);


        Station[] stations_2 = {stationIndex.getStation("Александровская"),
                stationIndex.getStation("Ванеевская"),
        };
        List<Station> stationsList_2 = Arrays.asList(stations_2);
        stationIndex.addConnection(stationsList_2);

        Station[] stations_3 = {stationIndex.getStation("Ильинская"),
                stationIndex.getStation("Венская"),
        };
        List<Station> stationsList_3 = Arrays.asList(stations_3);
        stationIndex.addConnection(stationsList_3);

        Station[] stations_4 = {stationIndex.getStation("Венская"),
                stationIndex.getStation("Пролетарская"),
        };
        List<Station> stationsList_4 = Arrays.asList(stations_4);
        stationIndex.addConnection(stationsList_4);

        Station[] stations_5 = {stationIndex.getStation("Липецская"),
                stationIndex.getStation("Волжская"),
        };
        List<Station> stationsList_5 = Arrays.asList(stations_5);
        stationIndex.addConnection(stationsList_5);

        Station[] stations_6 = {stationIndex.getStation("Волжская"),
                stationIndex.getStation("Александровская"),
        };
        List<Station> stationsList_6 = Arrays.asList(stations_6);
        stationIndex.addConnection(stationsList_6);

        Station[] stations_7 = {stationIndex.getStation("Липецская"),
                stationIndex.getStation("Венская"),
        };
        List<Station> stationsList_7 = Arrays.asList(stations_7);
        stationIndex.addConnection(stationsList_7);

        routeCalculator = new RouteCalculator(stationIndex);
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 19.5;

        assertEquals(expected, actual);
    }

    public void testGetShortestRoute() {
        int actual = routeCalculator.getShortestRoute(station1,
                station6).size();
        System.out.println();
        int expected = 6;

        assertEquals(expected, actual);
    }

}

