package com.hongyan.androidpractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hongyan.androidpractice.ui.theme.AndroidPracticeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun jump() {
        val intent =  Intent(this, ScoreListActivity::class.java)
        intent.putExtra("key1","Value1")
        startActivity(intent)
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Column {
            Text(text = "Hello2 $name!", modifier = modifier)
            Text(text = "哈哈哈哈哈")
            ImageGroup()
            Button(onClick = {
                println("点击了按钮")
                jump()
            }) {
                Icon(imageVector = Icons.Default.Call, contentDescription = null)
                Text(text = "跳转Button")
            }
        }
    }

    @Composable
    fun ImageGroup() {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "收拾收拾"
        )
    }
}
