# Digital_Image_Processsing
Some knowledge of DIP during the learning and some code of DIP project. 

Java语言实现的数字图像处理的相关函数和方法，其中：

Mian 类在 MainProcessing.java 中。

Image Format Transform.java:
1.图片的路径赋给了String变量 path, 修改所在的文件路径只需要修改变量 path 的赋值。
2.根据提示输入 path 文件夹下的图片名。
3.IP 是 ImageProcess 类的一个对象，通过调用 Image Process 类中的方法来完成对图片的操作。

Preprocess.java:
此类顾名思义是主要是包含一些预处理的方法和一些会高频使用的非图片处理类的方法，譬如写文件操作。

ImageProcess.java:
此类中包含图片的相关属性以及图片处理的方法。
1.PowerTransform 方法的参数是double类型， 取值范围就是double类型的取值范围。
2.LinerGreyTransform 方法的参数是int类型，取值范围理论上来说也是int的取值范围，但是为了让输入的值有意义，n 的绝对值最好不超过0xff即256。当n>0变亮，当n<0变暗。
3.PowerGreyTransform 方法的参数是double类型，取值范围是大于零的双精度浮点数。当n>1变暗，当0<n<1变亮。

ImageFilter.java:
此类继承了 ImageProcess 类，其中包含一些滤波器相关的方法。
1.ArithmeticAverage 方法（算数均值滤波器）的参数是int类型且都大于零，子图窗口的大小为 m × n，而且满足 m 与 n 应当满足 2k + 1, k 属于自然数这一形式。否则会向下取近似运算。 
2.StatisticalFilter 方法  （统计排序滤波器）的参数是int类型，其中，参数为1则使用中值滤波器， 参数为2则使用最大值滤波器，参数为3则使用最小值滤波器，参数为4则使用中点滤波器。
3.AdaptiveFilter 方法 （自适应中值滤波器）一共有四个两对int类型的参数，第一对表示滤波器最大的长与宽，第二队表示滤波器初始状态的长与宽。所要满足的要求参照1方法。
