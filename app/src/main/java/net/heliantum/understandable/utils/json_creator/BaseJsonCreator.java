package net.heliantum.understandable.utils.json_creator;

import android.content.Context;

import java.io.File;

/**
 * Created by Marcin on 2017-07-25.
 */

public abstract class BaseJsonCreator {

    protected File input;
    protected Context context;

    public BaseJsonCreator(File input, Context context) {
        this.input = input;
        this.context = context;
    }

    abstract void create();

}
