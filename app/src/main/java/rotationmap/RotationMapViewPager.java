package rotationmap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class RotationMapViewPager extends ViewPager {
    private OnViewPagerTouchListener mTouchListener = null;

    public RotationMapViewPager(@NonNull Context context) {
        super(context);
    }

    public RotationMapViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mTouchListener != null) {
                    mTouchListener.onPageTouch(true);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mTouchListener != null) {
                    mTouchListener.onPageTouch(false);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setOnViewPagerListener(OnViewPagerTouchListener listener) {
        this.mTouchListener = listener;
    }

    public interface OnViewPagerTouchListener {
        void onPageTouch(boolean isTouch);
    }
}
