package com.donald.insertmetainf


import org.gradle.api.Plugin
import org.gradle.api.Project

class InsertMetaInfPlugin implements Plugin<Project> {
    private InsertMetaInfDelegate delegate = new InsertMetaInfDelegate()

    @Override
    void apply(Project project) {
        delegate.apply(project)
    }
}