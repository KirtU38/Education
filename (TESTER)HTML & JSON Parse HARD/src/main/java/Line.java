import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor @Data
public class Line {

    String name;
    String number;
    List<Station> stations;
}
