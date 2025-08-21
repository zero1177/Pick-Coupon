package hackathon.pickCoupon.recommendation.domain;

import lombok.Getter;

@Getter
public enum CategoryType {
    FLOWER("꽃/원예"),
    SNACK("분식"),
    LEISURE("레저/오락시설"),
    BAKERY("베이커리/제과점"),
    BEAUTY("뷰티/헤어"),
    BOOK("서점/문구점"),
    WESTERN("양식"),
    EXPERIENCE("원데이클래스/체험"),
    SHOW("전시/공연"),
    DRINK("주점/바"),
    CHINESE("중식"),
    CAFE("카페"),
    FASHION("패션/액세서리"),
    KOREAN("한식");

    private final String korean;

    CategoryType(String korean) {
        this.korean = korean;
    }
}

