package com.github.Diosa34.DMS_HelloWorld.io

import java.io.File
import java.io.FileInputStream

class FileStringReader(
    filepath: String
): ScannerStringReader(FileInputStream(File(filepath)))
