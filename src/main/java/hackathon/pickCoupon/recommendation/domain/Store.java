package hackathon.pickCoupon.recommendation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "store")
@Getter @Setter
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String name;

    private String imageURL;

    @Enumerated(EnumType.STRING)   // Enum을 DB에 문자열로 저장
    @Column(nullable = false, length = 50)
    private CategoryType category;

    private String address;
    private Double rating;
    private String hours;
    private String phone;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menuList = new ArrayList<>();



}
