package com.mutissx.magic.presentation.screens.candidate_info

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.mutissx.magic.R
import com.mutissx.magic.presentation.MainActivity
import org.junit.Rule
import org.junit.Test

class CandidateInfoScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun show_name_role_and_email_from_developer_in_candidate_info_screen() {
        val nameDeveloper = composeRule.activity.getString(R.string.developer_name)
        val roleDeveloper = composeRule.activity.getString(R.string.developer_role)
        val emailDeveloper = composeRule.activity.getString(R.string.developer_email)

        composeRule.onNodeWithText(nameDeveloper).assertIsDisplayed()
        composeRule.onNodeWithText(roleDeveloper).assertIsDisplayed()
        composeRule.onNodeWithText(emailDeveloper).assertIsDisplayed()
    }

    @Test
    fun show_name_from_developer_in_candidate_info_screen_is_wrong() {
        val nameDeveloper = "Wrong developer"
        composeRule.onNodeWithText(nameDeveloper).assertIsNotDisplayed()
    }
}
