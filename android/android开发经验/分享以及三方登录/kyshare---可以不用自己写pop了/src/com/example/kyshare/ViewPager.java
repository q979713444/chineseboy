package com.example.kyshare;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

import com.example.kyshare.AutoScrollThread.OnAutoScrollListener;

public class ViewPager extends View implements OnGestureListener{
	private LayoutCreator layoutCreator = new LayoutCreator(this.getContext());
	private ParamsCreator paramsCreator = new ParamsCreator(this.getContext());
	private GestureDetector mGestureDetector;
	private Paint paint = new Paint();
    private List<ShareWrapper> shareWrappers;
    private AutoScrollThread autoScrollThread;
    private int selfWidth;
    private int selfHeight;
    private int headerHeight;
    private int contentHeight;
    private int bottomHeight;
    
    private int columnNum = 4;//每页列数
	private int rowNum = 3;//每页行数
    
    private List<RuntimePageWrapper> runtimePageWrappers = new ArrayList<RuntimePageWrapper>();
    private List<RuntimePageWrapper> copyRuntimeWrappers;
    private int pageNo = 1;//当前页码
    
    
    
	public ViewPager(Context context) {
		super(context);
		mGestureDetector = new GestureDetector(this);
	}
	public ViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(this);
	}
	public ViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mGestureDetector = new GestureDetector(this);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {		
		this.selfWidth = layoutCreator.getWidth();
		this.selfHeight = layoutCreator.getHeight();
		setMeasuredDimension(this.selfWidth, this.selfHeight);
	}
	/**
	 * 初始化参数
	 */
	private void initParams(){
		this.headerHeight = this.paramsCreator.getHeaderHeight();
		this.bottomHeight = this.paramsCreator.getBottomHeight();
		this.contentHeight = this.selfHeight - this.headerHeight - this.bottomHeight;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(0xFFFFFFFF);
		try {
			drawHeader(canvas);
			drawContent(canvas);
			drawBottom(canvas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 画header
	 */
	private void drawHeader(Canvas canvas){
		//画底下的横线
		paint.setColor(0xFF6E6E6E);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);		
		RectF rectF = new RectF(0, this.headerHeight-paramsCreator.getHeaderBottomLineHeight(), this.selfWidth, this.headerHeight);
		canvas.drawRect(rectF, paint);

		paint.setTextSize(paramsCreator.getHeaderTextSize());
		paint.setColor(0xFF676767);
		String text = "请选择分享到的应用";
		FontMetricsInt fontMetrics = paint.getFontMetricsInt();
		int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
		int txtWidth = (int)paint.measureText(text);
		paint.setColor(0xFF000000);
		canvas.drawText(text, this.selfWidth/2 - txtWidth/2, (this.headerHeight-txtHeight)/2-fontMetrics.ascent, paint);	
	}	
	/**
	 * 画content
	 */
	private void drawContent(Canvas canvas){	
		if(this.runtimePageWrappers.size() == 0)
			return ;
		
		for(int i = this.runtimePageWrappers.size()-1; i>=0; i--){			
			RuntimePageWrapper runtimeWrapper = this.runtimePageWrappers.get(i);
			drawThePage(canvas, runtimeWrapper);
		}
	}
	/**
	 * 画某一页
	 */
	private void drawThePage(Canvas canvas, RuntimePageWrapper runtimeWrapper){
		int textSize = this.paramsCreator.getAppNameTextSize();
		int spacing = this.paramsCreator.getSpacingOfIconAndName();
		paint.setColor(0xFF6E6E6E);
		paint.setStyle(Paint.Style.STROKE);
		paint.setAntiAlias(true);
		paint.setTextSize(textSize);
		FontMetricsInt fontMetrics = paint.getFontMetricsInt();
		int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
		int iconfHeight = this.paramsCreator.getAppIconSize();
		int totalHeight = iconfHeight + spacing + txtHeight;
		
		int tdHeight = this.contentHeight/rowNum;
		int tdWidth = this.selfWidth/columnNum;
		List<ShareWrapper> wrappers = runtimeWrapper.shareWrappers;
		
		for(int i = 0; i<wrappers.size(); i++){
			ShareWrapper wrapper = wrappers.get(i);
			int wrapperTop = runtimeWrapper.top + i/columnNum * tdHeight;
	    	int wrapperLeft = runtimeWrapper.left + i%columnNum * tdWidth;
			//画icon
			Bitmap bitmap = wrapper.getBitmap();			
			int top = wrapperTop + (tdHeight/2 - totalHeight/2);
			int left = wrapperLeft + (tdWidth/2 - bitmap.getWidth()/2);		
			canvas.drawBitmap(bitmap, left, top, null);
			//画name
			top += iconfHeight;
			top += spacing;

			int txtWidth = (int)paint.measureText(wrapper.getName());
			left = wrapperLeft + (tdWidth/2 - txtWidth/2);
			canvas.drawText(wrapper.getName(), left, top -fontMetrics.ascent, paint);
		}
	}
	/**
	 * 画bottom
	 */
	private void drawBottom(Canvas canvas){
		paint.setColor(0xFFFFFFFF);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		RectF rectF = new RectF(0, this.selfHeight-this.bottomHeight, this.selfWidth, this.selfHeight);
		canvas.drawRect(rectF, paint);
	}
	
	/**
	 * 触碰事件
	 */
	@Override
    public boolean onTouchEvent(MotionEvent event) { 
		mGestureDetector.onTouchEvent(event); 
		if(event.getAction() == MotionEvent.ACTION_UP)
			executeUp();			
		return true;
    }
	///手势监听
	@Override
	public boolean onDown(MotionEvent event) {
		cancelAutoScrollThread();
		//之所以要拷贝副本，是因为onScroll中的差值(changeY)是根据onDown时计算的
		copyRuntimeWrappers = copyRuntimeWrappers();	
		return true;
	}
	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2, float arg2, float arg3) {
		int changeX = (int)(event2.getX() - event1.getX());
		int orientation = changeX < 0 ? AutoScrollThread.LEFT : AutoScrollThread.RIGHT;
		executeFling(orientation);
		return true;
	}
	@Override
	public void onLongPress(MotionEvent event) {		
	}
	@Override
	public boolean onScroll(MotionEvent event1, MotionEvent event2, float arg2, float arg3) {
		int changeX = (int)(event2.getX() - event1.getX());
		executeScroll(changeX);
		return true;
	}
	@Override
	public void onShowPress(MotionEvent event) {
	}
	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		executeClick(x, y);
		return true;
	}
	/**
	 * 处理滚动事件
	 */
	public void executeScroll(int changedX){
		if(this.runtimePageWrappers.size() <= 1)
			return ;
		if(changedX == 0)
			return ;
		if(changedX < 0){//手指向左移动
			int pageLeft = copyRuntimeWrappers.get(copyRuntimeWrappers.size()-1).left + changedX;//改变前最后页的left值
			if(pageLeft < 0){
				changedX = 0 - copyRuntimeWrappers.get(copyRuntimeWrappers.size()-1).left;
			}
			moveRuntimeWrappers(changedX);
			return ;
		}
		//向右移动
		int pageLeft = copyRuntimeWrappers.get(0).left + changedX;//改变前第一页的left值
		if(pageLeft > 0){
			changedX = 0 - copyRuntimeWrappers.get(0).left;
		}
		moveRuntimeWrappers(changedX);
	}
	/**
	 * 执行手指抬起事件
	 */
	public void executeUp(){
		//获得当前页
		RuntimePageWrapper currentPageWrapper = getCurrentPageWrapper();
		if(currentPageWrapper == null)
			return ;		
		copyRuntimeWrappers = copyRuntimeWrappers();
		if(currentPageWrapper.left<= this.selfWidth/2){//向左滚动
			openAutoScroll(AutoScrollThread.LEFT, currentPageWrapper.left);
		}else{
			openAutoScroll(AutoScrollThread.RIGHT, this.selfWidth - currentPageWrapper.left);
		}
	}
	/**
	 * 执行Fling事件
	 */
	public void executeFling(int orientation){
		cancelAutoScrollThread();//取消滚动
		//获得当前页
		RuntimePageWrapper currentPageWrapper = getCurrentPageWrapper();
		if(currentPageWrapper == null)
			return ;
		copyRuntimeWrappers = copyRuntimeWrappers();
		if(orientation == AutoScrollThread.LEFT){//向左fling
			openAutoScroll(orientation, currentPageWrapper.left);
		}else{//向右fling			
			openAutoScroll(orientation, this.selfWidth - currentPageWrapper.left);
		}
	}
	/**
	 * click事件处理
	 */
	public void executeClick(int x, int y){
		if(this.runtimePageWrappers.size() == 0)
			return ;
		int textSize = this.paramsCreator.getAppNameTextSize();
		int spacing = this.paramsCreator.getSpacingOfIconAndName();
		paint.setColor(0xFF000000);
		paint.setStyle(Paint.Style.STROKE);
		paint.setAntiAlias(true);
		paint.setTextSize(textSize);
		FontMetricsInt fontMetrics = paint.getFontMetricsInt();
		int txtHeight = fontMetrics.bottom - fontMetrics.ascent;
		int iconfHeight = this.paramsCreator.getAppIconSize();
		int totalHeight = iconfHeight + spacing + txtHeight;
		int tdHeight = this.contentHeight/rowNum;
		int tdWidth = this.selfWidth/columnNum;
		
		for(RuntimePageWrapper runtimeWrapper : this.runtimePageWrappers){
			List<ShareWrapper> wrappers = runtimeWrapper.shareWrappers;
			for(int i = 0; i<wrappers.size(); i++){
				ShareWrapper wrapper = wrappers.get(i);
				int wrapperTop = runtimeWrapper.top + i/columnNum * tdHeight;
		    	int wrapperLeft = runtimeWrapper.left + i%columnNum * tdWidth;
		    	Bitmap bitmap = wrapper.getBitmap();			
				int top = wrapperTop + (tdHeight/2 - totalHeight/2);
				int left = wrapperLeft + (tdWidth/2 - bitmap.getWidth()/2);	
				int right = left + bitmap.getWidth();
				int bottom = top + totalHeight;
				if(x>=left && x<=right && y>=top && y<=bottom){
					if(wrapper != null && wrapper.getOnClickListener() != null){
						wrapper.getOnClickListener().onClick();
					}
				    return ;	
				}	
			}
			
		}
	}
	/**
	 * 获得当前RuntimePageWrapper
	 */
	private RuntimePageWrapper getCurrentPageWrapper(){
		//获得当前页
		RuntimePageWrapper currentPageWrapper = null;
		for(RuntimePageWrapper wrapper : this.runtimePageWrappers){
			if(wrapper.left >=0 && wrapper.left<this.selfWidth){
				currentPageWrapper = wrapper;
				break;
			}
		}		
		return currentPageWrapper;
	}
	/**
	 * 开启自动滚动
	 */
	private void openAutoScroll(int orientation, int moveSize){
		if(autoScrollThread != null && autoScrollThread.isScrolling())//正在滚动中
			return ;
		System.out.println("ViewPage openAutoScroll");
		final Handler upHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {	
				int changeX = (Integer)msg.obj;
				executeScroll(changeX);
			}			
		};
		OnAutoScrollListener listener = new OnAutoScrollListener(){
			@Override
			public void onScroll(int changeX) {
				Message msg = new Message();
				msg.obj = changeX;
				upHandler.sendMessage(msg);
			}
			@Override
			public void onEnd() {
			}			
		};
		if(orientation == AutoScrollThread.LEFT){//向左fling
			autoScrollThread = new AutoScrollThread(listener, AutoScrollThread.LEFT, moveSize);
		}else{//向右fling			
			autoScrollThread = new AutoScrollThread(listener, AutoScrollThread.RIGHT, moveSize);
		}
		autoScrollThread.start();
	}
	
	/**
	 * 取消upThread
	 */
	private void cancelAutoScrollThread(){
		if(this.autoScrollThread != null){
			this.autoScrollThread.stopScroll();
		}
	}	
	
	/**
	 * 移动menus
	 * @param changedY:触摸滑动点与触摸开始点的差值
	 * @param copyMenus为滑动前menus的副本
	 */
	private void moveRuntimeWrappers(int changedX){
		for(int i = 0; i<this.runtimePageWrappers.size(); i++){
			RuntimePageWrapper wrapper = this.runtimePageWrappers.get(i);
			int left = copyRuntimeWrappers.get(i).left + changedX;
			wrapper.left = left;
		}
		this.invalidate();
	}
	
	/**
	 * 拷贝runtimeWrappers副本
	 */
	public List<RuntimePageWrapper> copyRuntimeWrappers(){
		List<RuntimePageWrapper> copyRuntimeWrappers = new ArrayList<RuntimePageWrapper>();
		for(int i = 0; i<this.runtimePageWrappers.size(); i++){
			RuntimePageWrapper oldWrapper = this.runtimePageWrappers.get(i);
			RuntimePageWrapper wrapper = new RuntimePageWrapper(oldWrapper.left, oldWrapper.top, oldWrapper.height, oldWrapper.width, oldWrapper.shareWrappers);
			copyRuntimeWrappers.add(wrapper);
		}
		return copyRuntimeWrappers;
	}
	
    /**
     * 设置图标
     * @param icons
     */
	public void setWrappers(List<ShareWrapper> icons) {
		this.shareWrappers = icons;
		this.selfWidth = layoutCreator.getWidth();
		this.selfHeight = layoutCreator.getHeight();
		initParams();

		if(icons == null || icons.size() == 0)
			return ;
		int pageSize = columnNum * rowNum;//每页多少个
		//第一页
		int pageNo = 1;
		int top = this.headerHeight;
		List<ShareWrapper> shareWrappers = new ArrayList<ShareWrapper>();
		for(int i = 0; i<icons.size(); i++){
			ShareWrapper wrapper = icons.get(i);
			Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), wrapper.getIconId());
			int iconSize = paramsCreator.getAppIconSize();
	    	bitmap = BitmapUtil.resizeBitmap(bitmap, iconSize);
	    	wrapper.setBitmap(bitmap);
			shareWrappers.add(wrapper);
			if(shareWrappers.size() == pageSize || i == icons.size()-1){
				int left = (pageNo - 1) * this.selfWidth;
				RuntimePageWrapper pageWrapper = new RuntimePageWrapper(left, top, this.contentHeight, this.selfWidth, shareWrappers);
				this.runtimePageWrappers.add(pageWrapper);
				pageNo++;
				shareWrappers = new ArrayList<ShareWrapper>();
			}
		}
	}
}
