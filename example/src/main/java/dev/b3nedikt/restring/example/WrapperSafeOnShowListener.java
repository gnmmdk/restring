package dev.b3nedikt.restring.example;

import android.content.DialogInterface;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Desc:
 * <p>
 * Date: 2019-07-22
 * Copyright: Copyright (c) 2010-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time: 2019/9/3
 * Update Comments:
 *
 * @Author: linjiaqiang
 */
class WrapperSafeOnShowListener implements DialogInterface.OnShowListener {

    private WeakReference<DialogInterface.OnShowListener> mListener;

    WrapperSafeOnShowListener(@Nullable DialogInterface.OnShowListener listener) {
        mListener = new WeakReference<>(listener);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        DialogInterface.OnShowListener listener = mListener.get();
        if (listener != null) {
            listener.onShow(dialog);
        }
    }
}
