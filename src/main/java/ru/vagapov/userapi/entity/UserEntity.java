package ru.vagapov.userapi.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Данные по пользователю
 */
@DynamicUpdate
@Table(name = "USERS")
@EqualsAndHashCode
@Entity
public class UserEntity{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * Версия
     */
    @Version
    private Short version;

    /**
     * GUID пользователя
     */
    @Column(name = "user_guid")
    private UUID userGuid;

    /**
     * Дата создания пользователя
     */
    @Column(name = "create_date")
    private OffsetDateTime createDate;

    /**
     * Последнее изменение пользователя
     */
    @Column(name = "change_date")
    private OffsetDateTime changeDate;

    /**
     * Признак удаленного пользователя
     */
    @Column(name = "deleted")
    private Boolean deleted = false;

    /**
     * Имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * ФИО
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * День рождения
     */
    @Column(name = "birth_date")
    private OffsetDateTime birthDate;

    /**
     * Место рождения
     */
    @Column(name = "birth_place")
    private String birthPlace;

    /**
     * Пол
     */
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private SexEntity sex;

    /**
     * Буквенный код страны гражданство
     */
    @Column(name = "citizenship_code")
    private String citizenshipCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public UUID getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }

    public OffsetDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(OffsetDateTime createDate) {
        this.createDate = createDate;
    }

    public OffsetDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(OffsetDateTime changeDate) {
        this.changeDate = changeDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public SexEntity getSex() {
        return sex;
    }

    public void setSex(SexEntity sex) {
        this.sex = sex;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", version=" + version +
                ", userGuid=" + userGuid +
                ", createDate=" + createDate +
                ", changeDate=" + changeDate +
                ", deleted=" + deleted +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", sex=" + sex +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                '}';
    }
}
