## 1days (1~8)

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



## 2~4 days (9~19) 

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



## 5days (20~ 22)

---------------------------------------------



