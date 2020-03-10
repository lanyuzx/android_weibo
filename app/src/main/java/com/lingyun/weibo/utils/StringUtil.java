package com.lingyun.weibo.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;

public class StringUtil {

    public static SpannableString setTextViewSpannable( String text , String changeText, int color,boolean isUnderline) {
        int astart = text.indexOf(changeText);
        int aend = astart + changeText.length();
        SpannableString ss = new SpannableString(text);
//        ForegroundColorSpan bcolorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, color));
        ForegroundColorSpan acolorSpan = new ForegroundColorSpan(color);
        ss.setSpan(acolorSpan, astart, aend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//给“隐私政策”加颜色
        if (isUnderline){
            ss.setSpan(new UnderlineSpan(), astart, aend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);////给“用户协议”加下划线
        }
        return ss;
    }

    public static boolean isBlank(final CharSequence cs) {
        if (cs.equals("null")){
            return true;
        }
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }


}
