package hackathon.pickCoupon.recommendation.repository;

import hackathon.pickCoupon.recommendation.domain.UserConsumptionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConsumptionLogRepository extends JpaRepository<UserConsumptionLog, Long> {
    List<UserConsumptionLog> findByUserId(Long userId);
}
