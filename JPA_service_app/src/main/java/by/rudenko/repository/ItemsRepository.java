package by.rudenko.repository;

import by.rudenko.models.Item;
import by.rudenko.models.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository{
List<Item> findByItemName(String itemName);
List<Item> findByOwner(Person owner);

}
