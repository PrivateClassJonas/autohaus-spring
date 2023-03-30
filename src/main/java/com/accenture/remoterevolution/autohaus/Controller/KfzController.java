package com.accenture.remoterevolution.autohaus.Controller;

import com.accenture.remoterevolution.autohaus.DTOs.KfzDto;
import com.accenture.remoterevolution.autohaus.Services.KfzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kfz")
public class KfzController {
    @Autowired
    private KfzService kfzService;

    @GetMapping("/")
    public ResponseEntity<List<KfzDto>> showKfzs() {
        Optional<List<KfzDto>> response = kfzService.showKfzs();
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }

    @PostMapping(path = "/")
    public @ResponseBody ResponseEntity<KfzDto> addNewKfz(
            @RequestBody KfzDto kfzDto) {
        Optional<KfzDto> response = kfzService.addNewKfz(kfzDto);
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }


    @DeleteMapping(path = "/{kfzId}")
    public @ResponseBody ResponseEntity<KfzDto> deleteKfz(
            @PathVariable("kfzId") String autohausGUID) {
        Optional<KfzDto> response = kfzService.deleteKfz(autohausGUID);
        if (response.isEmpty()) ResponseEntity.badRequest().build();
        return ResponseEntity.ok(response.get());
    }
}
