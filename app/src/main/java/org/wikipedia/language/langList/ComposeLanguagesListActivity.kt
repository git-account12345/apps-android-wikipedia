package org.wikipedia.language.langList

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import org.wikipedia.WikipediaApp
import org.wikipedia.activity.BaseActivity
import org.wikipedia.settings.languages.WikipediaLanguagesFragment
import org.wikipedia.util.DeviceUtil

class ComposeLanguagesListActivity : BaseActivity() {
    private var isLanguageSearched: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LanguagesListParentScreen(
                onBackButtonClick = {
                    finish()
                },
                onListItemClick = { languageCode ->
                    val interactionsCount = 1
                    val app = WikipediaApp.instance
                    if (languageCode != app.appOrSystemLanguageCode) {
                        app.languageState.addAppLanguageCode(languageCode)
                    }
                    val returnIntent = Intent()
                    returnIntent.putExtra(WikipediaLanguagesFragment.ADD_LANGUAGE_INTERACTIONS, interactionsCount)
                    returnIntent.putExtra(LANGUAGE_SEARCHED, isLanguageSearched)
                    setResult(RESULT_OK, returnIntent)
                    finish()
                },
                onLanguageSearched = {
                    isLanguageSearched = it
                }
            )
        }
    }

    override fun onBackPressed() {
        DeviceUtil.hideSoftKeyboard(this)
        val returnIntent = Intent()
        returnIntent.putExtra(LANGUAGE_SEARCHED, isLanguageSearched)
        setResult(RESULT_OK, returnIntent)
        super.onBackPressed()
    }

    companion object {
        const val LANGUAGE_SEARCHED = "language_searched"
    }
}
