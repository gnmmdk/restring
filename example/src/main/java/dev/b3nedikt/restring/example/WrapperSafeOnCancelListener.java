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
class WrapperSafeOnCancelListener implements DialogInterface.OnCancelListener {

    private WeakReference<DialogInterface.OnCancelListener> mListener;

    WrapperSafeOnCancelListener(@Nullable DialogInterface.OnCancelListener listener) {
        mListener = new WeakReference<>(listener);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        DialogInterface.OnCancelListener listener = mListener.get();
        if (listener != null) {
            listener.onCancel(dialog);
        }
    }
}
