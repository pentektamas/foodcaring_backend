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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DISADVANTAGED_PERSON', 'ROLE_DONOR')")
    public ResponseEntity<List<DonationDTO>> getDonations() {
        List<DonationDTO> donationDTOS = donationService.findDonations();
        return new ResponseEntity<>(donationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/disadvantaged/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DISADVANTAGED_PERSON')")
    public ResponseEntity<List<DonationDTO>> getDonationsForDisadvantaged(@PathVariable("username") String username) {
        List<DonationDTO> donations = donationService.findDonationsByDisadvantagedPerson(username);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @GetMapping(value = "/donor/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DONOR')")
    public ResponseEntity<List<DonationDTO>> getDonationsFromDonor(@PathVariable("username") String username) {
        List<DonationDTO> donations = donationService.findDonationsByDonor(username);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DISADVANTAGED_PERSON', 'ROLE_DONOR')")
    public ResponseEntity<DonationDTO> getDonation(@PathVariable("id") UUID id) {
        DonationDTO donationDTO = donationService.findDonationById(id);
        return new ResponseEntity<DonationDTO>(donationDTO, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DONOR')")
    public ResponseEntity<UUID> insertDonation(@Valid @RequestBody DonationDTO donationDTO) {
        UUID uuid = donationService.insertDonation(donationDTO);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESTAURANT_RESPONSIBLE', 'ROLE_DONOR')")
    public ResponseEntity<DonationDTO> updateDonation(@Valid @RequestBody DonationDTO donationDTO) {
        DonationDTO updatedDonationDTO = donationService.updateDonation(donationDTO);
        return new ResponseEntity<>(updatedDonationDTO, HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{username}/{donationId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DISADVANTAGED_PERSON', 'ROLE_DONOR')")
    public ResponseEntity<DonationDTO> cancelDonation(@PathVariable("username") String username, @PathVariable("donationId") UUID donationId) {
        DonationDTO updatedDonationDTO = donationService.cancelDonation(username, donationId);
        return new ResponseEntity<>(updatedDonationDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DONOR')")
    public ResponseEntity<UUID> deleteDonation(@PathVariable UUID id) {
        UUID uuid = donationService.deleteDonation(id);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }
}
