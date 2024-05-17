package org.demo.notesharingapp.controller;

import org.demo.notesharingapp.entity.Sharer;
import org.demo.notesharingapp.service.abstracts.SharerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sharers")
public class SharerController {

    private final SharerService sharerService;

    @Autowired
    public SharerController(SharerService sharerService) {
        this.sharerService = sharerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Sharer> saveSharer(@RequestBody Sharer sharer) {
        Sharer savedSharer = sharerService.saveSharer(sharer);
        return new ResponseEntity<>(savedSharer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{sharerId}")
    public ResponseEntity<Sharer> updateSharer(@PathVariable int sharerId, @RequestBody Sharer updatedSharer) {
        Sharer updated = sharerService.updateSharer(sharerId, updatedSharer);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{sharerId}")
    public ResponseEntity<Void> deleteSharer(@PathVariable int sharerId) {
        sharerService.deleteSharer(sharerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sharer>> getSharers() {
        List<Sharer> sharers = sharerService.getSharers();
        return new ResponseEntity<>(sharers, HttpStatus.OK);
    }

    @GetMapping("/{sharerId}")
    public ResponseEntity<Sharer> getSharerById(@PathVariable int sharerId) {
        Sharer sharer = sharerService.getSharerById(sharerId);
        return new ResponseEntity<>(sharer, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<Sharer> getSharerByEmail(@RequestParam String email) {
        Sharer sharer = sharerService.getSharerByEmail(email);
        return new ResponseEntity<>(sharer, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<Sharer> getSharerByUsername(@RequestParam String username) {
        Sharer sharer = sharerService.getSharerByUsername(username);
        return new ResponseEntity<>(sharer, HttpStatus.OK);
    }
}
