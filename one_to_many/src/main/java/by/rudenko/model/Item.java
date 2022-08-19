package by.rudenko.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "item_name")
  private String itemName;

  @ManyToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person owner;

  public Item() {
  }

  public Item(String itemName, Person owner) {
    this.itemName = itemName;
    this.owner = owner;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Person getOwner() {
    return owner;
  }

  public void setOwner(Person owner) {
    this.owner = owner;
  }

  @Override
  public String toString() {
    return "Item{" +
        "id=" + id +
        ", itemName='" + itemName + '\'' +
        '}';
  }
}
