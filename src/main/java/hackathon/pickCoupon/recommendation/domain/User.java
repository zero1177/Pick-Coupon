package hackathon.pickCoupon.recommendation.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "users")
@Getter @Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "user")
    List<UserCoupon> userCoupons = new ArrayList<>();

    //편의 메서드
    public void addUserCoupon(UserCoupon userCoupon) {
        userCoupons.add(userCoupon);
        userCoupon.setUser(this);
    }

    public void removeUserCoupon(UserCoupon userCoupon) {
        userCoupons.remove(userCoupon);
        userCoupon.setUser(null);
    }

}
