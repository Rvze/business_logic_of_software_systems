package com.mpanchuk.app.controller;

import com.mpanchuk.app.domain.response.StashResponse;
import com.mpanchuk.app.model.Item;
import com.mpanchuk.app.domain.StashPair;
import com.mpanchuk.app.model.User;
import com.mpanchuk.app.service.StashService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stash")
public class StashController implements SecuredRestController{
    private final StashService stashService ;

    @Autowired
    public StashController(StashService stashService) {
        this.stashService = stashService ;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            return ResponseEntity.status(HttpStatus.OK).body(username);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(auth.getPrincipal().toString());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<StashResponse>> getStash() {

        var arr = stashService.getStash(getUsername());
        if (arr == null) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(arr, HttpStatus.OK) ;
    }

    @PutMapping("/add")
    public ResponseEntity<List<StashResponse>> addItem(@RequestParam int itemId, @RequestParam(defaultValue = "1") int amount) {
        var arr = stashService.addItem(getUsername(), (long) itemId, amount);
        return new ResponseEntity<>(arr, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<StashResponse>> deleteItem(@RequestParam int itemId, @RequestParam(defaultValue = "1") int amount) {
        var arr = stashService.deleteItem(getUsername(), (long) itemId, amount);
        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

    private String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return "" ;
    }
}
