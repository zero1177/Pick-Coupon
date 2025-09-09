package hackathon.pickCoupon.recommendation.service;

import hackathon.pickCoupon.recommendation.domain.CategoryType;
import hackathon.pickCoupon.recommendation.domain.Menu;
import hackathon.pickCoupon.recommendation.domain.Store;
import hackathon.pickCoupon.recommendation.repository.MenuRepository;
import hackathon.pickCoupon.recommendation.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    //매장 등록
    public Long registerStore(String name, String storeImage, CategoryType category,
                              String address, Double rating, String hours, String phone) {
        Store store = new Store();
        store.setName(name);
        store.setStoreImage(storeImage);
        store.setCategory(category);
        store.setAddress(address);
        store.setRating(rating);
        store.setHours(hours);
        store.setPhone(phone);
        storeRepository.save(store);
        return store.getId();
    }

    //매장 삭제
    public void removeStore(Long storeId) {
        Store store = storeRepository.findOne(storeId);

    }

    //메뉴 추가
    public Long addMenu(Long storeId, String name, String menuImage) {
        Menu menu = new Menu();
        menu.setName(name);
        menu.setMenuImage(menuImage);

        Store store = storeRepository.findOne(storeId);
        store.addMenu(menu); //Menu에 store 설정
        menuRepository.save(menu);
        return menu.getId();
    }

    //메뉴 삭제
    public void removeMenu(Long menuId) {
        Menu menu = menuRepository.findOne(menuId); //예외??
        menu.getStore().removeMenu(menu);
    }
}
