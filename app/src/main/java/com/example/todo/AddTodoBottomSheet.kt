package com.example.todo

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.todo.database.MyDataBase
import com.example.todo.database.model.Todo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputLayout
import java.time.Month
import java.util.*
import java.util.Calendar as Calendar

class AddTodoBottomSheet : BottomSheetDialogFragment() {
    lateinit var titleLayout: TextInputLayout
    lateinit var detailsLayout: TextInputLayout
    lateinit var chooseDate: TextView
    lateinit var addTodo: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        titleLayout = requireView().findViewById(R.id.title_container)
        detailsLayout = requireView().findViewById(R.id.details_container)
        addTodo = requireView().findViewById(R.id.add_button)
        chooseDate = requireView().findViewById(R.id.choose_date)

        chooseDate.setText(
            "" + calender.get(Calendar.DAY_OF_MONTH) + "/"
                    + calender.get(Calendar.MONTH) + "/" + calender.get(Calendar.YEAR)
        )
        chooseDate.setOnClickListener {
            showDatePicker()
        }

        addTodo.setOnClickListener {
            var title = titleLayout.editText?.text.toString()
            var details = detailsLayout.editText?.text.toString()
            if (validateForm(title, details)) {

                insertTodo(title, details)
            }

        }
    }

    private fun insertTodo(title: String, details: String) {
        var todo = Todo(name = title, description = details, date = calender.time, isDone = false)
        MyDataBase.getInstance(requireContext().applicationContext).todoDao()
            .addTodo(todo)// TODO: 12/16/2021
        Toast.makeText(requireContext(), "Todo Added Sucssefully", Toast.LENGTH_LONG).show()
        dismiss()
    }


    private fun validateForm(title: String, details: String): Boolean {
        var isValid = false
        if (titleLayout.editText?.text.toString().isBlank()) {
            titleLayout.error = "Please enter todo title "
        } else {
            titleLayout.error = null
            isValid = true;
        }
        if (detailsLayout.editText?.text.toString().isBlank()) {
            detailsLayout.error = "Please enter todo title "
        } else {
            detailsLayout.error = null
            isValid = true;
        }
        return isValid
    }

    val calender: Calendar = Calendar.getInstance()
    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            requireContext(),
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    calender.set(Calendar.YEAR, year)
                    calender.set(Calendar.MONTH, month)
                    calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    chooseDate.text = "" + dayOfMonth + "/" + month + "/" + year
                }
            },
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }
}

