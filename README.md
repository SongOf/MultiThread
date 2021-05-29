#java并发示例

##计数器实现方案
```aidl
    1、 synchronized
    2、 volatile + cas
    3、 原子类，其原理也是volatile + cas
    4、 LongAdder类，性能比cas要好 高并发适用
```