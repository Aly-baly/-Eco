package diploma.ecology.services;

import diploma.ecology.models.Places;
import diploma.ecology.repository.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlacesServices {

    PlacesRepository placesRepository;

    @Autowired
    public void setPlacesRepository(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public List<Places> findByWorked(boolean worked){
        return placesRepository.findByWorked(worked);
    }

    public List<Places> findAll(){
        return placesRepository.findAll();
    }

    public List<Places> findByNameContaining(String title){
        return placesRepository.findByNameContaining(title);
    }

    public Optional<Places> findById(int id){
        return placesRepository.findById(id);
    }

    public void deleteAll(){
        placesRepository.deleteAll();
    }

    public void deleteById(int id){
        placesRepository.deleteById(id);
    }

    public Places save(Places places){
       return placesRepository.save(places);
    }
}
