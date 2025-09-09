package hackathon.pickCoupon.recommendation.repository;

import hackathon.pickCoupon.recommendation.domain.Store;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class StoreRepository{
    private final EntityManager em;

    //매장 저장
    public void save(Store store) {
        em.persist(store);
    }

    //매장 하나 조회
    public Store findOne(Long id) {
        return em.find(Store.class, id);
    }

    //전체 매장 조회
    public List<Store> findAll() {
        return em.createQuery("select s from Store s", Store.class)
                .getResultList();
    }


}