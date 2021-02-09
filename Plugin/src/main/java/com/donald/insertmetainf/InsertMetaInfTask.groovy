package com.donald.insertmetainf

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class InsertMetaInfTask extends DefaultTask {
    @Input
    File destDir
    @Input
    InsertMetaInfExtension insertMetaInf

    @TaskAction
    def doExecute() {
        def file = new File(destDir, insertMetaInf.metaInfName)
        if (insertMetaInf.hasConent()) {
            file.parentFile.mkdirs()
            FileUtils.writeByteArrayToFile(file, insertMetaInf.metaInfContent.getBytes("UTF-8"))
        } else {
            if (file.exists()) file.delete()
        }
    }
}