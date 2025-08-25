package hackathon.pickCoupon.seed;

import com.fasterxml.jackson.databind.ObjectMapper;
import hackathon.pickCoupon.recommendation.domain.UserConsumptionLog;
import hackathon.pickCoupon.recommendation.repository.UserConsumptionLogRepository;
import hackathon.pickCoupon.seed.dto.UserConsumptionSeed;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsumptionDataLoader implements CommandLineRunner {
    private final ObjectMapper objectMapper;
    private final UserConsumptionLogRepository userConsumptionLogRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1) 클래스패스에서 읽기
        ClassPathResource resource = new ClassPathResource("data/userConsumption.json");
        if (!resource.exists()) return;

        List<UserConsumptionSeed> seeds = Arrays.asList(
                objectMapper.readValue(resource.getInputStream(), UserConsumptionSeed[].class)
        );

        userConsumptionLogRepository.deleteAll();;

           /* // 2) (선택) 중복 로딩 방지
            if (userConsumptionLogRepository.count() > 0) return;*/

            List<UserConsumptionLog> entities = new ArrayList<>();
            for (UserConsumptionSeed seed : seeds) {
                UserConsumptionLog log = new UserConsumptionLog();
                log.setUserId(seed.getUserId());
                log.setCategory(seed.getCategory());
                log.setDate(seed.getDate());
                entities.add(log);
            }

            userConsumptionLogRepository.saveAll(entities);
    }
}

