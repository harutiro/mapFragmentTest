package io.github.harutiro.mapfragmenttest

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_maps, container, false)

        view.findViewById<Button>(R.id.goToSampleFragment)?.setOnClickListener{
            replaceFragment(SampleFragment())
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
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