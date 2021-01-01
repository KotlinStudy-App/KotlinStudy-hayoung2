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



## 5~ 7 일차 (20 ~ 22)

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

  

