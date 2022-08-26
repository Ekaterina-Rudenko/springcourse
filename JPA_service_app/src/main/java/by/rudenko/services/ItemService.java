package by.rudenko.services;

import by.rudenko.models.Item;
import by.rudenko.models.Person;
import by.rudenko.repository.ItemsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ItemService {

  private final ItemsRepository itemsRepository;

  @Autowired
  public ItemService(ItemsRepository itemsRepository) {
    this.itemsRepository = itemsRepository;
  }

  public List<Item> findByItemName(String itemName){
    return itemsRepository.findByItemName(itemName);
  }

  public List<Item> findByOwner(Person owner){
    return itemsRepository.findByOwner(owner);
  }



}
