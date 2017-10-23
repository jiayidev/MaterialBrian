package dev.brian.materialbrian.utils;


import dev.brian.materialbrian.common.Constant;
import dev.brian.materialbrian.common.URL;
import dev.brian.materialbrian.utils.common.SPUtils;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at 2017/10/23
 * Description:
 */

public class EBookUtils {
    public static String getHotRankingId(@Constant.Gender String gender) {
        if (gender.equals(Constant.Gender.MALE)) {
            return Constant.EBOOK_RANK_ID_HOT_MALE;
        } else {
            return Constant.EBOOK_RANK_ID_HOT_FEMALE;
        }
    }

    public static String getRetainedRankingId(@Constant.Gender String gender) {
        if (gender.equals(Constant.Gender.MALE)) {
            return Constant.EBOOK_RANK_ID_RETAINED_MALE;
        } else {
            return Constant.EBOOK_RANK_ID_RETAINED_FEMALE;
        }
    }

    public static String getFinishedRankingId(@Constant.Gender String gender) {
        if (gender.equals(Constant.Gender.MALE)) {
            return Constant.EBOOK_RANK_ID_FINISHED_MALE;
        } else {
            return Constant.EBOOK_RANK_ID_FINISHED_FEMALE;
        }
    }

    public static String getPotentialRankingId(@Constant.Gender String gender) {
        if (gender.equals(Constant.Gender.MALE)) {
            return Constant.EBOOK_RANK_ID_POTENTIAL_MALE;
        } else {
            return Constant.EBOOK_RANK_ID_POTENTIAL_FEMALE;
        }
    }

    public static String getImageUrl(String name) {
        return URL.HOST_URL_ZSSQ_IMG + name;
    }

    public static String getGender() {
        return SPUtils.getPrefString(Constant.USER_GENDER, Constant.Gender.MALE);
    }

    public static void setGender(String gender) {
        SPUtils.setPrefString(Constant.USER_GENDER, gender);
    }

    //通过列表以及性别获取id，然后拉取书列表
    public static String getRankId(int type, String gender) {
        switch (type) {
            case Constant.TYPE_HOT_RANKING:
                return getHotRankingId(gender);
            case Constant.TYPE_RETAINED_RANKING:
                return getRetainedRankingId(gender);
            case Constant.TYPE_FINISHED_RANKING:
                return getFinishedRankingId(gender);
            case Constant.TYPE_POTENTIAL_RANKING:
                return getPotentialRankingId(gender);
            default:
                return getHotRankingId(gender);
        }
    }
}
