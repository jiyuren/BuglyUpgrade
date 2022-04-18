# BuglyUpgrade

### 兼容升级到 gradle 4.2.0, gradle-6.7.1-bin 
#### com.tencent.bugly:tinker-support:1.2.3

### gradle 升级到4.2.0 时lombok 与kapt 不兼容，无法编译生成getter，setter方法，折中处理将实体类改成kotlin data class，或者就不使用kotlin，
或者将所有的实体类放到另外一个module中


