package www.ctrip.com.androiddatepickertimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private Calendar mCalendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取一个日历对象
        mCalendar = Calendar.getInstance();
        //获取年月日时分秒的信息
        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH) + 1;
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        minute = mCalendar.get(Calendar.MINUTE);
        //设置标题
        setTitle(year +"-"+ month + "-" + day + "-" + hour + "-" + minute);

        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);

        mDatePicker.init(year, mCalendar.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year +"-"+ (monthOfYear + 1) + "-" + dayOfMonth);
            }
        });

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay + ":" + minute);
            }
        });

        //日期选择器对话框
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setTitle(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        },year,mCalendar.get(Calendar.MONTH),day).show();


        //时间选择器对话框
        new  TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay + ":" + minute);
            }
        },hour,minute,true).show();
    }
}
