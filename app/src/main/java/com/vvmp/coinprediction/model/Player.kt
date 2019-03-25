package com.vvmp.coinprediction.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vvmp.coinprediction.BR

data class Score(private var _scorePlayerOne: Int = 0, private var _scorePlayerTwo: Int = 0) : BaseObservable() {
    var scorePlayerOne: Int
        @Bindable get() {
            return _scorePlayerOne
        }
        set(value) {
            _scorePlayerOne = value
            notifyPropertyChanged(BR.scorePlayerOne)
        }

    var scorePlayerTwo: Int
        @Bindable get() {
            return _scorePlayerTwo
        }
        set(value) {
            _scorePlayerTwo = value
            notifyPropertyChanged(BR.scorePlayerTwo)
        }
}
