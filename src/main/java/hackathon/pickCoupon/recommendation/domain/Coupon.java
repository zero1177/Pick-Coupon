package hackathon.pickCoupon.recommendation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter @Setter
@Entity @Table(name="Coupon")

public class Coupon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "store_id", nullable = false, unique = true)
    private Store store;

    @Column(length = 180)
    private String title;

}
