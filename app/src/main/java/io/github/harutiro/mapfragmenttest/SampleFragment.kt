package io.github.harutiro.mapfragmenttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class SampleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // フラグメントで表示する画面をlayoutファイルからインフレートする
        // フラグメントで表示する画面をlayoutファイルからインフレートする
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<Button>(R.id.goToMapFragment)?.setOnClickListener{
            replaceFragment(MapsFragment())
        }


        return view
    }

    // 表示させるFragmentを切り替えるメソッドを定義（表示したいFragmentを引数として渡す）
    private fun replaceFragment(fragment: Fragment) {
        // フラグメントマネージャーの取得
        val manager: FragmentManager? = fragmentManager // アクティビティではgetSupportFragmentManager()?
        // フラグメントトランザクションの開始
        val transaction: FragmentTransaction = manager!!.beginTransaction()
        // レイアウトをfragmentに置き換え（追加）
        transaction.replace(R.id.fragment, fragment)
        // 置き換えのトランザクションをバックスタックに保存する
        transaction.addToBackStack(null)
        // フラグメントトランザクションをコミット
        transaction.commit()
    }


}