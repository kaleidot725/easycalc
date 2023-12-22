package jp.kaleidot725.easycalc.core.ui.screen.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.repository.TextRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MyListViewModel(
    private val textRepository: TextRepository,
) : ContainerHost<MyListState, MyListEvent>, MyListAction, ViewModel() {
    override val container = container<MyListState, MyListEvent>(MyListState())

    init {
        viewModelScope.launch {
            textRepository.getFavorite().collectLatest {
                intent {
                    reduce {
                        state.copy(mathTexts = it)
                    }
                }
            }
        }
    }

    override fun clickText(mathText: MathText) = intent {
        postSideEffect(MyListEvent.ClickText(mathText))
    }

    override fun popBack() = intent {
        postSideEffect(MyListEvent.PopBack)
    }
}