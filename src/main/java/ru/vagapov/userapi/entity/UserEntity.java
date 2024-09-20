package ru.vagapov.userapi.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.sql.Date;
import java.sql.SQLData;
import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * Данные по пользователю
 */
@Entity
@Table (name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;


    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "birthPlace")
    private String birthPlace;

    @Column(name = "age")
    private Byte age;

    public UserEntity(Long id,
                      String firstName,
                      String lastName,
                      LocalDate birthDate,
                      String birthPlace,
                      Byte age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.age = age;
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", age=" + age +
                '}';
    }
}