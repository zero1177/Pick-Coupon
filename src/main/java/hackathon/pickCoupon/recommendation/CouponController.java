package hackathon.pickCoupon.recommendation;

import hackathon.pickCoupon.recommendation.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor //final에만 사용 가능, 롬복 기능
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/coupon")
    public download
}
