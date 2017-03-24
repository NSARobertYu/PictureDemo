# 美图Demo
## 闪光灯、摄像头改变
使用Camera.Parameters的FlashMode切换闪光灯；
再通过CameraInfo.facing找到摄像头,使用open方法打开对应的摄像头
## 相册
通过FilePutputStream和BufferedOutputStream保存，bimmap压缩

## 缩放与拖动
ACTION_MOVE判断中通过MotionEvent.getPointerCount获取手指数是否大于1，设置缩或放
根据上述若手指数为1使用event.getRaw获得坐标设置拖动

## 大小裁剪
利用Rect和Canvas画出四个角和边框，根据Motion.getEvent
的触发事件，获取手指抬起时的位置

## 旋转
seekbar控制旋转角度,旋转后的图片获取到drawable再转为bitmap利用Matrix作为旋转参数

## 反转
创建Matrix对象，将Matrix的Values值与原来水平相反，再将完成后的值给bitmap，完成左右反转
将Values值与原来上下相反则完成上下反转

## 自定义边框
Canvas和Paint设置边框颜色和宽度，获取Imageview的drawable转为bitmap，利用Paint将边框颜色设置与自定义Imageview相同颜色

## 画板涂鸦
自定义SurfaceView，重写onTouch，以ACTION_DOWN、ACTION_MOVE、ACTION_UP记录滑动线路

## 水印
bitmap放到Canvas，创建Paint设置Tyoeface，将原图和需要的文字赋给canvas，再显示到另一张图上

## 饱和度、色相、亮度
seekbar控制饱和度,bitmap放到Canvas，定义一个ColorMatrixColorFiler的Saturation值，定义Paint修改colorfilter颜色变还属性，通过canvas绘制,实现饱和度填充;
seekbar控制色相,设置Rotate中三原色分量在色轮角度，通过canvas绘制,实现色相填充;
seekbar控制亮度,设置Scale中三原色分量比例，通过canvas绘制,实现亮度填充;

## 保存图片
指定保存目录path,通过FileOutputStream完成操作。
MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,null,null)保存到系统相册。
