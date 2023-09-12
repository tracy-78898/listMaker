package com.raywenderlich.listmaker

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class MainViewModel(private val sharedPrefences:SharedPreferences) : ViewModel()
{
    lateinit var  onListAdded: (() -> Unit )
   lateinit var  list: TaskList
   lateinit var onTaskAdded: (( ) -> Unit)

     val lists:MutableList<TaskList> by lazy{
         retrieveLists()
     }
   fun addTask(task: String) {
       list.tasks.add(task)
       onTaskAdded.invoke()
   }

    private fun retrieveLists():MutableList<TaskList>
    {
    val sharedPreferencesContents = sharedPrefences.all
    val taskLists = ArrayList<TaskList>()
    for (taskList in sharedPreferencesContents) {
        val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
        val list = TaskList(taskList.key, itemsHashSet)
        taskLists.add(list)
    }
        return taskLists
    }
    fun refreshLists() {
        lists.clear()
        lists.addAll(retrieveLists())
    }
    fun saveList(list: TaskList) {
        sharedPrefences.edit().putStringSet(list.name,
            list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }
    fun updateList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        lists.add(list)
    }


}



