package main;

import main.model.DoingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Doing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DoingController {
    @Autowired
    private DoingRepository doingRepository;

    //    @RequestMapping(value = "/doings/", method = RequestMethod.GET)
    @GetMapping(value = "/doings/")
    public List<Doing> list() {
        List<Doing> doings = new ArrayList<>();
        doingRepository.findAll().iterator().forEachRemaining(doings::add);
        return doings;
//        return Storage.getAllDoings();
    }

    //    @RequestMapping(value = "/doings/", method = RequestMethod.POST)
    @PostMapping(value = "/doings/")
    public synchronized int addDoing(String doing, String who) {
        Doing newDoing = new Doing();
        newDoing.setWho(who);
        newDoing.setDoing(doing);
        doingRepository.save(newDoing);
        return newDoing.getId();
//        return doingRepository.save(doing).getId();
//        return Storage.addDoing(doing);
    }

    @GetMapping(value = "/doings/{id}")
    public ResponseEntity getDoing(@PathVariable int id) {
        Optional<Doing> optional = doingRepository.findById(id);
//        Doing doing = Storage.getDoing(id);
        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optional.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/doings/{id}")
    public synchronized ResponseEntity deleteDoing(@PathVariable int id) {
        Optional<Doing> optional = doingRepository.findById(id);
//        int res = Storage.deleteDoing(id);
        if (!optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        doingRepository.delete(optional.get());
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @DeleteMapping(value = "/doings/")
    public void deleteAllDoings() {
        doingRepository.deleteAll();
//        Storage.deleteAllDoings();
    }

    @PutMapping(value = "/doings/{id}")
    public synchronized int editDoing(@PathVariable int id, String doing, String who) {
        Optional<Doing> optional = doingRepository.findById(id);
        if (!optional.isPresent()) {
            System.out.println("asdasdas");
            addDoing(doing, who);
            return id;
        }
        Doing editDoing = optional.get();
        editDoing.setDoing(doing);
        editDoing.setWho(who);
        doingRepository.save(editDoing);
//        int todo = Storage.editDoing(id, doing, who);
//        if (todo == 0) {
//            Doing newDoing = new Doing();
//            newDoing.setDoing(doing);
//            newDoing.setWho(who);
//            return Storage.addDoing(newDoing);
//        }
        return id;
    }
}
