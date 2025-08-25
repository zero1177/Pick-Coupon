package hackathon.pickCoupon.recommendation;

import hackathon.pickCoupon.recommendation.service.RecommendationService;
import hackathon.pickCoupon.recommendation.web.RecommendationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController// JSON 직렬화 응답
@RequiredArgsConstructor
@CrossOrigin(
        origins = {
                "http://localhost:5175",
                "https://pick-coupon.com"
        },   //프론트 오리진
        allowedHeaders = "*",
        allowCredentials = "true"           //쿠키/세션 필요시
)
public class RecommendationController {
    private final RecommendationService recommendationService;


    @GetMapping("/api/recommendations") //"/api/recommendations?userId=1"로 프론트에서 Get 요청
    public RecommendationResult getRecommendations(@RequestParam Long userId) {
        return recommendationService.getRecommendations(userId);
    }

    @GetMapping("/")
    public String home() {
        return "App is running!";
    }
}
