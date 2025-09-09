package hackathon.pickCoupon.recommendation.repository;

import hackathon.pickCoupon.recommendation.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository{
    private final EntityManager em;

    //유저 저장
    public void save(User user) {
        em.persist(user);
    }

    //유저 한명 조회
    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    //유저 전체 조회
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

}
