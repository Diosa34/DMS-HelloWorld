package com.github.Diosa34.DMS_HelloWorld;


interface ApplicationPart {
    val applicationInstance: Application
}

val ApplicationPart.filepath: String? get() = this.applicationInstance.filepath