package dat3.car.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="car_brand",length = 50, nullable = false)
    private String brand;
    @Column(name="car_model",length = 60, nullable = false)
    private String model;
    @Column(name="rental_price_day")
    private double pricePrDay;

    @Column(name = "max_discount")
    private int bestDiscount;


    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime lastEditedDate;


    public Car(String brand, String model, double pricePrDay) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
    }
}
