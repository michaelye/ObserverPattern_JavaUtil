package com.michael.observerpattern_javautil;

import java.util.Observable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;


/**
 * 使用java API中的Observable类来实现观察者模式
 * 
 * */
public class MainActivity extends Activity {

	
	private SeekBar sbDataChanger;
	private ObservableImpl subjectImpl;
	private ObserverButton  btnObserverOne;
	private ObserverButton  btnObserverTwo;
	private ObserverButton  btnObserverThree;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        subjectImpl = new ObservableImpl();
        sbDataChanger = (SeekBar)findViewById(R.id.sb_data_changer);
        btnObserverOne = (ObserverButton)findViewById(R.id.btn_observer_one);
        btnObserverTwo = (ObserverButton)findViewById(R.id.btn_observer_two);
        btnObserverThree = (ObserverButton)findViewById(R.id.btn_observer_three);
        
        //这里需要在3个按钮被实例化的时候都调用registerObserver(Observable observable)方法
        //一便一开始就实现监听，本来这个是要放在构造器中来做的，但是Button的构造器不能自定义，因此放在这里来调用
        btnObserverOne.registerObserver(subjectImpl);
        btnObserverTwo.registerObserver(subjectImpl);
        btnObserverThree.registerObserver(subjectImpl);
        
        sbDataChanger.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// SeekBar在这个地方与主题进行交互
				//当SeekBar的progress改变的时候，主题中的数据也改变，主题再对所有监听了该主题的观察者一一通知，从而达到改变数据的目的
				//主题在这个地方相对于桥梁的作用，不仅这样，它还负责观察者的注册和取消注册
				subjectImpl.setData(progress);
			}
		});
        
        btnObserverOne.setOnClickListener(clickListener);
        btnObserverTwo.setOnClickListener(clickListener);
        btnObserverThree.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.btn_observer_one:
				cancelRegister(v);
				break;
			case R.id.btn_observer_two:
				cancelRegister(v);
				break;
			case R.id.btn_observer_three:
				cancelRegister(v);
				break;
			default:
				break;
			}
		}
	};
	
	/**
	 * 取消监听
	 * 
	 * */
	private void cancelRegister(View view)
	{
		ObserverButton observerButton = (ObserverButton)view;
		observerButton.unRegisterObserver((Observable)subjectImpl);
		Toast.makeText(this, "一个按钮取消了监听", Toast.LENGTH_SHORT).show();
	}
	
}
