package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.dtos.DonorDTO;
import ro.disi.dtos.MenuDTO;
import ro.disi.services.DisadvantagedPersonService;
import ro.disi.services.DonorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/donor")
public class DonorController {

    private final DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }
    @GetMapping(value = "/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DONOR')")
    public ResponseEntity<DonorDTO> getDonorByUsername(@PathVariable("username") String username) {
        DonorDTO donorDTO = donorService.getDonorByUsername(username);
        return new ResponseEntity<>(donorDTO, HttpStatus.OK);
    }
}
