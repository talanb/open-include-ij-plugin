package com.meredith.intellij.plugins.openinclude;

import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.psi.filters.*;
import com.intellij.psi.filters.position.NamespaceFilter;
import com.intellij.psi.filters.position.ParentElementFilter;
import com.intellij.psi.xml.XmlTag;
import com.intellij.xml.util.XmlUtil;
import org.jetbrains.annotations.NotNull;

public class DspIncludeReferenceContributor extends PsiReferenceContributor {
    public final static ClassFilter TAG_CLASS_FILTER = new ClassFilter(XmlTag.class);

    public static final NamespaceFilter NAMESPACE_ATG_DSP = new NamespaceFilter("http://www.atg.com/taglibs/daf/dspjspTaglib1_1");

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar psiReferenceRegistrar) {
        PathReferenceAdapter adapter = new PathReferenceAdapter(new MyPagesReferenceProvider(), false);

        XmlUtil.registerXmlAttributeValueReferenceProvider(psiReferenceRegistrar, new String[]{"page"}, andTagNames(NAMESPACE_ATG_DSP, "include"),
                adapter);
        XmlUtil.registerXmlAttributeValueReferenceProvider(psiReferenceRegistrar, new String[]{"src"}, andTagNames(NAMESPACE_ATG_DSP, "include"),
                adapter);
        XmlUtil.registerXmlAttributeValueReferenceProvider(psiReferenceRegistrar, new String[]{"script"}, andTagNames(NAMESPACE_ATG_DSP, "include"),
                adapter);
        XmlUtil.registerXmlAttributeValueReferenceProvider(psiReferenceRegistrar, new String[]{"src"}, andTagNames(new NamespaceFilter("/mdpTaglib"), "WebResourceScript"),
                adapter);
    }


    public static ScopeFilter andTagNames(final ElementFilter namespace, final String... tagNames) {
        return new ScopeFilter(new ParentElementFilter(new AndFilter(TAG_CLASS_FILTER, new TextFilter(tagNames)), 2));
    }

}
