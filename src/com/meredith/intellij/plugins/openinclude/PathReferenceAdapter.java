package com.meredith.intellij.plugins.openinclude;

import com.intellij.openapi.paths.PathReferenceProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Dmitry Avdeev
 */
public class PathReferenceAdapter extends PsiReferenceProvider {

  private final PathReferenceProvider myConverter;
  private final boolean mySoft;

  public PathReferenceAdapter(PathReferenceProvider provider, boolean soft) {
    myConverter = provider;
    mySoft = soft;
  }

  @NotNull
  public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull final ProcessingContext context) {
    final ArrayList<PsiReference> list = new ArrayList<PsiReference>();
    myConverter.createReferences(element, list, mySoft);
    return list.toArray(new PsiReference[list.size()]);
  }
}
