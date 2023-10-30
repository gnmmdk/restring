package dev.b3nedikt.restring.example;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.NotNull;

/**
 * Desc: Dialog 通用设置
 * 处理 java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState 异常
 * <p>
 * Date: 2019-08-27
 * Copyright: Copyright (c) 2010-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: linjiaqiang
 */
public class CommonDialogFragment extends DialogFragment {

    private DialogInterface.OnDismissListener mOnDismissListener;
    private DialogInterface.OnCancelListener mOnCancelListener;

    @CallSuper
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog =  new LeakSafeDialog(requireContext(), getTheme());
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.BaseCustomDialog);
    }

    @Override
    public void onDismiss(@NotNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    @Override
    public void onCancel(@NotNull DialogInterface dialog) {
        super.onCancel(dialog);
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(dialog);
        }
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void show(FragmentManager manager, String tag) {
        this.show(manager.beginTransaction(), tag);
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShowing() {
        return isAdded() && isVisible();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        mOnDismissListener = listener;
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener listener) {
        mOnCancelListener = listener;
    }

    @Override
    public int show(@NotNull FragmentTransaction transaction, String tag) {
        if (isResumed() || isAdded() || isVisible() || isRemoving() || isDetached()) {
            return -1;
        }
        try {
            return transaction.add(this, tag).commitAllowingStateLoss();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "showDialog Failed", e);
            e.printStackTrace();
            return -1;
        }
    }
}