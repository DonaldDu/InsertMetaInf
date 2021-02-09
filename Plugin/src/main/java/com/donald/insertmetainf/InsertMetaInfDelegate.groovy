package com.donald.insertmetainf

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.TestExtension
import com.android.build.gradle.TestPlugin
import org.gradle.api.Project

class InsertMetaInfDelegate {

    void apply(Project project) {
        project.extensions.create('insertMetaInf', InsertMetaInfExtension)
        InsertMetaInfTask insertMetaInfTask = project.task("insertMetaInfTask", InsertMetaInfTask)

        def resDir = new File(project.buildDir, 'generated/insertMetaInf')
        def destDir = new File(resDir, 'META-INF')

        boolean isLibrary = project.plugins.hasPlugin(LibraryPlugin)
        if (isLibrary) {
            project.extensions.getByType(LibraryExtension).sourceSets({
                main.resources.srcDirs += resDir
            })
        } else if (project.plugins.hasPlugin(TestPlugin)) {
            project.extensions.getByType(TestExtension).sourceSets({
                main.resources.srcDirs += resDir
            })
        } else if (project.plugins.hasPlugin(AppPlugin)) {
            project.extensions.getByType(AppExtension).sourceSets({
                main.resources.srcDirs += resDir
            })
        }

        insertMetaInfTask.destDir = destDir
        project.afterEvaluate {
            insertMetaInfTask.insertMetaInf = project.extensions.insertMetaInf
            project.tasks.findAll { task ->
                task.name.startsWith('generate') && task.name.endsWith('Resources')
            }.each { t ->
                t.dependsOn insertMetaInfTask
            }
        }
    }
}