package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Place {
    @JsonProperty("place name")
    private String placeName;
    private String longitude;
    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;
    private String latitude;


    Place() {
        this.placeName = "Riga";
        this.longitude = "1";
        this.state = "Riga";
        this.stateAbbreviation = "RI";
        this.latitude = "1";
    }
}
