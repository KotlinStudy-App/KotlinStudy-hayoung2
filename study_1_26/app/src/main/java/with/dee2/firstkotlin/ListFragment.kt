package with.dee2.firstkotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    var fragmentMainActivity: FragmentMainActivity? =null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentMainActivity=context as FragmentMainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =inflater.inflate(R.layout.fragment_list, container, false)
        view.btnNext.setOnClickListener{
            fragmentMainActivity?.goDetail()
        }

        return view
    }

}