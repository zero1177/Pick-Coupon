package hackathon.pickCoupon.seed.dto;

import hackathon.pickCoupon.recommendation.domain.CategoryType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserConsumptionSeed {
    private Long userId;
    private CategoryType category;
    private String date; //"2025-08-01"
}
