package com.github.Diosa34.DMS_HelloWorld.parsing

import java.io.File
import java.io.FileInputStream

class FileStringReader(
    filepath: String
): ScannerStringReader(FileInputStream(File(filepath.also { FileVerification.fullVerification(it) })))
// FileVerification.fullVerification(it) - boolean