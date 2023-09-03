package com.indisparte.util.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * @author Antonio Di Nuzzo
 */
inline fun <T> Flow<T>.collectIn(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline action: suspend (T) -> Unit,
) = owner.lifecycleScope.launch {
    owner.lifecycle.repeatOnLifecycle(minActiveState) {
        collect {
            action(it)
        }
    }
}









