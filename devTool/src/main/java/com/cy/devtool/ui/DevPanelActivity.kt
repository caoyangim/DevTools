package com.cy.devtool.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cy.devtool.R
import com.cy.devtool.anno.DevelopView
import com.cy.devtool.plugins.IModulePlugin
import com.cy.devtool.ui.canvas.CanvasRuleActivity
import com.cy.devtool.utils.AppUtils
import java.util.ServiceLoader


@DevelopView
class DevPanelActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DevPanelActivity::class.java))
        }
    }

    private lateinit var mBtnInvokeTest: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = AppUtils.getAppName(this) + "工具包"
        setContentView(R.layout.activity_dev_panel)
        mBtnInvokeTest = findViewById<Button>(R.id.btn_service_test).also { btn ->
            btn.setOnClickListener {
                val loaders = ServiceLoader.load(
                    IModulePlugin::class.java
                )
                loaders.forEach {
                    val result = it.invokePlugin(btn.context)
                }
            }
        }
        val btnCanvasRule = findViewById<Button>(R.id.btn_canvas_rule).also {btn ->
            btn.setOnClickListener {
                CanvasRuleActivity.start(this)
            }
        }
    }
}