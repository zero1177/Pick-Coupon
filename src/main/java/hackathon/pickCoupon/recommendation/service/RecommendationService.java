package hackathon.pickCoupon.recommendation.service;

import hackathon.pickCoupon.recommendation.domain.CategoryType;
import hackathon.pickCoupon.recommendation.domain.Coupon;
import hackathon.pickCoupon.recommendation.domain.Menu;
import hackathon.pickCoupon.recommendation.domain.Store;
import hackathon.pickCoupon.recommendation.repository.CouponRepository;
import hackathon.pickCoupon.recommendation.repository.StoreRepository;
import hackathon.pickCoupon.recommendation.web.RecommendationResult;
import hackathon.pickCoupon.recommendation.web.StoreCardsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final ConsumptionAnalysisService consumptionAnalysisService;
    private final GPTService gptService;
    private final StoreRepository storeRepository;
    private final CouponRepository couponRepository;


    public RecommendationResult getRecommendations(Long userId) {
        List<CategoryType> topCategories = consumptionAnalysisService.getTop3Categories(userId);

        if (topCategories.isEmpty()) {
            throw new IllegalStateException("사용자의 소비 로그가 없습니다.");
        }

        // 1. 사용자의 소비 로그 기반 상위 1,2등 카테고리
        CategoryType top1 = topCategories.get(0);
        CategoryType top2 = topCategories.get(1);

        // 2. 카테고리에 맞는 추천 멘트 가져오기
        String recommendMessage = gptService.makeRecommendationMessage(top1, top2);

        // 3. 상위 1등, 상위 2등 기준 3곳씩 추천
        List<Store> storesTop1 = storeRepository.findTop3ByCategory(top1);
        List<Store> storesTop2 = storeRepository.findTop3ByCategory(top2);

        //아래 toCard 메서드 이용해서 6개 response 프론트에 보내기
        List<StoreCardsResponse> responses = new ArrayList<>();
        for (Store s : storesTop1) {
            responses.add(toCard(s));
        }
        for (Store s : storesTop2) {
            responses.add(toCard(s));
        }

        return new RecommendationResult(recommendMessage, responses);
    }

    private StoreCardsResponse toCard(Store store) {
        Optional<Coupon> couponOptional= couponRepository.findByStoreId(store.getId());  // 쿠폰 1개 조회
        int isCoupon = 0;
        String couponName = null;

        if (couponOptional.isPresent()) {
            Coupon coupon = couponOptional.get();
            if (coupon.getTitle() != null) {
                isCoupon = 1;
                couponName = coupon.getTitle();
            }
        }

        Double rating = (store.getRating() != null) ? store.getRating() : null; //별점 있으면 넣고, 없으면 null
        String paymentName = "서울 페이"; //지금은 서울페이밖에 없음

        // 메뉴 리스트 변환
        List<StoreCardsResponse.MenuItem> menuItems = new ArrayList<>();
        if (store.getMenuList() != null) {
            for (Menu menu : store.getMenuList()) {
                menuItems.add(StoreCardsResponse.MenuItem.builder()
                        .name(menu.getName())
                        .menuImage(menu.getMenuImage())
                        .build());
            }
        }

        return StoreCardsResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .image(store.getImageURL())
                .rating(rating)
                .address(store.getAddress())
                .phone(store.getPhone())
                .hours(store.getHours())
                .category(store.getCategory().getKorean())
                .payment(paymentName)
                .coupon_is(isCoupon)
                .coupon(couponName)
                .menu(menuItems)
                .build();
    }

}
