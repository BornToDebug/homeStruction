package com.lilla.homestruction.listeners;

/**
 * Created by lilla on 09/10/16.
 */

public interface OnDialogCallbacksListener {
    void onTimeSet(int hourOfDay, int minute);
    void onCancel();
}
