package com.ipx.common.validator.result;


import com.ipx.common.validator.threadlocal.ThreadMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017/1/6.
 */
public class MessageManager {

    private static       MessageManager mm           = null;
    private final static String         DEFAULT_PATH = "i18n";

    private Logger logger = LoggerFactory.getLogger(MessageManager.class);

    private MessageManager() {

    }

    public static MessageManager getInstance() {
        if (mm == null) {
            mm = new MessageManager();
        }
        return mm;
    }

    /**
     * 获得消息
     * @param key
     * @return
     */
    public String getMessage(String key)
    {
        ResourceBundle rb = ThreadMap.getInstance().getResouceBundle();
        if(rb == null)
        {
            rb = ResourceBundle.getBundle("i18n",new Locale(ThreadMap.getInstance().getLanguage()));
            if(rb == null)
                return null;
        }
        return rb.getString(key);
    }



}
