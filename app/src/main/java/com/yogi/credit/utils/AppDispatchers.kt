package com.yogi.credit.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */
class AppDispatchers(val main: CoroutineDispatcher,
                     val io: CoroutineDispatcher
)