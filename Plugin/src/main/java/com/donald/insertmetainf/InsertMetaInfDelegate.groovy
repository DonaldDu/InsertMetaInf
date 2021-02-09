package com.donald.insertmetainf


import org.gradle.api.Project

class InsertMetaInfDelegate {

    void apply(Project project) {
        project.extensions.create('insertMetaInf', InsertMetaInfExtension)
        InsertMetaInfTask insertMetaInfTask = project.tasks.create("insertMetaInfTask", InsertMetaInfTask)

        def resDir = new File(project.buildDir, 'generated/insertMetaInf')
        def destDir = new File(resDir, 'META-INF')
        insertMetaInfTask.destDir = destDir

        project.afterEvaluate {
            project.extensions.android.sourceSets.main.resources.srcDirs += resDir
            insertMetaInfTask.insertMetaInf = project.extensions.insertMetaInf
            project.tasks.findAll { task ->
                task.name.startsWith('generate') && task.name.endsWith('Resources')
            }.each { t ->
                t.dependsOn insertMetaInfTask
            }
        }
    }
}