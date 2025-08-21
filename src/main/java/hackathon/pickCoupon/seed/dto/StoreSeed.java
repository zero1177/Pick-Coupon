package hackathon.pickCoupon.seed.dto;

import hackathon.pickCoupon.recommendation.domain.CategoryType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class StoreSeed {
    private Long id;
    private String name;
    private CategoryType category;
    private String imageURL;
    private String address;
    private Double rating;
    private String hours;
    private String phone;
    private String payment;

    private CouponSeed coupon;
    private List<MenuSeed> menu;
}
