package hackathon.pickCoupon.recommendation.service;

import hackathon.pickCoupon.recommendation.domain.CategoryType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class GPTService {

    private final WebClient webClient;

    public GPTService(
            @Value("${openai.api-key}") String apiKey,
            @Value("${openai.base-url:https://api.openai.com/v1}") String baseUrl
    ) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String makeRecommendationMessage(CategoryType top1, CategoryType top2) {
        String systemPrompt =
               "우리 서비스는 사용자의 소비 패턴을 분석한 결과로 상위 2개 카테고리를 추출할거야. " +
               "그 상위 2개 카테고리를 잘 어우르는 추천 멘트를, 친절하고 친근한 말투로 한 문장(최대 80자)으로 만들어야 돼." +
               "중복 수식어를 피하고 계절/시간대/감정/날씨 등 표현을 위주로 사용해. 해시태그는 넣지 말고 이모지는 하나 정도 간단한건 괜찮아.";

        String userPrompt = String.format(
                "상위 카테고리: 1) %s  2) %s\n" +
                "형식: 한 문장만 출력. 예시는: \"카페에서 잠깐 쉬고, 레저로 주말의 활력을 채워보세요.\"",
                top1.getKorean(), top2.getKorean()
        );

        Map<String, Object> payload = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", userPrompt)
                ),
                "temperature", 0.7
        );

        // 호출
        Map response = this.webClient.post()
                .uri("/chat/completions")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorResume(e -> Mono.empty())
                .block();

        // 파싱 (choices[0].message.content)
        if (response == null) return null;
        try {
            List choices = (List) response.get("choices");
            if (choices == null || choices.isEmpty()) return null;
            Map first = (Map) choices.get(0);
            Map message = (Map) first.get("message");
            return (String) message.get("content");
        } catch (Exception ignored) {
            return null;
        }
    }
}
