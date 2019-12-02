package earthquakes.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.HashMap;

import com.nimbusds.oauth2.sdk.client.ClientReadRequest;

import earthquakes.geojson.FeatureCollection;
import earthquakes.services.*;


//new import
import earthquakes.EarthquakeQueryService;
import earthquakes.searches.LocSearch;


@Controller
public class LocationsController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;


    @GetMapping("/locations/search")
    public String getLocationsSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken, LocSearch locSeatch){
	return "locations/search";
    }

    // @GetMapping("/earthquakes/results")
    // public String getEarthquakesResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,EqSearch eqSearch) {

	// EarthquakeQueryService e = new EarthquakeQueryService();
	
    //     model.addAttribute("eqSearch", eqSearch);

	// String json = e.getJSON(eqSearch.getDistance(), eqSearch.getMinmag());
    //     model.addAttribute("json", json);

	// FeatureCollection featureCollection = FeatureCollection.fromJSON(json);
    //     model.addAttribute("featureCollection",featureCollection);


	
    //     return "earthquakes/results";
    // }
	
}