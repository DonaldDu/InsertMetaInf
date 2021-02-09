package com.donald.insertmetainf

class InsertMetaInfExtension {
    String metaInfName = 'ExtraMetaInf.txt'
    String metaInfContent = 'test'

    boolean hasConent() {
        return metaInfContent != null && metaInfContent.length() > 0
    }
}