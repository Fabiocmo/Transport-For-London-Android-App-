package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.model.StationManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

@Override
    protected URL getURL() throws MalformedURLException {
        String request = stn.getName();
        String stopPointId = stn.getID();
        String ids = null;
        for(Line l : stn.getLines()) {
            if (stn.getLines().size() > 1 && stn.getLines().iterator().hasNext()) {
                ids = l.getId() + ",";
            }
            else {
                ids = l.getId();
            }
        }
        String url = "https://api.tfl.gov.uk/Line/"+ ids + "/Arrivals?stopPointId=" + stopPointId + "&app_id=&app_key=";
        return new URL(url);
    }
}