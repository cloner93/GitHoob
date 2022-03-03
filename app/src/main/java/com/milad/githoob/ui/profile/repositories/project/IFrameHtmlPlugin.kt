package com.milad.githoob.ui.profile.repositories.project

import io.noties.markwon.AbstractMarkwonPlugin
import io.noties.markwon.MarkwonConfiguration
import io.noties.markwon.MarkwonPlugin
import io.noties.markwon.RenderProps
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.html.HtmlTag
import io.noties.markwon.html.tag.SimpleTagHandler
import io.noties.markwon.image.ImageProps
import io.noties.markwon.image.ImageSize
import org.commonmark.node.Image

class IFrameHtmlPlugin : AbstractMarkwonPlugin() {
    override fun configure(registry: MarkwonPlugin.Registry) {
        registry.require(
            HtmlPlugin::class.java
        ) { htmlPlugin: HtmlPlugin ->
            htmlPlugin.addHandler(
                EmbedTagHandler()
            )
        }
    }

    private class EmbedTagHandler : SimpleTagHandler() {
        override fun getSpans(
            configuration: MarkwonConfiguration,
            renderProps: RenderProps,
            tag: HtmlTag
        ): Any? {
            val imageSize = ImageSize(
                ImageSize.Dimension(640F, "px"),
                ImageSize.Dimension(480F, "px")
            )
            ImageProps.IMAGE_SIZE[renderProps] = imageSize
            ImageProps.DESTINATION[renderProps] =
                "https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png"
            return configuration.spansFactory().require(Image::class.java)
                .getSpans(configuration, renderProps)
        }

        override fun supportedTags(): Collection<String> {
            return setOf("iframe")
        }
    }
}
