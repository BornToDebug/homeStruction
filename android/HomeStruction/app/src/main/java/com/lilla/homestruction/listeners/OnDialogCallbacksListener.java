package com.lilla.homestruction.listeners;

/**
 * Created by lilla on 09/10/16.
 */
/**An interface which is used for communication between the MainScreen class and the TimePickerFragment class**/
public interface OnDialogCallbacksListener {
    void onTimeSet(int hourOfDay, int minute);
    void onCancel();
}
