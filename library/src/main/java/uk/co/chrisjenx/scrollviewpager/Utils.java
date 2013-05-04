package uk.co.chrisjenx.scrollviewpager;

import android.os.Build;
import android.util.Log;
import android.widget.OverScroller;
import android.widget.ScrollView;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created with Intellij with Android, BIZZBY product.
 * See licencing for usage of this code.
 * <p/>
 * User: chris
 * Date: 04/05/2013
 * Time: 11:56
 */
public final class Utils
{
    public static final boolean SUPPORTS_GINGER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    private static final boolean DEBUG = true;
    private static final String TAG = "ScrollViewPager";

    public static final Scroller findScroller(final ScrollView scrollView)
    {
        if (scrollView == null) return null;
        final Field field = findField(scrollView, "mScroller");
        makeFieldAccessable(field);
        if (field == null) return null;
        try
        {
            final Scroller scroller = (Scroller) field.get(scrollView);
            return scroller;
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static final OverScroller findOverScroller(final ScrollView scrollView)
    {
        if (scrollView == null) return null;
        final Field field = findField(scrollView, "mScroller");
        makeFieldAccessable(field);
        if (field == null) return null;
        try
        {
            final OverScroller scroller = (OverScroller) field.get(scrollView);
            return scroller;
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Finds a declared field on a class, if it will ask for the direct declaration first, then look deeper if it can't be found
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static final Field findField(final Object obj, final String fieldName)
    {
        if (obj == null) return null;
        try
        {
            if (DEBUG) Log.d(TAG, String.format("Find[%s] on [%s]", fieldName, obj));
            if(obj instanceof ScrollView)
                return ScrollView.class.getDeclaredField(fieldName);
            else
                return obj.getClass().getField(fieldName);
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Will make private/default fields accessible
     *
     * @param field
     */
    public static final void makeFieldAccessable(final Field field)
    {
        if (field == null) return;
        field.setAccessible(true);
    }
}
