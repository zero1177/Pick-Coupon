package hackathon.pickCoupon.recommendation.service;

import hackathon.pickCoupon.recommendation.domain.CategoryType;
import hackathon.pickCoupon.recommendation.domain.UserConsumptionLog;
import hackathon.pickCoupon.recommendation.repository.UserConsumptionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsumptionAnalysisService {

    private final UserConsumptionLogRepository userConsumptionLogRepository;

    public List<CategoryType> getTop3Categories(Long userId) { //상위 3개 카테고리 찾고 반환
        List<UserConsumptionLog> logs = userConsumptionLogRepository.findByUserId(userId);

        //1. 카테고리명의 방문횟수 각각 집계
        Map<CategoryType, Integer> CategoryCounter = new HashMap<>();
        for (UserConsumptionLog log : logs) {
            CategoryType category = log.getCategory();
            CategoryCounter.put(category, CategoryCounter.getOrDefault(category, 0) + 1);
        }

        //2. 가장 많이 방문한 category 1,2,3등 추출
        List<Map.Entry<CategoryType, Integer>> sortedList = new ArrayList<>(CategoryCounter.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<CategoryType> result = new ArrayList<>();
        for (int i = 0; i < Math.min(3, sortedList.size()); i++) {
            CategoryType categoryName = sortedList.get(i).getKey();
            result.add(categoryName);
        }
        return result;
    }
}
