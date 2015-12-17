/*
 * Copyright 2000-2007 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.meredith.intellij.plugins.openinclude;

import com.intellij.javaee.web.WebUtil;
import com.intellij.javaee.web.facet.WebFacet;
import com.intellij.openapi.paths.PathReference;
import com.intellij.openapi.paths.PathReferenceProvider;
import com.intellij.openapi.util.Iconable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmitry Avdeev
*/
public class MyPagesReferenceProvider implements PathReferenceProvider {

  public boolean createReferences(final @NotNull PsiElement psiElement, final @NotNull List<PsiReference> references, final boolean soft) {
    final FileReferenceSet set = FileReferenceSet.createSet(psiElement, soft, false, true);
    if (set == null) {
      return true;
    }
//    set.addCustomization(
//      FileReferenceSet.DEFAULT_PATH_EVALUATOR_OPTION,
//      new Function<PsiFile, Collection<PsiFileSystemItem>>() {
//        public Collection<PsiFileSystemItem> fun(final PsiFile file) {
//          final StrutsModel model = StrutsManager.getInstance().getStrutsModel(psiElement);
//          return model == null ? Collections.<PsiFileSystemItem>emptyList() : ContainerUtil.<PsiFileSystemItem>createMaybeSingletonList(model.getModuleRoot());
//        }
//      });
    Collections.addAll(references, set.getAllReferences());
    return false;
  }

  @Nullable
  public PathReference getPathReference(@NotNull final String path, @NotNull final PsiElement element) {
    return null;
//    final WebFacet webFacet = WebUtil.getWebFacet(element);
//    if (webFacet == null) return null;
//
//    final WebDirectoryUtil webDirectoryUtil = WebDirectoryUtil.getWebDirectoryUtil(element.getProject());
//    final PsiElement psiElement = webDirectoryUtil.findFileByPath(path, webFacet);
//    final Function<PathReference, Icon> iconFunction = new Function<PathReference, Icon>() {
//      public Icon fun(final PathReference webPath) {
//        return psiElement.getIcon(Iconable.ICON_FLAG_READ_STATUS);
//      }
//    };
//
//    return psiElement != null ? new PathReference(path, iconFunction) {
//      public PsiElement resolve() {
//        return webDirectoryUtil.findFileByPath(path, webFacet);
//      }
//    } : null;
  }
}
