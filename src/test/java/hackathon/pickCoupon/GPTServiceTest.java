package hackathon.pickCoupon;

import hackathon.pickCoupon.recommendation.domain.CategoryType;
import hackathon.pickCoupon.recommendation.service.GPTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


public class GPTServiceTest {

    @Test
    void makeRecommendationMessage() {
        // given: 더미 카테고리 2개
        String apiKey = "www";
        String baseUrl = "https://api.openai.com/v1";

        GPTService gptService = new GPTService(apiKey,baseUrl);

        CategoryType top1 = CategoryType.CAFE;
        CategoryType top2 = CategoryType.KOREAN;

        // when: GPT 호출
        String result = gptService.makeRecommendationMessage(top1, top2);

        // then: 결과 확인 (콘솔 출력)
        System.out.println("GPT 응답 결과: " + result);

        // 간단한 검증: null/빈 문자열 아니어야 함
        assertThat(result).isNotBlank();
        // 필요하면 길이, 이모지, 해시태그 여부 등 추가 검증
        assertThat(result.length()).isLessThanOrEqualTo(80);
        assertThat(result).doesNotContain("#");
    }
}
