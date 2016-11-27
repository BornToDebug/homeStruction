package com.lilla.homestruction.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import com.lilla.homestruction.R;
import com.lilla.homestruction.listeners.OnDialogCallbacksListener;

import java.util.Calendar;

/**
 * Created by lilla on 09/10/16.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener, DialogInterface.OnCancelListener, DialogInterface.OnClickListener {

    private OnDialogCallbacksListener onDialogCallbacksListener;
    private Handler mainHandler;
    private boolean wasCancelled;

    /**Gets instance of OnDialogCallbacksListener**/
    public void setOnDialogCallbacksListener(OnDialogCallbacksListener onDialogCallbacksListener) {
        this.onDialogCallbacksListener = onDialogCallbacksListener;
    }

    /**Created a TimePickerDialog**/
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /** Use the current time as the default values for the picker**/
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);

        /**Handler is used to delay the thread for a while, because TimePicker was buggy in older Android versions**/
        mainHandler = new Handler(getContext().getMainLooper());
        /** Create a new instance of TimePickerDialog and return it**/
        TimePickerDialog dialog = new TimePickerDialog(getContext(), R.style.DialogTheme, this, hour, minute,
                DateFormat.is24HourFormat(getContext()));
        dialog.setOnCancelListener(this);
        return dialog;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        /**Do something with the time chosen by the user**/
        /**Delays the thread for 250 ms (to be sure that if the cancel button was pressed, we don't need to set the time)**/
        mainHandler.postDelayed(new ShouldCancelRunnable(hourOfDay, minute), 250);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (onDialogCallbacksListener != null) {
            onDialogCallbacksListener.onCancel();
        }
        wasCancelled = true;
        super.onCancel(dialog);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        System.out.println("LOG Clicked");
    }

    /**If the cancel button was not pressed, we set the time**/
    private class ShouldCancelRunnable implements Runnable {

        private int hourOfDay;
        private int minute;

        public ShouldCancelRunnable(int hourOfDay, int minute) {
            this.hourOfDay = hourOfDay;
            this.minute = minute;
        }

        @Override
        public void run() {
            if (onDialogCallbacksListener != null && !wasCancelled) {
                onDialogCallbacksListener.onTimeSet(hourOfDay, minute);
            }
        }
    }
}