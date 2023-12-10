package com.cy.devtool.plugins

import android.content.Context
import android.widget.Toast
import com.google.auto.service.AutoService

@AutoService(IModulePlugin::class)
class DevPlugin : IModulePlugin<String> {
    override fun invokePlugin(context: Context): String {
        Toast.makeText(context, "xxxxxx", Toast.LENGTH_SHORT).show()
        return "ss"
    }

}