package ru.vagapov.userapi.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

/**
 * Данные по пользователю
 */
@Table
public class UserEntity {

    @Id
    private Long id;

    /**
     * Имя
     */
    @Column
    private String firstName;

    /**
     * Фамилия
     */
    @Column
    private String lastName;

    /**
     * День рождения
     */
    @Column
    private OffsetDateTime birthDate;

    /**
     * Место рождения
     */
    @Column
    private String birthPlace;

    /**
     * Место рождения
     */
    @Column
    private Byte age;

    public UserEntity(Long id,
                      String firstName,
                      String lastName,
                      OffsetDateTime birthDate,
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

    public OffsetDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(OffsetDateTime birthDate) {
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
