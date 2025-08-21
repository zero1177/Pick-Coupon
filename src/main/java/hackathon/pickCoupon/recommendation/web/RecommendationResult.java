package hackathon.pickCoupon.recommendation.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class RecommendationResult {
    private String message;  // GPT 추천 멘트
    private List<StoreCardsResponse> stores;  // 추천 매장 카드 6개
}
