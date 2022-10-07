package diploma.ecology.controllers;

import diploma.ecology.models.Places;
import diploma.ecology.repository.PlacesRepository;
import diploma.ecology.services.PlacesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PlacesController {

    @Autowired
    PlacesRepository placesRepository;

    @Autowired
    PlacesServices placesServices;


    @GetMapping("/allPlaces")
    @RolesAllowed("ADMIN")
    @CrossOrigin
    public ResponseEntity<List<Places>> getAllPlaces(@RequestParam(required = false) String title) {
        try {
            List<Places> places = new ArrayList<Places>();
            if (title == null)
                placesServices.findAll().forEach(places::add); else
                placesServices.findByNameContaining(title).forEach(places::add);
            if (places.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(places, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/places/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Places> getPlacesById(@PathVariable("id") int id) {
        Optional<Places> placeData = placesServices.findById(id);
        if (placeData.isPresent()) {
            return new ResponseEntity(placeData.get(), HttpStatus.OK); }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/places")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Places> createPlaces(@RequestBody Places places) {
        try {
            Places places1 = placesServices.save(new Places(places.getAddress(),places.getName(),true));
            return new ResponseEntity<>(places1,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/places/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Places> updatePlaces(@PathVariable("id") int id, @RequestBody Places places) {
        Optional<Places> placeData = placesServices.findById(id);
        if (placeData.isPresent()) {
            Places places1 = placeData.get();
            places1.setName(places.getName());
            places1.setAddress(places.getAddress());
            places1.setWorked(places.isWorked());
            return new ResponseEntity(placesServices.save(places1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/places/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<HttpStatus> deletePlaces(@PathVariable("id") int id) {
        try {
        placesServices.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

    @DeleteMapping("/places")
    @RolesAllowed("ADMIN")
    public ResponseEntity<HttpStatus> deleteAllPlaces() {
        try {
            placesServices.deleteAll();
            return new ResponseEntity(HttpStatus. NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

}
