package hackathon.pickCoupon.recommendation.repository;

import hackathon.pickCoupon.recommendation.domain.UserCoupon;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCouponRepository {
    private final EntityManager em;

    public void save(UserCoupon userCoupon) {
        em.persist(userCoupon);
    }

    public UserCoupon findOne(Long userCouponId) {
        return em.find(UserCoupon.class, userCouponId);
    }

    public List<UserCoupon> findAll() {
        return em.createQuery("select u from UserCoupon u", UserCoupon.class)
                .getResultList();
    }
}
