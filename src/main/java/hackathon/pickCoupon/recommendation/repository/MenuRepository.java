package hackathon.pickCoupon.recommendation.repository;


import hackathon.pickCoupon.recommendation.domain.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository {

    @PersistenceContext
    private EntityManager em;

    //메뉴 저장
    public void save(Menu menu) {
        em.persist(menu);
    }

    //메뉴 하나 조회
    public Menu findOne(Long id) {
        return em.find(Menu.class, id);
    }

    //전체 메뉴 조회
    public List<Menu> findAll() {
        return em.createQuery("select m from Menu m", Menu.class)
                .getResultList();
    }


}
