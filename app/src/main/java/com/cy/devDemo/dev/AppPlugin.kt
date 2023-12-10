package com.cy.devDemo.dev

import android.content.Context
import com.cy.devDemo.uitls.toast
import com.cy.devtool.plugins.IModulePlugin
import com.google.auto.service.AutoService

@AutoService(IModulePlugin::class)
class AppPlugin : IModulePlugin<Boolean> {
    override fun invokePlugin(context: Context): Boolean {
        context.toast("执行了app模块的方法")
        return true
    }
}