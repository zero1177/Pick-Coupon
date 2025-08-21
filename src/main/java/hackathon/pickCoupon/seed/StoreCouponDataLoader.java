package hackathon.pickCoupon.seed;

import com.fasterxml.jackson.databind.ObjectMapper;
import hackathon.pickCoupon.recommendation.domain.Coupon;
import hackathon.pickCoupon.recommendation.domain.Menu;
import hackathon.pickCoupon.recommendation.domain.Store;
import hackathon.pickCoupon.recommendation.repository.CouponRepository;
import hackathon.pickCoupon.recommendation.repository.MenuRepository;
import hackathon.pickCoupon.recommendation.repository.StoreRepository;
import hackathon.pickCoupon.seed.dto.CouponSeed;
import hackathon.pickCoupon.seed.dto.MenuSeed;
import hackathon.pickCoupon.seed.dto.StoreSeed;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class StoreCouponDataLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final StoreRepository storeRepository;
    private final CouponRepository couponRepository;
    private final MenuRepository menuRepository;

    @Override
    public void run(String... args) throws Exception {
        /*if (storeRepository.count() > 0) { // 이미 있으면 로딩 안 함
            return;
        }*/
        ClassPathResource resource = new ClassPathResource("data/storesWithCoupons.json");
        if (!resource.exists()) return; // 파일 없으면 종료

        List<StoreSeed> seeds = Arrays.asList(
                objectMapper.readValue(resource.getInputStream(), StoreSeed[].class)
        );

        menuRepository.deleteAll();
        couponRepository.deleteAll();
        storeRepository.deleteAll();

        // store 저장
        for (StoreSeed storeSeed : seeds) {
            Store store = storeRepository.findByName(storeSeed.getName()).orElse(null);

            if (store == null) {
                store = new Store();
                store.setName(storeSeed.getName());
                store.setCategory(storeSeed.getCategory());
                store.setImageURL(storeSeed.getImageURL());
                store.setAddress(storeSeed.getAddress());
                store.setRating(storeSeed.getRating());
                store.setHours(storeSeed.getHours());
                store.setPhone(storeSeed.getPhone());
            }

            store = storeRepository.save(store);

            // 메뉴 저장
            if (storeSeed.getMenu() != null) {
                for (MenuSeed menuSeed : storeSeed.getMenu()) {
                    Menu menu = new Menu();
                    menu.setName(menuSeed.getName());
                    menu.setMenuImage(menuSeed.getMenuImage());
                    menu.setStore(store);
                    menuRepository.save(menu);
                }
            }

            // coupon 저장
            if (storeSeed.getCoupon() != null) {
                if (couponRepository.findByStoreId(store.getId()).isEmpty()) {
                    CouponSeed couponSeed = storeSeed.getCoupon();

                    Coupon coupon = new Coupon();
                    coupon.setStore(store);
                    coupon.setTitle(couponSeed.getTitle());
                    couponRepository.save(coupon);
                }
            }
        }
    }
}