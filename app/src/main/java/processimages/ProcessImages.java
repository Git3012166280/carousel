package processimages;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ProcessImages {
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     *
     * @param res       资源
     * @param resId     资源id
     * @param reqWidth  需要的宽
     * @param reqHeight 需要的高
     * @return 成功返回bitmap, 异常返回null
     */
    public static Bitmap decodeCalSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 检查bitmap的大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        // 设置为true，BitmapFactory会解析图片的原始宽高信息，并不会加载图片
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        BitmapFactory.decodeResource(res, resId, options);

        // 计算采样率
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // 设置为false，加载bitmap
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 计算采样率
     *
     * @param options   图片的宽高信息
     * @param reqWidth  需要的宽
     * @param reqHeight 需要的高
     * @return 成功返回采样率, 异常返回1
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 设置初始压缩率为1
        int inSampleSize;
        try {
            // 获取原图片长宽
            int width = options.outWidth;
            int height = options.outHeight;
            // reqWidth/width,reqHeight/height两者中最大值作为压缩比
            int w_size = width / reqWidth;
            int h_size = height / reqHeight;
            inSampleSize = Math.max(w_size, h_size);  // 取w_size和h_size两者中最大值作为压缩比
        } catch (Exception e) {
            return -1;
        }
        return inSampleSize;
    }

    /**
     * 根据手机的分辨率从 dp(像素) 的单位 转成为 px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return 成功返回px值, 异常返回-1
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context 上下文
     * @param pxValue 像素值
     * @return 成功返回dp值, 异常返回-1
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
