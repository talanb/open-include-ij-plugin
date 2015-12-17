package com.meredith.intellij.plugins.openinclude;

import com.intellij.lang.Language;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.psi.filters.*;
import com.intellij.psi.filters.position.NamespaceFilter;
import com.intellij.psi.filters.position.ParentElementFilter;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.util.XmlUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class OpenIncludeProjectComponent implements ProjectComponent {
    public final static ClassFilter TAG_CLASS_FILTER = new ClassFilter(XmlTag.class);

    public static final NamespaceFilter NAMESPACE_ATG_DSP = new NamespaceFilter("http://www.atg.com/taglibs/daf/dspjspTaglib1_0");
    public final PsiReferenceRegistrar registrar;
    public final MyPagesReferenceProvider myPagesReferenceProvider;

    public OpenIncludeProjectComponent(Project project) {
        registrar = ReferenceProvidersRegistry.getInstance().getRegistrar(Language.ANY);

        myPagesReferenceProvider = new MyPagesReferenceProvider();
    }


    @Override
    public void projectOpened() {

    }

    @Override
    public void projectClosed() {

    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "OpenInclude";
    }

    protected void registerTags(final PsiReferenceProvider provider,
                                final @NonNls String attributeName,
                                final NamespaceFilter namespaceFilter,
                                final @NonNls String... tagNames) {
        XmlUtil.registerXmlAttributeValueReferenceProvider(registrar, new String[]{attributeName}, andTagNames(namespaceFilter, tagNames),
                provider);
    }

    public static ScopeFilter andTagNames(final ElementFilter namespace, final String... tagNames) {
        return new ScopeFilter(new ParentElementFilter(new AndFilter(namespace, TAG_CLASS_FILTER, new TextFilter(tagNames)), 2));
    }


}
