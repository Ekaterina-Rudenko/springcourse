package by.rudenko.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Passport")
public class Passport implements Serializable {

  @Id
  @OneToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person person;

  @Column(name = "passport_number")
  private int passportNumber;

  public Passport() {
  }

  public Passport(int passportNumber) {
    this.passportNumber = passportNumber;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public int getPassportNumber() {
    return passportNumber;
  }

  public void setPassportNumber(int passportNumber) {
    this.passportNumber = passportNumber;
  }

  @Override
  public String toString() {
    return "Passport{" +
        "person=" + person +
        ", passportNumber=" + passportNumber +
        '}';
  }
}
