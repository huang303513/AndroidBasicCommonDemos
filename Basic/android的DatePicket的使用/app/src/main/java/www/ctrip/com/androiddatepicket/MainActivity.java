package www.ctrip.com.androiddatepicket;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener {
    public TextView textView;
    public DatePicker datePicker;
    public TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        //datepicker的初始化
        datePicker.init(2001,1,25,this);
        //24小时制
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(this);//注册事件
    }
    @Override
    /**
     * 日期控件的触发
     */
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),
                timePicker.getHour(),timePicker.getMinute());
        SimpleDateFormat format = new SimpleDateFormat("YYYY年MM月dd日 HH:mm");
        textView.setText(format.format(calendar.getTime()));
    }
    @Override
    /**
     * 时间控件的触发
     */
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(MainActivity.this,"hourofDay:" + hourOfDay + "minute:" + minute,1).show();
    }
}
