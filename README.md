## 1~ 4일차(1~8)

-----------------------

- 변수, 상수

  ```kotlin
  var a=1 //변수 
  a=3
  val b=2 //상수 ex. final 
  ```

  

- 조건식 when

  ```kotlin
  var value:String="hayoung2"
  
  when(value){
      "hayoung2"-> Log.d("name :"," $value !")
      else -> Log.d("name :"," X ")
  }
  ```

  

- Array, Collection (List, Set, Map)

  ```kotlin
  var arr:IntArray =IntArray(5) // 배열
  
  // 정의 후 변경 ㄴㄴ
  var list =listOf(1,3,5) //컬렉션 ListOf 컬렉션 생성 함수 
  
  // 쓰기 가능 ListOf 에 메소드 기본적 포함 및 추가적으로 add remove
  var mutableList = mutableListOf<String>() 
  mutableList.add("dd")
  
  var map = mutableMap<String,String>() //set같은 
  map.put("hayoung","111")
  ```

  

- 반복문 

  ```kotlin
  for ( i in 1..10){
      //1~ 값 나옴 
  }
  // while , do while 
  ```



## 4~ 5 일차  (9 ~ 19) 

--------------------------------------

- 함수

  ```kotlin
  fun testFun(p1:Int, p2:String) :String {
      return "p1 =${p1}, p2=${p2}"
  }
  ```

- 클래스

  ```kotlin
  //인터페이스도 똑같 
  interface models{ }
  // 추상 클래스 해당 메소드 자식에서 정의 
  open class Parent{
      open fun testFun() //open 있어야 재정의 가능 
  }
  
  //상속 받음. 
  class Test : Parent() {
      init {
         //초기화 
      }
      companion object {
          // 클래스 인스턴스없이 어떤 클래스 내부에 접근하고자 할 때 
         // 클래스 내부 객체 선언 시 사용 
          // 클래스가 메모리에 적재될 때 자동으로 함께 생성 바로 참조 
          // 변수 할당 가능 
          //하나만 사용 (인터페이스에도 사용 가능)
      }
      override fun testFun() {
          // 실행한 코드 정의 
      }
      //클래스 안에 클래스 
      inner class innerClass{
          
      }
  }
  //val test=Test()  로 불러오기
  // test.testFun() 
  ```

- Activity 간 값 전달

  ```kotlin
  val intent =Intent(this,TestActivity::class.java)
  intent.putExtra("p1","값 전달함")
  startActivity(intent)
  
  //////////////////////
  val p1=intent.getStringExtra("p1") //값 받음
  //intent에 값 전달 
  setResult(Activity.RESULT_OK,intent) 
  // 다른 Activity에 값 받음
  override fun onActivityResult(~){~}
  ```



## 5~ 7 일차 (20 ~ 23)

---------------------------------------------

- Spinner

```kotlin
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        spinner2.adapter=adapter
        spinner2.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem=data[p2]
                value.text=selectedItem
            }

        }
```

- Fragment

```kotlin
// ListFragment는 그냥 설정한 값 Fragment() 상속 받고 거기에 안에 들어갈 layout 만들고 설정 
// 
val fragment =ListFragment()
        // 삽입 트랜잭션 시작
        val transaction =supportFragmentManager.beginTransaction()
        // 트랜잭션을 통해서 프ㅐ그먼트 삽입
        transaction.add(R.id.frameLayout,fragment)
        //커밋
        transaction.commit()
```



- 권한 설정(카메라)

  ```kotlin
  //AndroidManifest.xml 에 먼저 설정
  // 해당 메소드로 해당권한 획득 확인함.
  val cameraPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
  if(cameraPermission==PackageManager.PERMISSION_GRANTED){
          // 권한 승인
  }else{     ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.CAMERA),FLAG_CAMERA)
        // 권한 요청 
  }
  
  ```

  

## 8~10 일차(24~26) 

----------------------------------------------

  20210111 스터디에서 부족한 점 추가로

-  Context ?

```markdown
abstract 클래스이며 구현은 안드로이드 시스템에서 제공. 
Context를 통해 리소스나 클래스에 접근 가능. 
ex. Activity 실행 ,Intent, API 호출 등  this.~
Context에 정의된 인스턴스 함수를 호출해야 가능. ex. System.out.println 

```



- 파일 처리

```kotlin
//StringBuffer String보다 무겁고 느림 작업이 많을 경우만 사용. 없는 경우 String
var strBuffer =StringBuffer()
val reader = FileReader(path)
val writer=FileWriter(path) // 생성자에 여러가지 , 파일이름(String), 파일 (경로), 
//mkdir()
//buffer로 입출력 하는 이유, 사용하지않을 경우, 키보드 입력 시 바로 전달
// buffer로 하면 하나의 메모리 특정 공간에 입력하고 프로그램에 전달 (한번에)
//효율적이고 성능면에서 더 좋음 
val buffer =f.bufferedWriter(~) // 내용을 메모리에 특정 공간에 저장 ,추ㅜㄹ력 
val buffer1=f.bufferedReader(~) // 입력 
buffer.write("DFSDFSdf") //입력
// 자원관리때문에
// 입력할 경우, 정상적으로 처리안되는 경우 많음. 
buffer.close() 
```



- SharedPreferences  : 어플리케이션 내에 File 형태로 Data를 저장.  해당 어플리케이션이 삭제되기 전까지 Data 보관(지우면 다 삭제 )

```kotlin
// 간단한 갑을 저장하거나 검색, 키-값 읽고 쓰기 사용. 
// 해당 context뿐만아니라 앱의 모든 Context에서 메소드 호출 가능. 
//p1 : 식별 가능한 걍 이름 , p2: 읽고 쓰기 권한 관련 Mode 
val sp=getSharedPreferences("storage",Context.MODE_PRIVATE)
 val editor =sp.edit()
        editor.putString(key,value)
        editor.apply()
```



- android와 SQLite DB 
- 안드로이드 플랫폼 리눅스 + HAL + C/C++라이브러리,android Runtime(예전 JVM) ,JAVA API Framework, SystemApps 
- java와 100%  호환 

```kotlin
// DB 파일 생성 및 버전 관리하는 클래스 
import android.databse.sqlite.SQLiteOpenHelper

class SqliteHelper (context: Context, name:String, version:Int)
    : SQLiteOpenHelper(context,name,null,version){
        // 최초로 한번 호출 (context에 db가 없으면 실행)
        // open을 통해서 db를 열거나 readDat~ getWrite 가 없으면 실행 x
    override fun onCreate(db: SQLiteDatabase?) {
        val create="create table memo(`no` Integer primary key,content  text, datetime  integer )" 
        db?.execSQL(create) // sql 실행, return 값 없음. 
        //select 가 아니어야하고 단일 SQL 

    }
//DB version 이 변경될 때 호출 
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //테이블에 변경사항이 있을 경우 호출됨..
        // SqliteHelper() 의 생성자를 호출할 때 기존 데이터베이스와 version 을 비교해서 높으면 호추

    }
        //onOpen (선택) 
        
        fun test() {
            // 값 입력 
           WritableDatabase.insert(테이블명, 해당 값들)
            // DB 개체 닫음. 
           WrittableDatabase.close()
        }
}
```

- rawQuery vs execSQL

```kotlin
db?.execSQL(create)  //select 가 아니어야하고 단일 SQL 문 사용 

//Cursor 객체 리턴. 
val list = db.rawQuery
```

- Cursor 객체

```kotlin
// Cursor 객체는 실제 DB 값을 가지고 마치 테이블의 행(Row)를 하나하나 이동하며 참조
Cursor.moveToNext() // Curwor를 다음 행으로 이동 
Cursor.getInt() //DB 테이블 실제 DATA 갖옴 


```

- SqliteHelper

```kotlin
val helper = SpliteHelper(this,"memo",1)
// context, DB 이름 (왜 강의에서 sqlite.sql이라고 했는지 이해 불가(설명 ㄴ) , 버전 )
```

