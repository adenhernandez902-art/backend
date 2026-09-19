package ani.dantotsu.account

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ani.dantotsu.dp
import ani.dantotsu.initActivity
import ani.dantotsu.themes.ThemeManager

/** Account presentation surface backed by the existing preference/profile infrastructure. */
class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager(this).applyTheme()
        initActivity(this)
        val content = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(20.dp, 24.dp, 20.dp, 36.dp); setBackgroundColor(Color.rgb(5, 14, 28)) }
        fun label(value: String, size: Int, color: Int = Color.WHITE, bold: Boolean = false) { content.addView(TextView(this).apply { text = value; textSize = size.toFloat(); setTextColor(color); typeface = if (bold) Typeface.DEFAULT_BOLD else Typeface.DEFAULT; setPadding(0, 6.dp, 0, 6.dp) }) }
        label("Account", 28, Color.WHITE, true)
        label("Manage your account and preferences", 14, Color.rgb(164, 181, 205))
        label("Shin", 24, Color.WHITE, true)
        label("Forever a weeb ^_^\nFree User", 15, Color.rgb(164, 181, 205))
        label("Anime watched       124\nEpisodes watched    1,892\nFavorites                 37", 16, Color.WHITE)
        listOf("Profile", "Premium & Diamonds", "Appearance", "Notifications", "Settings", "About AniLab").forEach { value -> label("$value\nManage your $value preferences  ›", 16, Color.WHITE); content.addView(View(this), LinearLayout.LayoutParams(-1, 1.dp)) }
        label("Edit Profile", 18, Color.rgb(80, 190, 255), true)
        label("Username: Shin\nBio: Forever a weeb ^_^\nAvatar, banner, and accent color are ready for existing profile preferences.", 15, Color.rgb(164, 181, 205))
        setContentView(ScrollView(this).apply { addView(content) })
    }
    private val Int.dp get() = (this * resources.displayMetrics.density).toInt()
}
