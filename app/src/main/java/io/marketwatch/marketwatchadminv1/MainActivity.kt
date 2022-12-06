package io.marketwatch.marketwatchadminv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import io.marketwatch.marketwatchadminv1.domain.use_cases.GetActiveTips_UseCase
import io.marketwatch.marketwatchadminv1.presenter.ui.activitys.MarketWatchListViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}