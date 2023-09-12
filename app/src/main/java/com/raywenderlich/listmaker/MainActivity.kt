package com.raywenderlich.listmaker

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.raywenderlich.listmaker.R.string
import com.raywenderlich.listmaker.databinding.MainActivityBinding
import com.raywenderlich.listmaker.ui.detail.ListDetailActivity
import com.raywenderlich.listmaker.databinding.MainFragmentBinding
import com.raywenderlich.listmaker.ui.detail.ui.detail.ListDetailFragment

class MainActivity : AppCompatActivity(),
MainFragment.MainFragmentInteractionListener {


    private lateinit var binding: MainActivityBinding


    private lateinit var viewModel: MainViewModel

    override fun listItemTapped(list: TaskList) {
        showListDetail(list)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            // 1
            var mainFragment = MainFragment.newInstance()
            mainFragment.clickListener = this
            // 2
            val fragmentContainerViewId: Int = if
                                                       (binding.mainFragmentContainer == null) {
                R.id.detail_container
            } else {
                R.id.main_fragment_container
            }





                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add(fragmentContainerViewId, mainFragment)
                }
            }



        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))
        ).get(MainViewModel::class.java)

        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

                companion object {
                    fun newInstance()=MainFragment
                    val INTENT_LIST_KEY = "list"
                    val LIST_DETAIL_REQUEST_CODE = 123
                }


   private fun showCreateListDialog() {
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT
        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            dialog.dismiss()
            val taskList = TaskList(listTitleEditText.text.toString())
            viewModel.saveList(taskList)
            showListDetail(taskList)
        }

        builder.create().show()
    }


    private fun showListDetail(list: TaskList) {
        if (binding.mainFragmentContainer == null) {
            val listDetailIntent = Intent(this,
                ListDetailActivity::class.java)
            listDetailIntent.putExtra(INTENT_LIST_KEY, list)
            startActivityForResult(listDetailIntent,
                LIST_DETAIL_REQUEST_CODE)
        } else {
            val bundle = bundleOf(INTENT_LIST_KEY to list)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.list_detail_fragment_container,
                    ListDetailFragment::class.java, bundle, null
                )
                binding.fabButton.setOnClickListener {
                    showCreateListDialog()
                }
            override fun onBackPressed() {
                    // 1
                    val listDetailFragment =

                        supportFragmentManager.findFragmentById(R.id.list_detail_fragment_container)
                    // 2
                    if (listDetailFragment == null) {
                        super.onBackPressed()
                    } else {
                        // 3
                        title = resources.getString(R.string.app_name)
                        // 4
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            remove(listDetailFragment)
                        }

                        // 5
                        binding.fabButton.setOnClickListener {
                            showCreateListDialog()
                        }
                    }
                }






            }


        }


    }
}

