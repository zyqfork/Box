package com.github.tvbox.osc.util;

import android.app.Activity;

import com.github.tvbox.osc.base.App;
import com.github.tvbox.osc.subtitle.widget.SimpleSubtitleView;
import com.orhanobut.hawk.Hawk;

public class SubtitleHelper {

    public static int getSubtitleTextAutoSize(Activity activity) {
        double screenSqrt = ScreenUtils.getSqrt(activity);
        int subtitleTextSize = 20;
        if (screenSqrt > 7.0 && screenSqrt <= 13.0) {
            subtitleTextSize = 24;
        } else if (screenSqrt > 13.0 && screenSqrt <= 50.0) {
            subtitleTextSize = 36;
        } else if (screenSqrt > 50.0) {
            subtitleTextSize = 46;
        }
        return subtitleTextSize;
    }

    public static int getTextSize(Activity activity) {
        int autoSize = getSubtitleTextAutoSize(activity);
        int subtitleConfigSize = Hawk.get(HawkConfig.SUBTITLE_TEXT_SIZE, autoSize);
        return subtitleConfigSize;
    }

    public static void setTextSize(int size) {
        Hawk.put(HawkConfig.SUBTITLE_TEXT_SIZE, size);
    }

    public static int getTimeDelay() {
        int subtitleConfigTimeDelay = Hawk.get(HawkConfig.SUBTITLE_TIME_DELAY, 0);
        return subtitleConfigTimeDelay;
    }

    public static void setTimeDelay(int delay) {
        Hawk.put(HawkConfig.SUBTITLE_TIME_DELAY, delay);
    }

    public static void upTextStyle(SimpleSubtitleView mSubtitleView) {
        upTextStyle(mSubtitleView, -1);
    }

    public static void upTextStyle(SimpleSubtitleView mSubtitleView, int style) {
        Integer colorIndex = style;
        if (style == -1) {
            colorIndex = Hawk.get(HawkConfig.SUBTITLE_TEXT_STYLE, 0);
        } else {
            Hawk.put(HawkConfig.SUBTITLE_TEXT_STYLE, style);
        }
        int[][] subtitleTextColor = App.getInstance().getSubtitleTextColor();
        mSubtitleView.setTextColor(subtitleTextColor[0][colorIndex]);
        mSubtitleView.setShadowLayer(10, 0, 0, subtitleTextColor[1][colorIndex]);
        // mSubtitleView.setBackGroundTextColor(subtitleTextColor[1][colorIndex]);
    }

    public static int getTextStyle() {
        return Hawk.get(HawkConfig.SUBTITLE_TEXT_STYLE, 0);
    }

    public static int getTextStyleSize() {
        int[][] subtitleTextColor = App.getInstance().getSubtitleTextColor();
        return Math.min(subtitleTextColor[0].length, subtitleTextColor[1].length);
    }
}