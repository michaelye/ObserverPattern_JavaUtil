package com.michael.observerpattern_javautil;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 实现的是java。util.Observer这个接口，而不是自定义的接口
 * 这个接口提供了一个update方法，用来接收主题传递过来的数据
 * 
 * */
public class ObserverButton extends Button implements Observer{
	
	public ObserverButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ObserverButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ObserverButton(Context context) {
		super(context);
	}
	
	private int progress = 0;
	/**
	 * 将本类注册成为一个观察者，本来这个最好是在构造器中进行，但是由于构造器的参数限制，因此写一个方法用来注册
	 * 这个方法需要首先被调用
	 * 
	 * */
	public void registerObserver(Observable observable)
	{
		observable.addObserver(this);
	}
	
	/**
	 * 取消监听
	 * 
	 * */
	public void unRegisterObserver(Observable observable)
	{
		observable.deleteObserver(this);
	}
	
	/**
	 * 这里接收主题的通知
	 * 
	 * */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		//这个是“拉”方法
		if(arg0 instanceof ObservableImpl){
			
			ObservableImpl subjectImpl = (ObservableImpl)arg0;
			this.progress = subjectImpl.getProgress();//需要自己去主题中取数据
			display();
		}
		
		//这个是“推”方法
//		if(arg1 instanceof Integer){
//			
//			this.progress = (Integer)arg1;
//			display();
//		}
		
	}
	
	/**
	 * 显示数据
	 * 
	 * */
	private void display()
	{
		this.setText(""+progress);
	}
}
















