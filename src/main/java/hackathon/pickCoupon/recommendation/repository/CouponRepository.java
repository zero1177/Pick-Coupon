package hackathon.pickCoupon.recommendation.repository;

import hackathon.pickCoupon.recommendation.domain.Coupon;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponRepository {
    private final EntityManager em;

    //쿠폰 저장
    public void save(Coupon coupon) {
        em.persist(coupon);
    }

    //쿠폰 하나 조회
    public Coupon findOne(Long id) {
        return em.find(Coupon.class, id);
    }

    //전체 쿠폰 조회
    public List<Coupon> findAll() {
        return em.createQuery("select c from Coupon c", Coupon.class)
                .getResultList();
    }

}
