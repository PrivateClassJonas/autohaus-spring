package com.accenture.remoterevolution.autohaus.Controller;

import com.accenture.remoterevolution.autohaus.DTOs.AutohausDto;
import com.accenture.remoterevolution.autohaus.Services.AutohausService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/autohaus")
public class AutohausController {

    @Autowired
    private AutohausService autohausService;


    @GetMapping("/")
    public ResponseEntity<List<AutohausDto>> showAutohaus() {
        Optional<List<AutohausDto>> response = autohausService.showAutohaus();
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }

    @GetMapping("/{autohausId}")
    public ResponseEntity<AutohausDto> showAutohausById(
            @PathVariable("autohausId") String autohausGUID) {
        Optional<AutohausDto> response = autohausService.showAutohausById(autohausGUID);
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }

    @PostMapping(path = "/")
    public @ResponseBody ResponseEntity<AutohausDto> addNewAutohaus(
            @Valid @RequestBody AutohausDto autohausDto) {
        Optional<AutohausDto> response = autohausService.addNewAutohaus(autohausDto);
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }

    @PostMapping(path = "/all/")
    public @ResponseBody ResponseEntity<List<AutohausDto>> addNewAutohause(
            @Valid @RequestBody AutohausDto[] autohausDto) {
        Optional<List<AutohausDto>> response = autohausService.addNewAutohause(autohausDto);
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }

    @DeleteMapping("/{autohausId}")
    public @ResponseBody ResponseEntity<AutohausDto> deleteAutohausById(
            @PathVariable("autohausId") String autohausGUID) {
        Optional<AutohausDto> response = autohausService.deleteAutohausById(autohausGUID);
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }

    @DeleteMapping("/")
    public @ResponseBody ResponseEntity<List<AutohausDto>> deleteAutohausWithNull() {
        Optional<List<AutohausDto>> response = autohausService.deleteAutohausWithNull();
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }

    @RequestMapping(value = "/{autohausID}", method = PUT)
    public @ResponseBody ResponseEntity<AutohausDto> updateAutohausByID(
            @PathVariable("autohausID") String autohausGUID,
            @Valid @RequestBody AutohausDto autohausDto) {
        Optional<AutohausDto> response = autohausService.updateAutohausByID(autohausGUID, autohausDto);
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }

}
