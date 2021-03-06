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
// import earthquakes.EarthquakeQueryService;
import earthquakes.searches.LocSearch;
import java.util.List;
import earthquakes.osm.Place;
import earthquakes.entities.Location;
import earthquakes.repositories.LocationRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


@Controller
public class LocationsController {

    private LocationRepository locationRepository;

    @Autowired
    // private ClientRegistrationRepository clientRegistrationRepository;
    
    public LocationsController (LocationRepository locationRepository) {
        this.locationRepository = locationRepository;   
    }


    @GetMapping("/locations/search")
    public String getLocationsSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken, LocSearch locSeatch){
	    return "locations/search";
    }

    @GetMapping("/locations/results")
    public String getLocationResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,LocSearch locSearch) {

	    LocationQueryService e = new LocationQueryService();
	
        model.addAttribute("locSearch", locSearch);

   
	    String json = e.getJSON(locSearch.getLocation());
        model.addAttribute("json", json);
        List<Place> Places = Place.listFromJson(json);
        model.addAttribute("Places",Places);
        
        return "locations/results";
    }


    @GetMapping("/locations")
        public String index(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Iterable<Location> locations = locationRepository.findByUid(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("id").toString());
        model.addAttribute("locations", locations);
        return "locations/index";
    }
	
    @PostMapping("/locations/add")
    public String add(Location location, Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
      location.setUid(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("id").toString());
      locationRepository.save(location);
      model.addAttribute("locations", locationRepository.findByUid(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("id").toString()));
      return "locations/index";
    }

    @GetMapping("/locations/admin")
    public String admin(Model model) {

        Iterable<Location> locations = locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "locations/admin";
    }


    @DeleteMapping("/locations/delete/{id}")
        public String delete(@PathVariable("id") long id, Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid courseoffering Id:" + id));
        locationRepository.delete(location);
        model.addAttribute("locations", locationRepository.findByUid(oAuth2AuthenticationToken.getPrincipal().getAttributes().get("id").toString()));
        return "locations/index";
    }
}