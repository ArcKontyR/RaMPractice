package ru.arckontyr.rampractice.ui.profile

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun TimePickerDialog(
    context: Context,
    initialHour: Int,
    initialMinute: Int,
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit,
) {
    DisposableEffect(Unit) {
        val dialog = android.app.TimePickerDialog(
            context,
            { _, h, m -> onConfirm(h, m) },
            initialHour,
            initialMinute,
            true,   // is24HourView
        ).apply {
            setOnCancelListener { onDismiss() }
            show()
        }
        onDispose { dialog.dismiss() }
    }
}