AssignmentDemo
=====

這是一個實作題目的練習


### 資料表

##### Weather

```kotlin
@PrimaryKey
weatherId: Long,

title: String

```

##### DailyWord

```kotlin
@PrimaryKey
dailyId: Long,

word: String

```

##### WeatherWeek

```kotlin
@PrimaryKey
weekId: Long,

description: String,

weatherId: Long

```

關聯式 Data Class

```kotlin
@Embedded
weather: Weather,

@Relation
weatherWeeks: MutableList<WeatherWeek>,

```

###需求
1.取得台中市 最近一週的天氣預報, 天氣預報顯示在列表上, 天氣標題顯示在上方展開畫面

2.下滑刷新，呼叫兩個API，更新資料庫並同步更新畫面

3.長按列表其中一項，跳出刪除提示並確認後，刪除資料庫項目並同步更新畫面
