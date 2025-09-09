package hackathon.pickCoupon.recommendation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class UserCoupon {
    @Id @GeneratedValue
    @Column(name = "user_coupon_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime downloadedAt;

    // == 비즈니스 로직 ==

    //사용자가 쿠폰 다운로드
    public static UserCoupon createUserCoupon(Coupon coupon, User user) { //하나의 쿠폰은 한번만 다운로드 가능하도록 설정
        UserCoupon userCoupon = new UserCoupon();

        userCoupon.setCoupon(coupon);
        userCoupon.setUser(user);
        userCoupon.setDownloadedAt(LocalDateTime.now());

        //UserCoupon & User 연관관계
        user.addUserCoupon(userCoupon);

        //발급 수량 차감
        coupon.removeIssueLimit();

        return userCoupon;
    }

    //사용자가 다운로드 한 쿠폰 삭제
    public void cancelDownloadCoupon() {
        getCoupon().addIssueLimit();

        //UserCoupon & User 연관관계
        getUser().removeUserCoupon(this);
    }


}
