package com.sendbird.mylibrary.ui

sealed class ViewEvent {

    // Keyboard Event
    object ShowKeyboard : ViewEvent()
    object HideKeyboard : ViewEvent()

    // Loading View Event
    object ShowLoadingView : ViewEvent()
    object HideLoadingView : ViewEvent()

    class ShowErrorDialog(val throwable: Throwable) : ViewEvent()
}