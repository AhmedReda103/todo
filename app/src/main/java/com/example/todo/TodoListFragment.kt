package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.database.MyDataBase
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

class TodoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    lateinit var recyclerView: RecyclerView
    lateinit var calenderView: MaterialCalendarView
    var adapter: TodosRecyclerAdapter = TodosRecyclerAdapter(null)

    override fun onResume() {
        super.onResume()
        getTodoListFromDB()
    }

    //there are more felexability in calendr than Date
    var date = Calendar.getInstance()

    //    fun clearCalenderTime(){
//        date.clear(Calendar.HOUR)
//        date.clear(Calendar.SECOND)
//        date.clear(Calendar.MILLISECOND)
//        date.clear(Calendar.MINUTE)
//    }
    fun getTodoListFromDB() {
        //if(context==null)return ;
        val todoList =
            MyDataBase.getInstance(requireContext().applicationContext).todoDao()
                .getTodosByDate(date.clearTime().time)//return time in milli second
        adapter.changeData(todoList.toMutableList())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = requireView().findViewById(R.id.recycler_view)
        calenderView = requireView().findViewById(R.id.calendarView)
        calenderView.selectedDate = CalendarDay.today()
        recyclerView.adapter = adapter
        calenderView.setOnDateChangedListener { widget, calenderDay, selected ->

            date.set(Calendar.DAY_OF_MONTH, calenderDay.day)
            date.set(Calendar.MONTH, calenderDay.month - 1)
            date.set(Calendar.YEAR, calenderDay.year)
            getTodoListFromDB()
        }

    }


}
