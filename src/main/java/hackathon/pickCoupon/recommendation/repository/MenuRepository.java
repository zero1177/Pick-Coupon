package hackathon.pickCoupon.recommendation.repository;

import hackathon.pickCoupon.recommendation.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
