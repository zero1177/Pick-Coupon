package hackathon.pickCoupon.recommendation.repository;

import hackathon.pickCoupon.recommendation.domain.CategoryType;
import hackathon.pickCoupon.recommendation.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findTop3ByCategory(CategoryType category);
    Optional<Store> findByName(String name);
}