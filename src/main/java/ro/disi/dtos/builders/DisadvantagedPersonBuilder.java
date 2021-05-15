package ro.disi.dtos.builders;

import ro.disi.dtos.DisadvantagedPersonDTO;
import ro.disi.entities.Account;
import ro.disi.entities.DisadvantagedPerson;

import java.util.HashSet;
import java.util.stream.Collectors;

public class DisadvantagedPersonBuilder {


    private DisadvantagedPersonBuilder() {
    }

    public static DisadvantagedPerson toEntityWithId(DisadvantagedPersonDTO disadvantagedPersonDTO) {
        Account accountDisadvantagePerson = new Account(disadvantagedPersonDTO.getUsername(), disadvantagedPersonDTO.getPassword(), disadvantagedPersonDTO.getRole());
        DisadvantagedPerson disadvantagedPerson = new DisadvantagedPerson(
                disadvantagedPersonDTO.getId(),
                disadvantagedPersonDTO.getFirstName(),
                disadvantagedPersonDTO.getLastName(),
                disadvantagedPersonDTO.getLocation(),
                disadvantagedPersonDTO.getPhoneNumber(),
                accountDisadvantagePerson,
                disadvantagedPersonDTO.getPriority(),
                disadvantagedPersonDTO.getAllergies(),
                new HashSet<>(),
                disadvantagedPersonDTO.getNrOfHelps());
        if (disadvantagedPersonDTO.getWishList() != null)
            disadvantagedPerson.setWishList(disadvantagedPersonDTO.getWishList().stream().map(MenuBuilder::toEntityWithId).collect(Collectors.toSet()));
        return disadvantagedPerson;
    }

    public static DisadvantagedPerson toEntity(DisadvantagedPersonDTO disadvantagedPersonDTO) {
        Account accountDisadvantagePerson = new Account(disadvantagedPersonDTO.getUsername(), disadvantagedPersonDTO.getPassword(), disadvantagedPersonDTO.getRole());
        DisadvantagedPerson disadvantagedPerson = new DisadvantagedPerson(
                disadvantagedPersonDTO.getFirstName(),
                disadvantagedPersonDTO.getLastName(),
                disadvantagedPersonDTO.getLocation(),
                disadvantagedPersonDTO.getPhoneNumber(),
                accountDisadvantagePerson,
                disadvantagedPersonDTO.getPriority(),
                disadvantagedPersonDTO.getAllergies(),
                new HashSet<>(),
                disadvantagedPersonDTO.getNrOfHelps());
        if (disadvantagedPersonDTO.getWishList() != null)
            disadvantagedPerson.setWishList(disadvantagedPersonDTO.getWishList().stream().map(MenuBuilder::toEntityWithId).collect(Collectors.toSet()));
        return disadvantagedPerson;
    }

    public static DisadvantagedPersonDTO toDisadvantagedPersonDTO(DisadvantagedPerson disadvantagedPerson) {
        DisadvantagedPersonDTO disadvantagedPersonDTO = new DisadvantagedPersonDTO(disadvantagedPerson.getId(),
                disadvantagedPerson.getFirstName(),
                disadvantagedPerson.getLastName(),
                disadvantagedPerson.getLocation(),
                disadvantagedPerson.getPhoneNumber(),
                disadvantagedPerson.getAccount().getUsername(),
                disadvantagedPerson.getAccount().getPassword(),
                disadvantagedPerson.getAccount().getRole(),
                disadvantagedPerson.getAllergies(),
                new HashSet<>(),
                disadvantagedPerson.getNrOfHelps());
        if (disadvantagedPerson.getWishList() != null)
            disadvantagedPersonDTO.setWishList(disadvantagedPerson.getWishList().stream().map(MenuBuilder::toMenuDTO).collect(Collectors.toSet()));
        return disadvantagedPersonDTO;
    }

    public static DisadvantagedPersonDTO toDisadvantagedPersonDtoWithPriority(DisadvantagedPerson disadvantagedPerson) {
        DisadvantagedPersonDTO disadvantagedPersonDTO = new DisadvantagedPersonDTO(disadvantagedPerson.getId(),
                disadvantagedPerson.getFirstName(),
                disadvantagedPerson.getLastName(),
                disadvantagedPerson.getLocation(),
                disadvantagedPerson.getPhoneNumber(),
                disadvantagedPerson.getAccount().getUsername(),
                disadvantagedPerson.getAccount().getPassword(),
                disadvantagedPerson.getAccount().getRole(),
                disadvantagedPerson.getPriority(),
                disadvantagedPerson.getAllergies(),
                new HashSet<>(),
                disadvantagedPerson.getNrOfHelps());
        if (disadvantagedPerson.getWishList() != null)
            disadvantagedPersonDTO.setWishList(disadvantagedPerson.getWishList().stream().map(MenuBuilder::toMenuDTO).collect(Collectors.toSet()));
        return disadvantagedPersonDTO;
    }
}
