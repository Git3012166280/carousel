package rotationmap;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.rotationmap.example.R;

import java.util.List;

public class RotationMap implements ViewPager.OnPageChangeListener, RotationMapViewPager.OnViewPagerTouchListener {
    private final RotationMapViewPager mRotationMapViewPager;
    private RotationMapAdapter mRotationMapAdapter;
    private final List<Integer> mImages;
    private final LinearLayout m_point;
    private boolean mIsTouch = false;
    private Handler mHandler;
    private final Context mContext;

    public RotationMap(Context context, RotationMapViewPager view, LinearLayout linearLayout, List<Integer> Pictures) {
        mContext = context;
        mRotationMapViewPager = view;
        m_point = linearLayout;
        mImages = Pictures;
        initAdapter();
        initViewPager();
    }

    private void initAdapter() {
        mRotationMapAdapter = new RotationMapAdapter();
        mRotationMapAdapter.setData(mImages);
    }

    private void initViewPager() {
        insertPoint(mImages.size());  //绘制指示点
        mRotationMapViewPager.addOnPageChangeListener(this);//拖动监听
        mRotationMapViewPager.setOnViewPagerListener(this); //触摸处理
        mRotationMapViewPager.setAdapter(mRotationMapAdapter);
        mRotationMapViewPager.setCurrentItem(mRotationMapAdapter.getDataRealSize() * 100, false);
        mHandler = new Handler();
        mHandler.postDelayed(mLoopTask, 3500);
    }

    /**
     * 指示点
     *
     * @param size 指示点个数
     */
    private void insertPoint(int size) {
        for (int i = 0; i < size; i++) {
            View Point = new View(mContext);
            LinearLayout.LayoutParams point_style = new LinearLayout.LayoutParams(15, 15);//布局
            point_style.leftMargin = 20;
            Point.setLayoutParams(point_style);
            Point.setBackgroundResource(R.drawable.shape_point_normal);
            m_point.addView(Point);
        }
    }

    /**
     * 定时轮播
     */
    private final Runnable mLoopTask = new Runnable() {
        @Override
        public void run() {
            if (!mIsTouch) {
                mRotationMapViewPager.setCurrentItem(mRotationMapViewPager.getCurrentItem() + 1, true);
            }
            mHandler.postDelayed(this, 3500);
        }
    };

    @Override
    public void onPageSelected(int position) {
        int realPosition = position % mRotationMapAdapter.getDataRealSize();
        for (int i = 0; i < m_point.getChildCount(); i++) {
            if (i == realPosition) {
                m_point.getChildAt(i).setBackgroundResource(R.drawable.shape_point_selected);
            } else {
                m_point.getChildAt(i).setBackgroundResource(R.drawable.shape_point_normal);
            }
        }
    }

    @Override
    public void onPageTouch(boolean isTouch) {
        mIsTouch = isTouch;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
