package hackathon.pickCoupon.recommendation.service;

import hackathon.pickCoupon.recommendation.domain.Coupon;
import hackathon.pickCoupon.recommendation.domain.Store;
import hackathon.pickCoupon.recommendation.domain.User;
import hackathon.pickCoupon.recommendation.domain.UserCoupon;
import hackathon.pickCoupon.recommendation.repository.CouponRepository;
import hackathon.pickCoupon.recommendation.repository.StoreRepository;
import hackathon.pickCoupon.recommendation.repository.UserCouponRepository;
import hackathon.pickCoupon.recommendation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponService {
    private final CouponRepository couponRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final UserCouponRepository userCouponRepository;

    //쿠폰 등록 (사장님이)
    public Long registerCoupon(Long storeId, String title, String description, int issueLimit) {
        Store store = storeRepository.findOne(storeId);

        Coupon newCoupon = Coupon.createCoupon(store,title,description,issueLimit);

        couponRepository.save(newCoupon);
        return newCoupon.getId();
    }

    //쿠폰 삭제 (사장님이)
    public void removeCoupon(Long couponId) {
        Coupon coupon = couponRepository.findOne(couponId);
        coupon.removeCoupon();
    }

    //사용자가 쿠폰을 다운로드 (다운로드 함에 저장됨)
    public void DownloadCoupon(Long couponId, Long userId) {
        Coupon coupon = couponRepository.findOne(couponId);
        User user = userRepository.findOne(userId);

        UserCoupon newUserCoupon = UserCoupon.createUserCoupon(coupon, user);
        userCouponRepository.save(newUserCoupon);
    }


    //사용자가 쿠폰 다운로드 함에서 쿠폰 삭제
    public void removeUserCoupon(Long userCouponId) {
        UserCoupon removedUserCoupon = userCouponRepository.findOne(userCouponId);
        removedUserCoupon.cancelDownloadCoupon();
    }


    //사장님들이 만든 쿠폰 조회 -> 나중에는 삭제하고 다른 방식을 찾아봐야됨!


}
