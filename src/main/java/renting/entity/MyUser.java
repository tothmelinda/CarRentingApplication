package renting.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(nullable = false, length = 30)
    private String fullName;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Car> cars;

    @OneToOne(cascade = CascadeType.ALL)
    private ContactDetails contactDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column
    private String randomToken;

    @Transient
    private String randomTokenEmail;

    @Transient
    private String passwordConfirm;

    public MyUser(MyUser user) {
        this.enabled = user.isEnabled();
        this.contactDetails = user.getContactDetails();
        this.roles = user.getRoles();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.id = user.getId();
        this.accountNonExpired = user.isAccountNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.cars = user.getCars();
        this.email = user.getEmail();}

    public MyUser(String username, String password, boolean accountNonExpired, boolean accountNonLocked,
                  boolean credentialsNonExpired, boolean enabled, String fullName, ContactDetails contactDetails,
                  String email, Set<Role> roles, Set<Car> cars, String passwordConfirm) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.fullName = fullName;
        this.contactDetails = contactDetails;
        this.email = email;
        this.roles = roles;
        this.cars = cars;
        this.passwordConfirm = passwordConfirm;}

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }



}
