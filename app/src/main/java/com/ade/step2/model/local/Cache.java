package com.ade.step2.model.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by RadyaLabs PC on 15/12/2017.
 */

@Entity
public class Cache {

    @PrimaryKey
    @NonNull
    public int id;

    public String json;

}
