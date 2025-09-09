package hackathon.pickCoupon.recommendation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "store")
@Getter @Setter
public class Store {
    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String storeImage;

    @Enumerated(EnumType.STRING)   // Enum을 DB에 문자열로 저장
    @Column(nullable = false)
    private CategoryType category;

    private String address;
    private Double rating;
    private String hours;
    private String phone;

    @OneToMany(mappedBy = "store")
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Coupon> couponList = new ArrayList<>();

    //편의 메서드
    public void addMenu(Menu menu) {
        menuList.add(menu);
        menu.setStore(this);
    }

    public void removeMenu(Menu menu) {
        menuList.remove(menu);
        menu.setStore(null);
    }

    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
        coupon.setStore(this);
    }

    public void removeCoupon(Coupon coupon) {
        couponList.remove(coupon);
        coupon.setStore(null);
    }

}
