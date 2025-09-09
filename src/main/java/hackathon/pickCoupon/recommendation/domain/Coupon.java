package hackathon.pickCoupon.recommendation.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Entity @Table(name="coupon")
@Getter @Setter
public class Coupon {
    @Id @GeneratedValue
    @Column(name = "coupon_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private int issueLimit;

    //== 비즈니스 로직 == //
    //쿠폰 생성
    public static Coupon createCoupon(Store store, String title, String description, int issueLimit) {
        Coupon coupon = new Coupon();

        coupon.setTitle(title);
        coupon.setDescription(description);
        coupon.setIssueLimit(issueLimit);

        store.addCoupon(coupon); //양방향 연관관계 설정
        return coupon;
    }

    //쿠폰 삭제
    public void removeCoupon() {

        getStore().removeCoupon(this);
    }

    //쿠폰 다운로드 -> 수량 관리
    public void removeIssueLimit() {
        int restCount = issueLimit - 1;
        if(restCount < 0) {
            throw new IllegalStateException("해당 쿠폰은 더 이상 발급할 수 없습니다.");
        } else {
            issueLimit = restCount;
        }
    }

    //쿠폰 다운로드 취소 -> 수량 복구
    public void addIssueLimit() {
        issueLimit += 1;
    }



}
