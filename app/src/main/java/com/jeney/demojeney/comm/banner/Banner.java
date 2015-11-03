package com.jeney.demojeney.comm.banner;

/**
 * desc: 广告位
 * author: martino
 * email: zechuanzheng@anjuke.com
 * date: 15/7/16
 */
public class Banner {
    private String id;
    private String text;
    private String target_url;
    private int out_target;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOut_target() {
        return out_target;
    }

    public void setOut_target(int out_target) {
        this.out_target = out_target;
    }

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", target_url='" + target_url + '\'' +
                ", out_target=" + out_target +
                ", image='" + image + '\'' +
                '}';
    }
}