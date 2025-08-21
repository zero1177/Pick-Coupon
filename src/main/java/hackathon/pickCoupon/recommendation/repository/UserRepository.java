package hackathon.pickCoupon.recommendation.repository;

import hackathon.pickCoupon.recommendation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
