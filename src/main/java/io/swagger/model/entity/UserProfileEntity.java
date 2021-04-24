package io.swagger.model.entity;

import io.swagger.model.viewobjects.User;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static io.swagger.model.viewobjects.User.RoleEnum;

@Entity
@Table(name = "user_profile", schema = "public", catalog = "postgres")
public class UserProfileEntity {
    private Long id;
    private RoleEnum role;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Long age;
    private String phone;
    private String tag;
    private Set<UserProfileEntity> reportee = new HashSet<>();
    private UserProfileEntity manager;

    private UserCredentialsEntity userCredential;

    public User mapToUser(){
        final var user = new User();
        user.setAddress(this.address);
        user.setAge(this.age);
        user.setEmail(this.email);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setManagerName(this.manager!=null?this.manager.email:null);
        user.setRole(this.role);
        user.setTag(this.tag);
        return user;
    }

    public void addUserCredential(UserCredentialsEntity userCredential){
        this.userCredential = userCredential;
        userCredential.setUserProfileEntity(this);
    }

       @OneToOne(mappedBy = "userProfileEntity",cascade = CascadeType.ALL)
    public UserCredentialsEntity getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredentialsEntity userCredential) {
        this.userCredential = userCredential;
    }

    public void addReportee(UserProfileEntity newReportee){
        reportee.add(newReportee);
        newReportee.setManager(this);
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", unique = true)
    @NaturalId(mutable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "age")
    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @OneToMany
    @JoinTable(
            name = "user_manager",
            joinColumns = @JoinColumn(
                    name = "manager_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )
    )
    public Set<UserProfileEntity> getReportee() {
        return reportee;
    }

    public void setReportee(Set<UserProfileEntity> reportee) {
        this.reportee = reportee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_manager",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "manager_id",
                    referencedColumnName = "id"
            )
    )
    public UserProfileEntity getManager() {
        return manager;
    }

    public void setManager(UserProfileEntity manager) {
        this.manager = manager;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfileEntity that = (UserProfileEntity) o;

        if (id != that.id) return false;
        if (age != that.age) return false;
        if (!Objects.equals(role, that.role)) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(phone, that.phone)) return false;
        if (!Objects.equals(tag, that.tag)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result.intValue();
    }
}
