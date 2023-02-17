package dat3.car.entity;


import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//lombok
@Getter
@Setter
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
@Entity(name="member")
public class Member extends UserWithRoles {
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastEdited;

    @ElementCollection
    private List<String> favoriteCarColours = new ArrayList<>();


    @ElementCollection
    @MapKeyColumn(name = "Description")
    @Column(name = "phoneNumber")
    private Map<String,String> phones = new HashMap<>();


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "member")
    List<Reservation> reservations = new ArrayList<>();



    public Member(String user, String password, String email,
                  String firstName, String lastName, String street, String city, String zip) {
        super(user,password,email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;

    }



    public void addReservation(Reservation reservation) {
        if (reservations == null) {
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
        reservation.setMember(this);
    }



}
