package com.guoenbo.library.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.qiji.fingertipfinancial.R;
import com.qiji.fingertipfinancial.util.CustomUtil;
import com.qiji.fingertipfinancial.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 自定义日期选择组件
 * 
 * @invoke mDialog = new CustomDatePicker(CustomDatePickerActivity.this,
 *         CustomDatePicker.DateStyle.DS_YYYY_MM_DD);
 *         mDialog.setInputDate("2011-12-23"); mDialog.show();
 *         mDialog.setDateInterface(new DateInterface() { public void
 *         setDate(String sdate) { tvDate.setText(sdate); } });
 */
public class CustomDatePicker extends Dialog implements View.OnClickListener{

	private ListView lvYear, lvMonth, lvDay,lvHour,lvMin,lvSecond;
	private String selectYear, selectMonth, selectDay,selectHour,selectMin,selectSecond;
	private List<String> listYear, listMonth, listDay,listHour,listMin,listSecond;
	private String[] arrYear, arrMonth, arrDay31, arrDay30, arrDay29, arrDay28,arrHour,arrMin,arrSecond;
	private Context mContext;
	private DateStyle mDateStyle;
	private String InputDate = null; // 输入日期
	private String SelectDate; // 输出日期
	private String[] arrDate, arrTime;
	private View layout;
	private MonthDays mMonthDays;
	private DateInterface mDateInterface;
	private LayoutInflater inflater;
	private Resources resources;
	private TextView dateview;
	private SimpleDateFormat sdf;
	private TextView btnDate,btnTime;
	private Button btnSure,btnCancle;
	private ViewSwitcher viewSwitch;
	private LinearLayout date_title;
	Calendar calendar = Calendar.getInstance();

	public void setDateInterface(DateInterface DateInterface) {
		this.mDateInterface = DateInterface;
	}

	private int i_month_num, i_year_num, i_day_num,i_hour_num,i_min_num,i_second_num;
	// listview标识
	final int CONS_YEAR_ID = 1;
	final int CONS_MONTH_ID = 2;
	final int CONS_DAY_ID = 3;
	final int CONS_HOUR_ID = 4;
	final int CONS_MIN_ID = 5;
	final int CONS_SECOND_ID = 6;

	/**
	 * 
	 * @param context
	 * @param DateStyle 风格
	 * @param iTheme 自定义标题栏View
	 */
	@SuppressWarnings("deprecation")
	public CustomDatePicker(Context context, DateStyle DateStyle, int iTheme) {
		super(context, R.style.custom_window_dialog);
		this.mContext = context;
		this.mDateStyle = DateStyle;
		inflater = getLayoutInflater();
		layout = inflater.inflate(R.layout.datepicker_main, null);
		layout.findViewById(R.id.time).setVisibility(View.GONE);//首先隐藏时分选择器
		date_title = (LinearLayout) layout.findViewById(R.id.date_title);
		viewSwitch=(ViewSwitcher) layout.findViewById(R.id.switchView);
		btnDate=(TextView) layout.findViewById(R.id.btnDate);
		btnTime=(TextView) layout.findViewById(R.id.btnTime);
		btnSure=(Button) layout.findViewById(R.id.btnSure);
		btnCancle=(Button) layout.findViewById(R.id.btnCancle);
		btnSure.setOnClickListener(this);
		btnCancle.setOnClickListener(this);
		btnDate.setOnClickListener(this);
		btnTime.setOnClickListener(this);
		TextView tvTitle=(TextView) layout.findViewById(R.id.dialog_tvTitle);
		tvTitle.setText("请选择时间");
		this.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        this.setContentView(layout);
        WindowManager windowManager = ((Activity) context).getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = (int)(display.getWidth()-50); //设置宽度
		getWindow().setAttributes(lp);
		resources = context.getResources();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(InputDate.indexOf(' ')!=-1) {
			int index=InputDate.indexOf(' ');
			String date=InputDate.substring(0, index);
			String time=InputDate.substring(index+1);
			String[] dates=date.split("-");
			String[] times=time.split(":");
			switch(dates.length) {
			case 1:
				btnDate.setText(selectYear);
				selectYear = dates[0];
				break;
			case 2:
				selectYear = dates[0];
				selectMonth = dates[1];
				btnDate.setText(selectYear+"-"+selectMonth);
				break;
			case 3:
				selectYear = dates[0];
				selectMonth = dates[1];
				selectDay = dates[2];
				
				String temp=selectYear+"-"+selectMonth+"-"+selectDay+"";
				java.sql.Date d=java.sql.Date.valueOf(selectYear+"-"+selectMonth+"-"+selectDay);
				Calendar cal= Calendar.getInstance();
				cal.setTime(d);
				int day = cal.get(Calendar.DATE);
		    	int month = (cal.get(Calendar.MONTH)+1) ;
		    	int year = cal.get(Calendar.YEAR);
				temp=temp+" "+ CustomUtil.getChineseWeek(year,month,day);
				btnDate.setText(temp);
				break;
			}
			switch(times.length) {
			case 1:
				selectHour=times[0];
				btnTime.setText(selectHour+"");
				break;
			case 2:
				selectHour=times[0];
				selectMin=times[1];
				btnTime.setText(selectHour+":"+selectMin+"");
				break;
			case 3:
				selectHour=times[0];
				selectMin=times[1];
				selectSecond=times[2];
				btnTime.setText(selectHour+":"+selectMin+":"+selectSecond+"");
				break;
			}
		} else {
			arrDate = InputDate.split("-");
			switch (arrDate.length) {
			case 1:
				selectYear = arrDate[0];
				break;
			case 2:
				selectYear = arrDate[0];
				selectMonth = arrDate[1];
				break;
			case 3:
				selectYear = arrDate[0];
				selectMonth = arrDate[1];
				selectDay = arrDate[2];
				break;
			}
			arrTime = InputDate.split(":");
			switch (arrTime.length) {
			case 1:
				selectHour = arrTime[0];
				break;
			case 2:
				selectHour = arrTime[0];
				selectMin = arrTime[1];
				break;
			case 3:
				selectHour = arrTime[0];
				selectMin = arrTime[1];
				selectSecond = arrTime[2];
				break;
			}
		}

		// 判断输出日期类型
		switch (mDateStyle) {
		case DS_YYYY_MM_DD:
			setYear();
			setMonth();
			setDay();
			layout.findViewById(R.id.chooser).setVisibility(View.GONE);
			break;
		case DS_YYYY_MM:
			setYear();
			setMonth();
			setDay();
			layout.findViewById(R.id.LayoutDay).setVisibility(View.GONE);
			layout.findViewById(R.id.chooser).setVisibility(View.GONE);
			break;
		case DS_YYYY:
			setYear();
			setMonth();
			setDay();
			layout.findViewById(R.id.LayoutMonth).setVisibility(View.GONE);
			layout.findViewById(R.id.LayoutDay).setVisibility(View.GONE);
			layout.findViewById(R.id.chooser).setVisibility(View.GONE);
			break;
		case DS_YYYY_MM_DD_HH_MM_SS:
			setYear();
			setMonth();
			setDay();
			setHour();
			setMin();
			setSecond();
			break;
		case DS_YYYY_MM_DD_HH_MM:
			setYear();
			setMonth();
			setDay();
			setHour();
			setMin();
			layout.findViewById(R.id.LayoutSecond).setVisibility(View.GONE);
			break;
		case DS_YYYY_MM_DD_HH:
			setYear();
			setMonth();
			setDay();
			setHour();
			layout.findViewById(R.id.LayoutMin).setVisibility(View.GONE);
			layout.findViewById(R.id.LayoutSecond).setVisibility(View.GONE);
			break;
		//单独选择时分例如 10:20
		case DS_HH_MM:
			setHour();
			setMin();
			layout.findViewById(R.id.LayoutSecond).setVisibility(View.GONE);
			layout.findViewById(R.id.time).setVisibility(View.VISIBLE);
			layout.findViewById(R.id.date).setVisibility(View.GONE);
			layout.findViewById(R.id.chooser).setVisibility(View.GONE);
			break;
		//单独选择时分秒例如 10:20:30
		case DS_HH_MM_SS:
			setHour();
			setMin();
			setSecond();
			layout.findViewById(R.id.time).setVisibility(View.VISIBLE);
			layout.findViewById(R.id.date).setVisibility(View.GONE);
			layout.findViewById(R.id.chooser).setVisibility(View.GONE);
			break;
		}
	}

	public void setYear() {
		lvYear = (ListView) findViewById(R.id.listViewYear);
		lvYear.setDivider(null);
		if (arrYear == null || arrYear.length == 0) {
			arrYear = resources.getStringArray(R.array.year);
		}
		i_year_num = arrYear.length;
		listYear = Arrays.asList(arrYear);

		DateAdapter adapterYear = new DateAdapter(mContext, CONS_YEAR_ID, null);
		lvYear.setAdapter(adapterYear);
		lvYear.setOnScrollListener(new OnlvScrollListener(CONS_YEAR_ID));
		lvYear.setOnItemClickListener(new SmoothItemListener(adapterYear));

		int index = listYear.indexOf(selectYear);
		lvYear.setSelection(index + i_year_num * 10 - 2);
	}

	public void setMonth() {
		lvMonth = (ListView) findViewById(R.id.listViewMonth);
		lvMonth.setDivider(null);
		arrMonth = resources.getStringArray(R.array.month);
		i_month_num = arrMonth.length;
		listMonth = Arrays.asList(arrMonth);

		DateAdapter adapterMonth = new DateAdapter(mContext, CONS_MONTH_ID,
				null);
		lvMonth.setAdapter(adapterMonth);

		lvMonth.setOnScrollListener(new OnlvScrollListener(CONS_MONTH_ID));
		int index = listMonth.indexOf(selectMonth);
		lvMonth.setSelection(index + i_month_num * 50 - 2);
	}

	public void setDay() {
		lvDay = (ListView) findViewById(R.id.listViewDay);
		lvDay.setDivider(null);
		setDayList(selectYear, selectMonth);
		lvDay.setOnScrollListener(new OnlvScrollListener(CONS_DAY_ID));
	}

	private void setHour() {
		lvHour = (ListView) findViewById(R.id.listViewHour);
		lvHour.setDivider(null);
		arrHour = resources.getStringArray(R.array.hour);
		i_hour_num = arrHour.length;
		listHour = Arrays.asList(arrHour);

		DateAdapter adapterHour = new DateAdapter(mContext, CONS_HOUR_ID,
				null);
		lvHour.setAdapter(adapterHour);

		lvHour.setOnScrollListener(new OnlvScrollListener(CONS_HOUR_ID));
		int index = listHour.indexOf(selectHour);
		lvHour.setSelection(index + i_hour_num * 50 - 2);
	}

	private void setSecond() {
		lvSecond = (ListView) findViewById(R.id.listViewSecond);
		lvSecond.setDivider(null);
		arrSecond = resources.getStringArray(R.array.time);
		i_second_num = arrSecond.length;
		listSecond = Arrays.asList(arrSecond);

		DateAdapter adapterSecond = new DateAdapter(mContext, CONS_SECOND_ID,
				null);
		lvSecond.setAdapter(adapterSecond);

		lvSecond.setOnScrollListener(new OnlvScrollListener(CONS_SECOND_ID));
		int index = listSecond.indexOf(selectSecond);
		lvSecond.setSelection(index + i_second_num * 50 - 2);
	}
	
	private void setMin() {
		lvMin = (ListView) findViewById(R.id.listViewMin);
		lvMin.setDivider(null);
		arrMin = resources.getStringArray(R.array.time);
		i_min_num = arrMin.length;
		listMin = Arrays.asList(arrMin);

		DateAdapter adapterMin = new DateAdapter(mContext, CONS_MIN_ID,
				null);
		lvMin.setAdapter(adapterMin);

		lvMin.setOnScrollListener(new OnlvScrollListener(CONS_MIN_ID));
		int index = listMin.indexOf(selectMin);
		lvMin.setSelection(index + i_min_num * 50 - 2);
	}
	
	private class SmoothItemListener implements OnItemClickListener {
		
		private Object adapter;
		
		public SmoothItemListener(Object adapter) {
			this.adapter = adapter;
		}
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
	
			DateAdapter adapterYear = (DateAdapter) adapter;
			View view = lvYear.getChildAt(2);
			TextView textView = (TextView) view.findViewById(R.id.txt_item);
			String current = textView.getText().toString();
			
			selectYear = adapterYear.getItemText(arg2);
			
			int currentPos = Integer.parseInt(current);
			int yearPos = Integer.parseInt(selectYear);
			
			int offset = currentPos-yearPos;
			if(offset == 149) {
				offset = -1;
			}
			else if(offset == -149) {
				offset = 1;
			}
			else if(offset == 148) {
				offset = -2;
			}
			else if(offset== -148) {
				offset = 2;
			}
			
			lvYear.smoothScrollToPositionFromTop(lvYear.getFirstVisiblePosition(), view.getHeight()*offset);

			setDayList(selectYear, selectMonth);
			String date1=selectYear+(selectMonth==null?"":"-"+selectMonth)+(selectDay==null?"":"-"+selectDay+"");
			if(!selectYear.equals("")&&!selectMonth.equals("")&&!selectDay.equals("")) {
				java.sql.Date d=java.sql.Date.valueOf(selectYear+"-"+selectMonth+"-"+selectDay);
				Calendar cal= Calendar.getInstance();
				cal.setTime(d);
				int day = cal.get(Calendar.DATE);
		    	int month = (cal.get(Calendar.MONTH)+1) ;
		    	int year = cal.get(Calendar.YEAR);
				date1=date1+" "+CustomUtil.getChineseWeek(year,month,day);
			}
			btnDate.setText(date1);
		}
		
	}

	public void setDayList(String sYear, String sMonth) {
		if(!StringUtil.isEmpty(sMonth)){
			int iDays = getMonthDays(sYear, sMonth);
			switch (iDays) {
			case 31:
				arrDay31 = resources.getStringArray(R.array.day31);
				i_day_num = arrDay31.length;
				listDay = Arrays.asList(arrDay31);
				mMonthDays = MonthDays.CONS_DAY_31;
				break;
			case 30:
				arrDay30 = resources.getStringArray(R.array.day30);
				i_day_num = arrDay30.length;
				listDay = Arrays.asList(arrDay30);
				mMonthDays = MonthDays.CONS_DAY_30;
				break;
			case 29:
				arrDay29 = resources.getStringArray(R.array.day29);
				i_day_num = arrDay29.length;
				listDay = Arrays.asList(arrDay29);
				mMonthDays = MonthDays.CONS_DAY_29;
				break;
			case 28:
				arrDay28 = resources.getStringArray(R.array.day28);
				i_day_num = arrDay28.length;
				listDay = Arrays.asList(arrDay28);
				mMonthDays = MonthDays.CONS_DAY_28;
				break;
			}

			DateAdapter adapterDay = new DateAdapter(mContext, CONS_DAY_ID, mMonthDays);
			lvDay.setAdapter(adapterDay);

			int index;
			// 如果选中年月中没有这一天，自动跳到选中年月的最后一天
			if (Integer.parseInt(StringUtil.isEmptyToString(selectDay, "1")) > i_day_num) {
				index = listDay.indexOf(i_day_num + "");
			} else {
				index = listDay.indexOf(selectDay);
			}
			index = index + i_day_num * 30;
			lvDay.setSelection(index - 2);
			selectDay = lvDay.getItemAtPosition(index).toString();
		} else {
			selectMonth = "";
			selectDay = "";
		}
	}

	class OnlvScrollListener implements OnScrollListener {

		private int DateFlag;

		public OnlvScrollListener(int DateFlag) {
			this.DateFlag = DateFlag;
		}

		public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
		}

		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_IDLE:
				int h = view.getHeight();
				int m = h / 2;
				int ifirst = view.getFirstVisiblePosition();
				int icount = view.getChildCount();
				for (int i = 0; i < icount; i++) {
					int itop = view.getChildAt(i).getTop();
					int ibottom = view.getChildAt(i).getBottom();
					if (m >= itop && m <= ibottom) {
						int pos = ifirst + i;
						switch (DateFlag) {
						case CONS_YEAR_ID:
							selectYear = view.getItemAtPosition(pos).toString();
							setDayList(selectYear, selectMonth);
							String date1=selectYear+(selectMonth==null?"":"-"+selectMonth)+(selectDay==null?"":"-"+selectDay+"");
							if(!selectYear.equals("")&&!selectMonth.equals("")&&!selectDay.equals("")) {
								java.sql.Date d=java.sql.Date.valueOf(selectYear+"-"+selectMonth+"-"+selectDay);
								Calendar cal= Calendar.getInstance();
								cal.setTime(d);
								int day = cal.get(Calendar.DATE);
						    	int month = (cal.get(Calendar.MONTH)+1) ;
						    	int year = cal.get(Calendar.YEAR);
								date1=date1+" "+CustomUtil.getChineseWeek(year,month,day);
							}
							btnDate.setText(date1);
							break;
						case CONS_MONTH_ID:
							selectMonth = view.getItemAtPosition(pos).toString();
							setDayList(selectYear, selectMonth);
							String date2=selectYear+(selectMonth==null?"":"-"+selectMonth)+(selectDay==null?"":"-"+selectDay+"");
							if(!selectYear.equals("")&&!selectMonth.equals("")&&!selectDay.equals("")) {
								java.sql.Date d=java.sql.Date.valueOf(selectYear+"-"+selectMonth+"-"+selectDay);
								Calendar cal= Calendar.getInstance();
								cal.setTime(d);
								int day = cal.get(Calendar.DATE);
						    	int month = (cal.get(Calendar.MONTH)+1) ;
						    	int year = cal.get(Calendar.YEAR);
								date2=date2+" "+CustomUtil.getChineseWeek(year,month,day);
							}
							btnDate.setText(date2);
							break;
						case CONS_DAY_ID:
							selectDay = view.getItemAtPosition(pos).toString();
							String date3=selectYear+(selectMonth==null?"":"-"+selectMonth)+(selectDay==null?"":"-"+selectDay+"");
							if(!selectYear.equals("")&&!selectMonth.equals("")&&!selectDay.equals("")) {
								java.sql.Date d=java.sql.Date.valueOf(selectYear+"-"+selectMonth+"-"+selectDay);
								Calendar cal= Calendar.getInstance();
								cal.setTime(d);
								int day = cal.get(Calendar.DATE);
						    	int month = (cal.get(Calendar.MONTH)+1) ;
						    	int year = cal.get(Calendar.YEAR);
								date3=date3+" "+CustomUtil.getChineseWeek(year,month,day);
							}
							btnDate.setText(date3);
							break;
						case CONS_HOUR_ID:
							selectHour= view.getItemAtPosition(pos).toString();
							btnTime.setText(selectHour+(selectMin==null?"":":"+selectMin)+(selectSecond==null?"":":"+selectSecond));
							break;
						case CONS_MIN_ID:
							selectMin= view.getItemAtPosition(pos).toString();
							btnTime.setText(selectHour+(selectMin==null?"":":"+selectMin)+(selectSecond==null?"":":"+selectSecond+""));
							break;
						case CONS_SECOND_ID:
							selectSecond= view.getItemAtPosition(pos).toString();
							btnTime.setText(selectHour+(selectMin==null?"":":"+selectMin)+(selectSecond==null?"":":"+selectSecond+""));
							break;
						}
						view.setSelection(pos - 2);
						break;
					}
				}
				break;
			case OnScrollListener.SCROLL_STATE_FLING:
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				break;
			}
		}
	}

	public String getInputDate() {
		return InputDate;
	}

	public void setInputDate(String inputDate) {
		if (inputDate == null || "".equals(inputDate)) {
			String dateFormat = "yyyy-MM-dd HH:mm:ss";
			switch (mDateStyle) {
			case DS_YYYY_MM_DD:
				dateFormat = "yyyy-MM-dd";
				break;
			case DS_YYYY_MM:
				dateFormat = "yyyy-MM";
				break;
			case DS_YYYY:
				dateFormat = "yyyy";
				break;
			case DS_YYYY_MM_DD_HH:
				dateFormat = "yyyy-MM-dd HH";
				break;
			case DS_YYYY_MM_DD_HH_MM:
				dateFormat = "yyyy-MM-dd HH:mm";
				break;
			case DS_YYYY_MM_DD_HH_MM_SS:
				dateFormat = "yyyy-MM-dd HH:mm:ss";
				break;
			case DS_HH_MM:
				dateFormat = "HH:mm";
				break;
			case DS_HH_MM_SS:
				dateFormat = "HH:mm:ss";
				break;
			}
			sdf = new SimpleDateFormat(dateFormat);
			InputDate = sdf.format(new Date());
		} else {
			InputDate = inputDate;
		}
	}

	// 输出日期风格
	public static enum DateStyle {
		DS_YYYY_MM_DD_HH_MM_SS,DS_YYYY_MM_DD_HH_MM, DS_YYYY_MM_DD_HH, DS_YYYY_MM_DD, DS_YYYY_MM, DS_YYYY,DS_HH_MM,DS_HH_MM_SS;
	}

	// 月份类型
	public enum MonthDays {
		CONS_DAY_31, CONS_DAY_30, CONS_DAY_29, CONS_DAY_28
	}

	// 根据年月判断天数
	public int getMonthDays(String sYear, String sMonth) {
		String Date = sYear + "-" + sMonth;
		int iDays = 0;
		try {
			sdf = new SimpleDateFormat("yyyy-MM");
			calendar.setTime(sdf.parse(Date));
			iDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return iDays;
	}

	// 判断输入日期是否正确yyyy-mm-dd
	public boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	public interface DateInterface {
		public void setDate(String sdate);
	}

	class DateAdapter extends BaseAdapter {

		protected Context _context;
		private int _DateFlag;
		private MonthDays _MonthDays;

		public DateAdapter(Context context, int dateFlag, MonthDays monthDays) {
			this._context = context;
			this._DateFlag = dateFlag;
			this._MonthDays = monthDays;
		}

		public int getCount() {
			return Integer.MAX_VALUE;
		}

		public Object getItem(int position) {
			return getItemText(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View view, ViewGroup parent) {
			if (view == null) {
				view = inflater.inflate(R.layout.datepicker_item, null);
				dateview = (TextView) view.findViewById(R.id.txt_item);
				view.setTag(dateview);
			} else {
				dateview = (TextView) view.getTag();
			}

			String itemText = getItemText(position);
			dateview.setText(itemText);
			return view;
		}

		public String getItemText(int position) {
			String result = "";
			switch (_DateFlag) {
			case CONS_YEAR_ID:
				result = arrYear[position % arrYear.length];
				break;
			case CONS_MONTH_ID:
				result = arrMonth[position % arrMonth.length];
				break;
			case CONS_DAY_ID:
				switch (_MonthDays) {
				case CONS_DAY_31:
					result = arrDay31[position % arrDay31.length];
					break;
				case CONS_DAY_30:
					result = arrDay30[position % arrDay30.length];
					break;
				case CONS_DAY_29:
					result = arrDay29[position % arrDay29.length];
					break;
				case CONS_DAY_28:
					result = arrDay28[position % arrDay28.length];
					break;
				}
				break;
			case CONS_HOUR_ID:
				result=arrHour[position % arrHour.length];
				break;
			case CONS_MIN_ID:
				result=arrMin[position % arrMin.length];
				break;
			case CONS_SECOND_ID:
				result=arrSecond[position % arrSecond.length];
				break;
			}
			return result;
		}
	}

	public void setYearRange(int StartYear, int EndYear) {
		int iStartYear = 1901;
		int iEndYear = 2050;

		if (arrYear == null || arrYear.length == 0) {
			if (StartYear > 1000) {
				iStartYear = StartYear;
			}
			if (EndYear > 1000) {
				iEndYear = EndYear;
			}
			if (StartYear >= EndYear) {
				iStartYear = EndYear - 100;
			}
			int icount = iEndYear - iStartYear + 1;
			arrYear = new String[icount];
			for (int i = 0; i < icount; i++) {
				arrYear[i] = iStartYear + i + "";
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnDate:
			viewSwitch.showPrevious();
			layout.findViewById(R.id.time).setVisibility(View.GONE);
			layout.findViewById(R.id.date).setVisibility(View.VISIBLE);
			break;
		case R.id.btnTime:
			viewSwitch.showNext();
			layout.findViewById(R.id.time).setVisibility(View.VISIBLE);
			layout.findViewById(R.id.date).setVisibility(View.GONE);
			break;
		case R.id.btnSure:
			// 判断输出日期类型
			switch (mDateStyle) {
			case DS_YYYY_MM_DD:
				SelectDate = selectYear + "-" + selectMonth + "-" + selectDay;
				break;
			case DS_YYYY_MM:
				SelectDate = selectYear + "-" + selectMonth;
				break;
			case DS_YYYY:
				SelectDate = selectYear;
				break;
			case DS_YYYY_MM_DD_HH:
				SelectDate = selectYear + "-" + selectMonth + "-" + selectDay+" "+selectHour;
				break;
			case DS_YYYY_MM_DD_HH_MM:
				SelectDate = selectYear + "-" + selectMonth + "-" + selectDay+" "+selectHour+":"+selectMin;
				break;
			case DS_YYYY_MM_DD_HH_MM_SS:
				SelectDate = selectYear + "-" + selectMonth + "-" + selectDay+" "+selectHour+":"+selectMin+":"+selectSecond;
				break;
			case DS_HH_MM:
				SelectDate = selectHour+":"+selectMin;
				break;
			case DS_HH_MM_SS:
				SelectDate=selectHour+":"+selectMin+":"+selectSecond;
				break;
			default:
				SelectDate = selectYear + "-" + selectMonth + "-" + selectDay;
				break;
			}
			if (mDateInterface != null) {
				mDateInterface.setDate(SelectDate);
			}
			this.dismiss();
			break;
		case R.id.btnCancle:
			this.dismiss();
			break;
		}
	}

}
