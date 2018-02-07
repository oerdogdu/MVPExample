package com.example.atoi.mvptrial.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by Atoi on 4.01.2018.
 */

public final class NewsPersistenceContract {
    private NewsPersistenceContract() {};

    public static abstract class NewsEntry implements BaseColumns {
        public static final String TABLE_NAME = "news";
        public static final String COLUMN_NAME_ENTRY_ID = "newsid";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_TEXT = "text";
    }
}
