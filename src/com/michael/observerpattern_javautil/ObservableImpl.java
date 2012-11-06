package com.michael.observerpattern_javautil;

import java.util.Observable;


/**
 * 这个类继承自java.util.Observable，注意：这个类是一个类，而不是接口
 * 这个是java API提供的类，我们就不需要自己去实现观察者对象的管理了，即不需要使用List来管理以及发送通知了。
 * 
 * 但是这个类最大的缺点就是它是一个类，而不是接口，扩展性较差
 * 
 * */
public class ObservableImpl extends Observable{

	int progress = 0;
	
	/**
	 * SeekBar通过这个方法与Subject进行交互
	 * 
	 * */
	public void setData(int progress)
	{
		this.progress = progress;
		setChanged();//在调用notifyObservers方法之前，这个方法一定要调用！否则没有效果！看看源码就知道了，这里有一个boolean标志位
		notifyObservers(progress);
	}
	
	/**
	 * 供“拉”方法取数据
	 * 
	 * */
	public int getProgress()
	{
		return this.progress;
	}
	
}
