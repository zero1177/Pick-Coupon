package hackathon.pickCoupon.recommendation.web;

import lombok.*;

import java.util.List;


@Getter @Setter @Builder
public class StoreCardsResponse {
    private Long id;
    private String name;
    private String image; //이미지 URL
    private Double rating;
    private String address;
    private String phone;
    private String hours;
    private String category;
    private String payment;   // 예: "서울페이 사용 가능"

    private int coupon_is;
    //쿠폰 존재하지 않으면 0, 존재하면 1 + coupon: '1,000원 할인 쿠폰', //쿠폰 존재하지 않으면 null, 존재하면 '무슨 쿠폰인지'

    private String coupon;    // 예: "1,000원 할인 쿠폰"



    private List<MenuItem> menu;

    @Data @Builder
    public static class MenuItem {
        private String name;
        private String menuImage;
    }

}
