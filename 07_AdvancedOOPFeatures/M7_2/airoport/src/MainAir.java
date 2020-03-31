import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Terminal;

import java.util.List;

public class MainAir {
    public static void main(String[] args) {
        Airport air = Airport.getInstance();
        List<Terminal> termList = air.getTerminals();

        termList.forEach(terminal -> System.out.println(terminal.getFlights()));
    }
}
