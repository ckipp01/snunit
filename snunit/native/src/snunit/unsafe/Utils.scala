package snunit.unsafe

import java.nio.charset.Charset
import scala.scalanative.unsafe._

private[snunit] object Utils {
  private val charset = Charset.defaultCharset()

  def fromCStringAndSize(cstr: CString, size: Int): String = {
    val bytes = new Array[Byte](size)

    var c = 0
    while (c < size) {
      bytes(c) = !(cstr + c)
      c += 1
    }

    new String(bytes, charset)
  }
}
