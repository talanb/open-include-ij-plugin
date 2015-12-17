package com.meredith.intellij.plugins.openinclude;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.paths.StaticPathReferenceProvider;
import org.jetbrains.annotations.Nullable;

public class OpenInclude extends StaticPathReferenceProvider {
    public OpenInclude(@Nullable FileType[] fileTypes) {
        super(fileTypes);
    }
}
