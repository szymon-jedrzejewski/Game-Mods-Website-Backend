package com.gmw.api.rest;

import com.gmw.api.rest.activity.mod.*;
import com.gmw.api.rest.tos.ModDTO;
import com.gmw.fieldvalue.tos.SearchFieldValue;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.mod.tos.NewModTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mod")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ModController {

    @GetMapping("/getMod/{modId}")
    public ResponseEntity<ModDTO> obtainMod(@PathVariable Long modId) {
        FindModByIdActivity activity = new FindModByIdActivity(modId);
        return activity.execute();
    }

    @GetMapping("/findModsByGameId/{gameId}")
    public ResponseEntity<List<ExistingModTO>> obtainModsByGameId(@PathVariable Long gameId) {
        FindModsByGameIdActivity activity = new FindModsByGameIdActivity(gameId);
        return activity.execute();
    }

    @PostMapping("/findModsBySearchValues")
    public ResponseEntity<List<ExistingModTO>> obtainModsByGameId(@RequestBody List<SearchFieldValue> searchValues) {
        FindModsBySearchValuesActivity activity = new FindModsBySearchValuesActivity(searchValues);
        return activity.execute();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ExistingModTO>> obtainAllMods() {
        FindAllModsActivity activity = new FindAllModsActivity();
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createMod(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                          @RequestBody NewModTO mod) {
        CreateModActivity activity = new CreateModActivity(mod, token);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateMod(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                          @RequestBody ExistingModTO existingModTO) {
        UpdateModActivity activity = new UpdateModActivity(existingModTO, token);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMod(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                          @PathVariable Long id) {
        DeleteModActivity activity = new DeleteModActivity(id, token);
        return activity.execute();
    }
}
