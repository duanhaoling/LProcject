package com.example.mydemo.greendaotdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOCAL_VIDEO".
*/
public class LocalVideoDao extends AbstractDao<LocalVideo, Long> {

    public static final String TABLENAME = "LOCAL_VIDEO";

    /**
     * Properties of entity LocalVideo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FileName = new Property(1, String.class, "fileName", false, "FILE_NAME");
        public final static Property CreationTime = new Property(2, String.class, "creationTime", false, "CREATION_TIME");
        public final static Property Md5 = new Property(3, String.class, "md5", false, "MD5");
        public final static Property Latitude = new Property(4, String.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(5, String.class, "longitude", false, "LONGITUDE");
    }


    public LocalVideoDao(DaoConfig config) {
        super(config);
    }
    
    public LocalVideoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOCAL_VIDEO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FILE_NAME\" TEXT NOT NULL ," + // 1: fileName
                "\"CREATION_TIME\" TEXT," + // 2: creationTime
                "\"MD5\" TEXT NOT NULL ," + // 3: md5
                "\"LATITUDE\" TEXT," + // 4: latitude
                "\"LONGITUDE\" TEXT);"); // 5: longitude
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOCAL_VIDEO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LocalVideo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getFileName());
 
        String creationTime = entity.getCreationTime();
        if (creationTime != null) {
            stmt.bindString(3, creationTime);
        }
        stmt.bindString(4, entity.getMd5());
 
        String latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindString(5, latitude);
        }
 
        String longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindString(6, longitude);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LocalVideo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getFileName());
 
        String creationTime = entity.getCreationTime();
        if (creationTime != null) {
            stmt.bindString(3, creationTime);
        }
        stmt.bindString(4, entity.getMd5());
 
        String latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindString(5, latitude);
        }
 
        String longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindString(6, longitude);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LocalVideo readEntity(Cursor cursor, int offset) {
        LocalVideo entity = new LocalVideo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // fileName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // creationTime
            cursor.getString(offset + 3), // md5
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // latitude
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // longitude
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LocalVideo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFileName(cursor.getString(offset + 1));
        entity.setCreationTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMd5(cursor.getString(offset + 3));
        entity.setLatitude(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLongitude(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LocalVideo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LocalVideo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LocalVideo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
