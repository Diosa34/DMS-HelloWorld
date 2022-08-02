package com.github.diosa.dms.mainGUI

import com.github.diosa.dms.users.User
import javafx.event.*

class SuccessEvent(
    val user: User?
) : Event(LOGIN_COMPLETED) {
    companion object{
        /**
         * The only valid EventType for the CustomEvent.
         */
        val LOGIN_COMPLETED: EventType<SuccessEvent> = EventType(Event.ANY, "LOGIN_COMPLETED")
    }
}