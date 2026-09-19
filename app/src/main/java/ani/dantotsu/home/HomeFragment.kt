@@
 import ani.dantotsu.profile.ProfileActivity
+import ani.dantotsu.social.SocialHubActivity
+import ani.dantotsu.account.AccountActivity
@@
                 binding.homeUserAvatarContainer.setOnLongClickListener {
@@
                 }
+
+        binding.homeProfileWatchTogether.setOnClickListener {
+            startActivity(Intent(requireContext(), SocialHubActivity::class.java))
+        }
+        binding.homeProfilePremium.setOnClickListener {
+            startActivity(Intent(requireContext(), AccountActivity::class.java))
+        }
*** End Patch
