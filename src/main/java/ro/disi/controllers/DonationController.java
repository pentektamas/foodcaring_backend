package ro.disi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.disi.dtos.DonationDTO;
import ro.disi.services.DonationService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/donation")
public class DonationController {
    private final DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<List<DonationDTO>> getDonations() {
        List<DonationDTO> donationDTOS = donationService.findDonations();
        return new ResponseEntity<>(donationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<DonationDTO> getDonation(@PathVariable("id") UUID id) {
        DonationDTO donationDTO = donationService.findDonationById(id);
        return new ResponseEntity<DonationDTO>(donationDTO, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> insertDonation(@Valid @RequestBody DonationDTO donationDTO) {
        System.out.println(donationDTO);
        UUID uuid = donationService.insertDonation(donationDTO);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<DonationDTO> updateDonation(@Valid @RequestBody DonationDTO donationDTO) {
        DonationDTO updatedDonationDTO = donationService.updateDonation(donationDTO);
        return new ResponseEntity<>(updatedDonationDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE')")
    public ResponseEntity<UUID> deleteDonation(@PathVariable UUID id) {
        UUID uuid = donationService.deleteDonation(id);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }
}
