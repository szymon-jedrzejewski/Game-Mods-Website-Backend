package com.gmw.api.rest;

import com.gmw.api.rest.activity.game.*;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.game.tos.NewGameTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @GetMapping("/findAll")
    public ResponseEntity<List<ExistingGameTO>> findAllGames() {
        FindAllGamesActivity activity = new FindAllGamesActivity();
        return activity.execute();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ExistingGameTO> findGameById(@PathVariable Long id) {
        FindGameByIdActivity activity = new FindGameByIdActivity(id);
        return activity.execute();
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<ExistingGameTO> findGameByName(@PathVariable String name) {
        FindGameByNameActivity activity = new FindGameByNameActivity(name);
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createGame(@RequestBody NewGameTO game) {
        CreateGameActivity activity = new CreateGameActivity(game);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateGame(@RequestBody ExistingGameTO existingGameTO) {
        UpdateGameActivity activity = new UpdateGameActivity(existingGameTO);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        DeleteGameActivity activity = new DeleteGameActivity(id);
        return activity.execute();
    }
}
