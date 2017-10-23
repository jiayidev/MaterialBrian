package dev.brian.materialbrian.bean;

import java.io.Serializable;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at 2017/10/23
 * Description:
 */
public class ImageBean implements Serializable {
    private String small;
    private String medium;
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
