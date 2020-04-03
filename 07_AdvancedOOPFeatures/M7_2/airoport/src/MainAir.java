import com.skillbox.airport.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MainAir {
    private static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");
    public static void main(String[] args) {

        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.HOUR_OF_DAY, 2);
        Date nowPlusTwo = cal.getTime();
        Airport air = Airport.getInstance();
        List<Terminal> termList = air.getTerminals();
        termList.stream().map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                .filter(flight -> nowDate.before(flight.getDate()) && nowPlusTwo.after(flight.getDate()))
                .forEach(flight -> System.out.printf("Время вылета -  %s, самолет - %s%n",
                        HOUR_FORMAT.format(flight.getDate()), flight.getAircraft()));

    }
}
