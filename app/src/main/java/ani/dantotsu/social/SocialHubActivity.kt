package ani.dantotsu.social

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ani.dantotsu.R
import ani.dantotsu.initActivity
import ani.dantotsu.themes.ThemeManager

/**
 * AniLab social shell. The screen intentionally uses local presentation state only until a
 * social service is available; it does not pretend to persist rooms, messages, or friends.
 */
class SocialHubActivity : AppCompatActivity() {
    private lateinit var content: LinearLayout
    private val navy = Color.rgb(5, 14, 28)
    private val surface = Color.rgb(15, 27, 45)
    private val cyan = Color.rgb(80, 190, 255)
    private val muted = Color.rgb(164, 181, 205)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager(this).applyTheme()
        initActivity(this)
        show(SocialScreen.HOME)
    }

    private fun show(screen: SocialScreen) {
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setBackgroundColor(navy) }
        val scroll = ScrollView(this)
        content = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(20), dp(24), dp(20), dp(40)) }
        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        setContentView(root)

        text("AniLab", 28, Color.WHITE, true)
        text(screen.title, 24, Color.WHITE, true)
        text(screen.subtitle, 14, muted)
        space(16)

        when (screen) {
            SocialScreen.HOME -> home()
            SocialScreen.WATCH_TOGETHER -> watchTogether()
            SocialScreen.ROOM_LOBBY -> roomLobby()
            SocialScreen.WATCH_ROOM -> watchRoom()
            SocialScreen.FRIENDS -> friends()
            SocialScreen.MESSAGES -> messages()
            SocialScreen.CHAT -> chat()
            SocialScreen.NOTIFICATIONS -> notifications()
            SocialScreen.SEARCH_USERS -> searchUsers()
            SocialScreen.OTHER_PROFILE -> otherProfile()
        }
    }

    private fun home() {
        card("Watch Together", "Create or join a room and watch anime with friends.") { show(SocialScreen.WATCH_TOGETHER) }
        section("Friend activity")
        list("Shin is watching One Piece E1150", "10m ago")
        list("Kael finished Solo Leveling", "2h ago")
        section("Active friends")
        list("Shin  •  Watching", "One Piece")
        list("Mizu  •  Online", "Available to chat")
        section("Recent messages")
        list("Kael", "Bro, episode terbaru udah rilis?") { show(SocialScreen.CHAT) }
        navButtons()
    }

    private fun watchTogether() {
        action("＋  Create Room") { show(SocialScreen.ROOM_LOBBY) }
        action("⇥  Join Room") { show(SocialScreen.ROOM_LOBBY) }
        section("Active rooms")
        list("One Piece E1150", "Shin  •  5/10 members") { show(SocialScreen.ROOM_LOBBY) }
        list("Solo Leveling E12", "Kael  •  3/8 members") { show(SocialScreen.ROOM_LOBBY) }
        navButtons()
    }

    private fun roomLobby() {
        card("One Piece", "Episode 1150  •  Action  •  Adventure")
        list("Host", "Shin")
        section("Members (5/10)")
        list("Shin", "Host")
        list("Kael", "Online")
        list("Rynn", "Ready")
        action("Invite Friends") { show(SocialScreen.FRIENDS) }
        action("Start Watching") { show(SocialScreen.WATCH_ROOM) }
    }

    private fun watchRoom() {
        card("One Piece  •  Episode 1150", "Player handoff is ready for the existing ExoPlayer foundation.")
        text("Player integration", 16, cyan, true)
        text("This social shell does not create a second player. Start playback from the existing anime player when room synchronization is connected.", 14, muted)
        section("Chat")
        list("Shin", "MULAI nih 🔥  12:34")
        list("Kael", "Akhirnyaaa!  12:35")
        action("Open chat") { show(SocialScreen.CHAT) }
        action("Members") { show(SocialScreen.FRIENDS) }
    }

    private fun friends() {
        input("Search users or ID")
        section("My Friends")
        list("Shin", "Watching One Piece")
        list("Kael", "Online")
        list("Rynn", "In a Watch Room")
        section("Requests")
        list("Hana", "Accept request")
        action("Search users") { show(SocialScreen.SEARCH_USERS) }
    }

    private fun messages() {
        input("Search messages")
        list("Kael", "Bro, episode terbaru udah rilis?  •  12:24") { show(SocialScreen.CHAT) }
        list("Hana", "Nanti malam nonton bareng?  •  11:03") { show(SocialScreen.CHAT) }
        list("Rynn", "Gila sih, episode kemarin keren.") { show(SocialScreen.CHAT) }
    }

    private fun chat() {
        list("Kael  •  Online", "Bro, episode terbaru udah rilis?  12:20")
        list("You", "Iya, gila sih keren banget.  12:21")
        list("Kael", "Nanti malam nonton bareng?  12:22")
        input("Type a message…")
        action("Invite to Watch Together") { show(SocialScreen.ROOM_LOBBY) }
        action("Block / Report") { }
    }

    private fun notifications() {
        text("All   Friends   Mentions   System", 14, cyan, true)
        list("Kael sent you a friend request", "recent")
        list("Hana invited you to a watch room", "One Piece E1150")
        list("Rynn mentioned you in a message", "1h ago")
        list("New episode available", "One Piece E1150")
    }

    private fun searchUsers() {
        input("Search users")
        list("Hana", "@hana_ka") { action("Add Friend") { } }
        list("Hanami", "@hanami") { action("Add Friend") { } }
        list("HanaSky", "@hanasky") { action("Add Friend") { } }
        action("Open Hana profile") { show(SocialScreen.OTHER_PROFILE) }
    }

    private fun otherProfile() {
        card("Hana  •  Online", "@hana_ka\nJust a random anime lover.")
        action("Add Friend") { }
        action("Message") { show(SocialScreen.CHAT) }
        section("Currently Watching")
        list("Frieren", "Episode 20")
        section("Recent Activity")
        list("Hana created a watch room", "3h ago")
    }

    private fun navButtons() {
        action("Watch Together") { show(SocialScreen.WATCH_TOGETHER) }
        action("Friends") { show(SocialScreen.FRIENDS) }
        action("Messages") { show(SocialScreen.MESSAGES) }
        action("Notifications") { show(SocialScreen.NOTIFICATIONS) }
        action("Search Users") { show(SocialScreen.SEARCH_USERS) }
    }

    private fun text(value: String, size: Int, color: Int, bold: Boolean = false) {
        content.addView(TextView(this).apply { text = value; textSize = size.toFloat(); setTextColor(color); typeface = if (bold) Typeface.DEFAULT_BOLD else Typeface.DEFAULT; setPadding(0, dp(4), 0, dp(4)) })
    }
    private fun section(value: String) { space(14); text(value, 17, Color.WHITE, true) }
    private fun card(title: String, subtitle: String, click: (() -> Unit)? = null) { box(title, subtitle, click, cyan) }
    private fun list(title: String, subtitle: String, click: (() -> Unit)? = null) { box(title, subtitle, click, Color.WHITE) }
    private fun action(label: String, click: () -> Unit) { Button(this).apply { text = label; setTextColor(Color.WHITE); setOnClickListener { click() }; setBackgroundColor(Color.rgb(28, 72, 112)); content.addView(this, LinearLayout.LayoutParams(-1, dp(50)).apply { setMargins(0, dp(6), 0, dp(6)) }) } }
    private fun box(title: String, subtitle: String, click: (() -> Unit)?, titleColor: Int) { val box = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(16), dp(12), dp(16), dp(12)); setBackgroundColor(surface); if (click != null) setOnClickListener { click() } }; box.addView(TextView(this).apply { text = title; textSize = 16f; setTextColor(titleColor); typeface = Typeface.DEFAULT_BOLD }); box.addView(TextView(this).apply { text = subtitle; textSize = 13f; setTextColor(muted) }); content.addView(box, LinearLayout.LayoutParams(-1, ViewGroup.LayoutParams.WRAP_CONTENT).apply { setMargins(0, dp(5), 0, dp(5)) }) }
    private fun input(hint: String) { content.addView(EditText(this).apply { this.hint = hint; setHintTextColor(muted); setTextColor(Color.WHITE); setSingleLine(); setBackgroundColor(surface); setPadding(dp(14), 0, dp(14), 0) }, LinearLayout.LayoutParams(-1, dp(52)).apply { setMargins(0, 0, 0, dp(12)) }) }
    private fun space(value: Int) { content.addView(View(this), LinearLayout.LayoutParams(1, dp(value))) }
    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()

    private enum class SocialScreen(val title: String, val subtitle: String) {
        HOME("Social", "Find friends. Chat. Watch together."), WATCH_TOGETHER("Watch Together", "Watch anime together in real time."), ROOM_LOBBY("Room Lobby", "Invite friends and get ready."), WATCH_ROOM("Watch Room", "Player, chat, and members."), FRIENDS("Friends", "Your anime community."), MESSAGES("Messages", "Recent conversations."), CHAT("Chat Detail", "Keep the conversation going."), NOTIFICATIONS("Notifications", "Friend and watching activity."), SEARCH_USERS("Search Users", "Find your next anime friend."), OTHER_PROFILE("Profile", "A fellow anime fan.")
    }
}
