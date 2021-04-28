package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }
    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate){
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.created(null).body(timeEntry);
    }
    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if (timeEntry==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(timeEntry);

    }
    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry=timeEntryRepository.update(timeEntryId, timeEntryToUpdate);
        if (timeEntry==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(timeEntry);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
//        TimeEntry timeEntry= timeEntryRepository.delete(timeEntryId);
//        return ResponseEntity.notFound(timeEntry);
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
//        TimeEntry timeEntr (TimeEntry) timeEntryRepository.list();
        List<TimeEntry> list = timeEntryRepository.list();
        return ResponseEntity.ok(list);

    }
}
