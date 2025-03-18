package com.hongyan.androidpractice.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class TestDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val scrollState = rememberScrollState()
            Column(modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .verticalScroll(scrollState)) {
                Text(text = "测试Activity")
                Button(onClick = {
//                    test01()
//                    test02()
                    test03()
                }) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = null)
                    Text(text = "启动协程")
                }
            }
        }
    }

    private fun test01 () {
        println("启动协程：11111111" + Thread.currentThread().name)
        runBlocking { // runBlocking 会阻塞主线程
            println("2222222222")
        }
        println("启动协程：3333333333")
    }

    /**
     * 1.执行task1.... [当前线程为：DefaultDispatcher-worker-1]
     * 2.执行task2.... [当前线程为：DefaultDispatcher-worker-1]
     * 3.task1 = one  , task2 = two , 耗时 3020 ms  [当前线程为：main]
     * 
     *  从上面结果可以看出，多个withContext是串行执行，如上代码执行顺序为先执行task1再执行task2，共耗时两个任务的所需时间的总和。
     *  这是因为withContext是个 suspend 函数，当运行到 withContext 时所在的协程就会挂起，直到withContext执行完成后再执行下面的方法。
     *  所以withContext可以用在一个请求结果依赖另一个请求结果的这种情况。
     */
    private fun test02 () {
        CoroutineScope(Dispatchers.Main).launch {
            val time1 = System.currentTimeMillis()
            val task1 = withContext(Dispatchers.IO) {
                delay(2000)
                Log.e("TAG", "1.执行task1.... [当前线程为：${Thread.currentThread().name}]")
                "one"  //返回结果赋值给task1
            }
            val task2 = withContext(Dispatchers.IO) {
                delay(1000)
                Log.e("TAG", "2.执行task2.... [当前线程为：${Thread.currentThread().name}]")
                "two"  //返回结果赋值给task2
            }
            Log.e("TAG", "task1 = $task1  , task2 = $task2 , 耗时 ${System.currentTimeMillis()-time1} ms  [当前线程为：${Thread.currentThread().name}]")
        }
    }

    /**
     * 2.执行task2.... [当前线程为：DefaultDispatcher-worker-4]
     * 1.执行task1.... [当前线程为：DefaultDispatcher-worker-4]
     * task1 = one  , task2 = two , 耗时 2007 ms  [当前线程为：main]
     *
     * 改为用async后，运行结果耗时明显比使用withContext更短，且看到与withContext不同的是，task2比task1优先执行完成 。
     * 所以说 async 的任务都是并行执行的。
     */
    private fun test03 () {
        CoroutineScope(Dispatchers.Main).launch {
            val time1 = System.currentTimeMillis()

            val task1 = async(Dispatchers.IO) {
                delay(2000)
                Log.e("TAG", "1.执行task1.... [当前线程为：${Thread.currentThread().name}]")
                "one"  //返回结果赋值给task1
            }

            val task2 = async(Dispatchers.IO) {
                delay(1000)
                Log.e("TAG", "2.执行task2.... [当前线程为：${Thread.currentThread().name}]")
                "two"  //返回结果赋值给task2
            }

            Log.e("TAG", "task1 = ${task1.await()}  , task2 = ${task2.await()} , 耗时 ${System.currentTimeMillis() - time1} ms  [当前线程为：${Thread.currentThread().name}]")
        }
    }
}