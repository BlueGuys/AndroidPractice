package com.hongyan.androidpractice

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scrollState = rememberScrollState()
            Column(modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .verticalScroll(scrollState)) {
                Text(text = "Compose UI")
                ImageGroup()
                Button(onClick = {
                    println("点击了按钮")
                    jump()
                }) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = null)
                    Text(text = "跳转Button")
                }
                Button(onClick = {
                    jumpTest()
                }) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = null)
                    Text(text = "测试Activity")
                }
            }
        }
    }

    private fun jump() {
        val intent =  Intent(this, ScoreListActivity::class.java)
        intent.putExtra("key1","Value1")
        startActivity(intent)
    }

    private fun jumpTest() {
        val intent =  Intent(this, TestDemoActivity::class.java)
        intent.putExtra("key1","Value1")
        startActivity(intent)
    }

    @Composable
    fun ImageGroup() {
        // 加载本地图片
        Image(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            painter = painterResource(id = R.drawable.mountain),
            contentDescription = "描述",
            alpha = 1f,//0~1完全透明到完全不透明设置
            contentScale = ContentScale.Crop//图片拉伸或裁剪设置
        )
        // 加载网络图片
        AsyncImage(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            model = "https://ziyuan.guwendao.net/mingjuImg/B38A17D34E9DADE19D67D0F27D429058.jpg",
            contentDescription = "描述",
            contentScale = ContentScale.Crop
        )
    }
}