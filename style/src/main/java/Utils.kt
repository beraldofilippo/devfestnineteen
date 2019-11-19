import android.content.Context
import android.content.res.Configuration

/**
 * https://chris.banes.dev/talks/2019/gesture-nav-dark-theme/
 */

object Utils {
    fun isInDarkMode(context: Context) : Boolean =
        context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}