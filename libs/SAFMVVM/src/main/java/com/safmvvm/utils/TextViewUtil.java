package com.safmvvm.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * TextView工具
 */
public class TextViewUtil {


    //    private static final String PATTERN_PHONE = "((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}";
    private static final String PATTERN_PHONE = "((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(19[0-9]))\\d{8}";
    private static final String SCHEME_TEL = "tel:";

    public interface FormatPhoneNumberListener {
        void clickPhoneNumber(View textView, String phoneNum);
    }

    /**
     * 格式化TextView的显示格式，识别手机号
     *
     * @param textView
     * @param source
     * @return
     */
    public static SpannableStringBuilder formatPhoneNumber(
            TextView textView,
            String source,
            /** 手机号颜色*/
            int phoneColor,
            FormatPhoneNumberListener formatPhoneNumberListener) {
        // 若要部分 SpannableString 可点击，需要如下设置
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        // 将要格式化的 String 构建成一个 SpannableStringBuilder
        SpannableStringBuilder value = new SpannableStringBuilder(source);

        // 使用正则表达式匹配电话
        Linkify.addLinks(value, Pattern.compile(PATTERN_PHONE), SCHEME_TEL);

        // 获取上面到所有 addLinks 后的匹配部分(这里一个匹配项被封装成了一个 URLSpan 对象)
        URLSpan[] urlSpans = value.getSpans(0, value.length(), URLSpan.class);
        for (final URLSpan urlSpan : urlSpans) {
            if (urlSpan.getURL().startsWith(SCHEME_TEL)) {
                int start = value.getSpanStart(urlSpan);
                int end = value.getSpanEnd(urlSpan);
                value.removeSpan(urlSpan);
                value.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        String phone = urlSpan.getURL().replace(SCHEME_TEL, "");
                        formatPhoneNumberListener.clickPhoneNumber(widget, phone);
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(phoneColor);
                        ds.setUnderlineText(true);
                    }
                }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return value;
    }

}
