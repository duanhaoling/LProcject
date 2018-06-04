package com.ldh.poxy;

import com.ldh.poxy.bean.Font;
import com.ldh.poxy.bean.FontProvider;

/**
 * Create by ldh on 2017/11/10.
 */
public class FontProviderFromDisk implements FontProvider {
    @Override
    public Font getFont(String name) {
        return new Font("来自硬盘");
    }
}
