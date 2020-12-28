package Controller;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import Models.ViewModels.CourseInfoVM;

public class FillSpinner<T> {
    public FillSpinner(Context context, Spinner spinner , List<T> CSVM){
        ArrayAdapter<T> adapter = new ArrayAdapter<T>(context,
                android.R.layout.simple_spinner_item,CSVM);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
