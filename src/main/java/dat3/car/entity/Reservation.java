package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Reservation {


    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime reservationDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime rentalDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    Member member;

    @ManyToOne
    Car car;


}
