package br.htaf.com.apresentation.loadscreen

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import br.htaf.com.R
import kotlinx.android.synthetic.main.load_screen.*

class LoadSreean: DialogFragment() {

    companion object{
        fun getInstace():LoadSreean{
            return LoadSreean();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(DialogFragment.STYLE_NORMAL, R.style.LoadScreenTheme)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.load_screen, container, false )
    }
}