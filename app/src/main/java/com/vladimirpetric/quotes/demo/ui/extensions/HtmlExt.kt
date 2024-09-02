package com.vladimirpetric.quotes.demo.ui.extensions

import android.content.res.Resources
import android.graphics.drawable.VectorDrawable
import android.util.Xml
import androidx.annotation.WorkerThread
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.ByteArrayInputStream
import java.io.IOException

@WorkerThread
fun Resources.createVectorDrawableFromSvg(
    svgHtml: String
): VectorDrawable? {
    return try {
        val inputStream = ByteArrayInputStream(
            svgHtml.toByteArray()
        )
        val parser: XmlPullParser =
            Xml.newPullParser()
        parser.setInput(inputStream, null)
        parser.nextTag()
        // Create an empty AttributeSet
        val attrs = Xml.asAttributeSet(parser)
        VectorDrawable.createFromXmlInner(
            /* r = */ this,
            /* parser = */ parser,
            /* attrs = */ attrs,
            /* theme = */ null
        ) as VectorDrawable?
    } catch (e: XmlPullParserException) {
        // Handle the exception
        null
    } catch (e: IOException) {
        // Handle the exception
        null
    }
}