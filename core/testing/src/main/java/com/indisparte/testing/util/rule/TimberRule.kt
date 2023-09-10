package com.indisparte.testing.util.rule

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import timber.log.Timber

/**
 * [Medium Article](https://medium.com/ackee/timbering-unit-tests-8acc23f7014)
 * @author Antonio Di Nuzzo
 *
 */
class TimberRule : TestWatcher() {

    private val printlnTree = object : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            println("$tag: $message")
        }
    }

    override fun starting(description: Description) {
        super.starting(description)
        Timber.plant(printlnTree)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Timber.uproot(printlnTree)
    }
}