package com.cy.devtool.plugins

import android.content.Context

interface IModulePlugin<O> {
    fun invokePlugin(context: Context): O
}