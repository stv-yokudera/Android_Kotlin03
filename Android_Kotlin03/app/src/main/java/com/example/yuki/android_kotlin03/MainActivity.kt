package com.example.yuki.android_kotlin03

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*

enum class SendType constructor(val int: Int) {
    Mail(0),
    Upload(1);

    companion object {

        // enumへの変換を行う
        fun fromInt(index: Int): SendType {
            // indexがenumの範囲外の値であった場合、"Mail"を返却する
            return values().firstOrNull() { it.int == index } ?: Mail
        }
    }

    val isMail: Boolean
        get() = this == Mail

    val isUpload: Boolean
        get() = this == Upload
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        conditionalBranch()
//        loop()
//        loop2()
        loop3()
    }

    /**
     * 条件分岐
     */
    private fun conditionalBranch() {

        val rand = Random().nextInt(100)

        var result = ""
        if (rand % 2 == 0) {
            result = "偶数"
        } else {
            result = "奇数"
        }
        println(result)

        val rand2 = Random().nextInt(100)
        // 上のif〜else文による代入を1行で表現
        val result2 = if (rand2 % 2 == 0) "偶数" else "奇数"
        println(result2)

        val intValue = 0
        val sendType = SendType.fromInt(intValue)
        when (sendType) {
            SendType.Mail -> println("Mail")
            SendType.Upload -> println("Upload")
        }

        val intValue2 = 1
        val sendTypeResult = when(SendType.fromInt(intValue2)) {
            SendType.Mail -> "Mail"
            SendType.Upload -> "Upload"
        }
        println("sendTypeResult: $sendTypeResult")
    }

    /**
     * 各ループ処理
     */
    private fun loop() {

        val programmingLanguageList = listOf("Kotlin", "Java", "Swift", "Objective-C")
        for (programmingLanguage in programmingLanguageList) {
            println(programmingLanguage)
        }

        // 最適化によってindicesは実際にはオブジェクトを生成しないのでパフォーマンス劣化はない
        for (i in programmingLanguageList.indices) {
            println("programmingLanguageList: $programmingLanguageList[i]")
        }

        for ((index, value) in programmingLanguageList.withIndex()) {
            println("the element at $index is $value")
        }

        // 10 ~ 1でループする
        var count = 10
        while (count > 0) {
            println("count: $count")
            count--
        }

        // 生成した乱数が3の倍数でなければループを続ける
        do {
            val rand = Random().nextInt(1000)
            println("rand: $rand")
        } while (rand % 3 != 0 )
    }

    /**
     * return処理
     */
    private fun loop2() {

        val intList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        intList.forEach {
            // returnは、直近の関数を抜ける。ラムダではない。
            // forEachでなくloop2から抜けてしまう
            if (it == 5) {
                return
            }
            println(it)
        }

        // ここまで来ない
        println("loop2おわり")
    }

    /**
     * returnとラベル
     */
    private fun loop3() {

        val intList = listOf(1, 2, 3, 4, 5)

        // ラムダを抜ける場合は、ラベルを定義して使用する
        intList.forEach lam@ {
            // ラベルを使用してforEachを抜ける(continue)
            if (it == 3) {
                return@lam // continue
            }
            println(it)
        }

        intList.forEach {
            // 暗黙のラベル（ラムダが渡される関数名）を使用してforEachを抜ける(continue)
            if (it == 3) {
                return@forEach // continue
            }
            println(it)
        }

        run loop@ {
            intList.forEach {
                if (it == 3) {
                    return@loop // break
                }
                println(it)
            }
        }

        // ここまで来る
        println("loop3おわり")
    }
}
