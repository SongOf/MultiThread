#java并发示例

##计数器实现方案
```aidl
    1、 synchronized
    2、 volatile + cas
    3、 原子类，其原理也是volatile + cas
    4、 LongAdder类，性能比cas要好 但是sum值不准确 最终一致性 高并发适用
```

##线程池状态转换
![](docs\imgs\1.png)
##线程池拒绝策略
```aidl
    AbortPolicy :抛出异常 默认
    CallerRunsPolicy :调用线程执行
    DiscardOldestPolicy :丢弃队列中最老的任务，再次尝试提交
    DiscardPolicy ：直接丢弃
```