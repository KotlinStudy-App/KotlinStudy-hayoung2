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

  

## 8 ~ 10 일차 (24 ~ 26) 

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



## 11 일차 (27 ~ 28)

-----------------------------

```kotlin
val STORAGE_PERMISSION =arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)

//시스템 권한 요청 코드 
ActivityCompat.requestPermissions(this,CAMERA_PERMISSION,FLAG_PERM_CAMERA)

//권한 체크 
val result = ContextCompat.checkSelfPermission(this, permission)

fun openCamera() {
    // 촬영된 사진 데이터 가져옴
    val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(intent,FLAG_REQ_CAMERA)
}
```



-  MediStore : 시스템 내에 제공하는 medai data db, Provider을 이용하여 사용가능

```kotlin
var values=ContentValues()
// (항목, 값 ) 넣음. 
values.put(MediaStore.Images.Media.DISPLAY_NAME,filename)
 
//현재 sdk 버전 ,  버전 코드 
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
    values.put(MediaStore.Images.Media.IS_PENDING,1)
}
// uri (Uniform Resource Identifier) 인터넷에 있는 자원을 나타내는 식별가능한 주소
// uri 하위개념 url, urn 
// Content Provider : 다른 앱에 DB 접근 
//UI에서 ContentProvider에 액세스하기 위해 UI의 Activity 또는 Fragment가 쿼리에 대해 CursorLoader를 호출하고 ContentResolver를 사용하여 ContentProvider를 가져옴 
// 새로운 행 삽입하고 해당 열에대한 Content uri를 반환 
// ContentResolver ? : 객체와 ContentProvider 객체와 통신 (ContentProvider 구현하는 클래스 인스터스 ) 클라이언트로부터 데이터 요청을 받아 요청된 작업 실행하고 반환,DB 조작
val url=contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)

// URI 데이터 액세스 
var descriper=contentResolver.openFileDescriptor(url,"w")
if (descriper!=null){
    //File or 데이터 쓰기위한 출력스트림 이미지 데이터와 같은 원시 바이트 스트림을 쓰기위한 것
    val fos=FileOutputStream(descriper.fileDescriptor)
    // fos에 압축하여 저장. 
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)
    fos.close()
    return url
}
```



## 12일차 (29 강)

- thread ? : 1 프로세스 1 개 이상 스레드 , 프로세스 내에서 실제로 작업 수행
- process : 시스템 상에서 실행 중인 프로그램, 독립된 메모리 공간 할당받음.

```kotlin
thread(start=true) {
  for (i in 0..100){
      Thread.sleep(1000)
  }
}//Looper :Message queue 에서 메시지 해당 값을 차례로 꺼내서 핸들러로 처리하도록 전달(앱이 실행 시 자동 생성(무한루프))

//Hanlder 인스턴스는 단일 스레드 or 특정 스레드의 메시지와 연결 
// Handler 역할 : msg나 실행파일을 어느 시점에 실행할지 예약, 스레드에 수행할 작업을 대기열에 추가함. 명령어 처리  (개발자가 생성)
  val handler =object : Handler() {
      //msg 수신하기 위해 구현 
      //대기열에 ㅊ가하기 위해 
      //
        override fun handleMessage(msg: Message) {
            // timer 수정하는 코드 
            // 메인쓰레드(UI ) 제어(변경시켜줌)
        }
    }
// 안드로이드 스레드에는 1) 메인스레드 2) 백그라운드 스레드
// 메인(UI Thread) : 화면 UI 처리, UI 이벤트 응답, 작업을 수 초내에 응당안할시 팝업창(응답 ㄴㄴ), 새로운 스레드 생성해서 처림
// 백그라운드 스레드 : 시간 내에 처리하기 어려운 작업 처리 
```



## 13일차 (30강) 

- AsyncTask는 Thread 나 Handler 같은 것과 다름 짧은 작업에 사용되어야 함. 

```kotlin
//동기 작업 ? 비동기 작업 ?
// 1. 보통 순차적으로 한 작업이 완료되면 다음 작업
// 2. 요청 -> 결과 같지않음 다른 작업 하면서 자원 효율적 사용 
// 비동기 작업 실행하면 4단계 거침
// 1. onPreExecute() : 작업이 실행되기 전 UI Thread 호출, 
// 2. doInBackground() : onPreExecute() 이 완료된 후 backgorund thread 호출. 오래 걸리는 것 여기다가 넣기. 계산결과 UI Thread에 게시됨
// 3. onProgressUpdate() : UI thread에서 호출되고 background가 계속 실행되는 동안 사용자 인터페이스에 진행상황 표시. 여기다가 막 로딩같은 거 넣으면 될듯. 
// 4. onPostExecute() : background 완료한 후 UI THread에서 호출 .결과는 매개변수로 전달. 
// 작업은 한번만 실행 가능. 
// Coruoutin 도 있음 이게 더 좋다는데.. 
fun downloadAndSetImage() {
        //interface 추상이라 코드 구현해야함
        //background 처리 후 main therad에 화면 처리
        //handler로 아래 함수 호출
        val asyncTask=object : AsyncTask<String,Void, Bitmap?>(){
            override fun doInBackground(vararg p0: String?): Bitmap? {
                    //주소에 파이프 연결이라고 생각
                    val stream = url.openStream()
                    bitmap = BitmapFactory.decodeStream(stream)
            }
//            //메인쓰레드 /포어그라운드 영역
//            override fun onProgressUpdate(vararg values: Void?) {
//                super.onProgressUpdate(*values)
//            }
            override fun onPostExecute(result: Bitmap?) {
                result?.let {
                    imagePreview.setImageBitmap(it)
                }
            }

        }
        asyncTask.execute(url) // UI Thread에 호출 
  }

```



## 14 ~ 16일차 ( 31강,32강)

- Service() : 다른 application 이 적용되어도 background에서 계속 실행. 프로세스 간 통신 or 네트워크 트랜잭션 등 오래 실행되는 작업 수행

```kotlin
//클라이언트가 서비스와 통신을 주고받기 위해 사용할 인터페이스를 제공
//다른 애플리케이션 구성요소를 도울 때까지만 유지되고 백그라운드에서 무한히 실행X
override fun onBind(intent: Intent): IBinder {
        return MyBinder()
}

// Service를 시작하도록 요청하는 경우 사용. 
// 2개 중 하나만 선택, 같이 사용할 때도 ㅇㅇ, 백그라운드에서 무한히 실행
//확실히 중지
override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action =intent?.action
        when(action) {
            ACTION_CREATE->create()
            ACTION_DELETE->delete()
        }
        return super.onStartCommand(intent, flags, startId)
}
```

```kotlin
    val connection =object:ServiceConnection{
        // Android 시스템이 클라이언트와 서비스 사이에 연결을 생성시
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            //bind되면 실행
            isService=true
            val binder=p1 as MyService.MyBinder
            myService =binder.getService()
        }
        //연결 ㄴㄴ
        override fun onServiceDisconnected(p0: ComponentName?) {
            //예외발생할 경우 호출되고 unbind는 아님
                isService=false
        }
```



## 33, 35 ,36강

```kotlin
 Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,id) // p1 이미 인코딩된 uri 를 id에 새로운 uri 만듦
// 안드로이드에서 view 만드는 방법
//LayoutInflater는 LayoutInflater.from(context)를 이용하여 얻음

```



```kotlin
getMapAsync(onMapReady(GoogleMap map)) // GoogleMap을 얻을 수 있음. call back 기다림 

//마커 옵션 설정 위치, 타이틀, 아이콘 설정  Google map 에 추가 
val marker=MarkerOptions().position(seoul).title("Seoul").icon(descriptor)
        mMap.addMarker(marker)
// 설정 
        val cameraOption =CameraPosition.Builder().target(seoul).zoom(17f).build()

        val camera=CameraUpdateFactory.newCameraPosition(cameraOption)
        mMap.moveCamera(camera)
```



```kotlin
priority=LocationRequest.PRIORITY_HIGH_ACCURACY // 가장 정확한 위치를 요청

```



## 37강

- Retrofit2 : 안드로이드에서 서버와 통신하기 위해 square사의 라이브러리, http통신에 활용하는 volley 나 asynctask 보다 효율이 좋음 

  반환되는 타입은 Call<객체타입설정> 형태

  HTTP 요청을 처리하는데 필요한 메소드 get,post,put,patch, delete

```kotlin
@GET("{api_key}/json/SeoulPublicLibraryInfo/1/{end}") //요청한 URI로부터 데이터를 받아오기 위해 사용하는 메소드

...
//앞에 코드는 요청을 정의하고 
retrofit = Retrofit.Builder()
            .baseUrl(SeoulOpenApi.DOMAIN)
//Json형식의 파일을 Json to kotlin class로 변경 
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//비동기로 Request를 보내고 Response가 돌아 왔을 때 콜백으로 알림
 service.getLibraries(SeoulOpenApi.API_KEY,200)
            .enqueue(object : Callback<Library> {
                override fun onFailure(call: Call<Library>, t: Throwable) {
                    Toast.makeText(this@MapsActivity2,"실패함",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Library>, response: Response<Library>) {
                    val result= response.body() //데이터 받음
                    showLibraries(result)
                }

            })
```



## AWS 연동 (EC2+ RDS)

- 개발환경 : android(kotlin), nodejs, aws rds(mysql),aws ec2
- ec2 : 가상 컴퓨터 환경 제공 
- aws rds ? :AWS 클라우드에서 관계형 데이터베이스를 더 쉽게 설치, 운영 및 확장할 수 있는 웹 서비스. 

![aws연습](https://user-images.githubusercontent.com/39898938/106390539-2fe7ef80-642c-11eb-9499-ad850243a459.gif) 

- utf-8 설정을 안해줘서 한글 인식을 못함.

  

## 210206 스터디준비

- bodyparser ? : express 내장된 미들웨어(클라이언트와 서버 통신 ) 기본적으로 request body 정의 X 그래서 post 할 경우 body parser 미들웨어를 사용함
- express 라우트 메소드 : get, post , put, delete .....

```javascript
// 0.0.0.0 호스트를 지정하지 않음 설정. 모든 인터페이스에서 실행가능.
//app.listen(port,[hostname],[backlog],[callback])
//서버 생성뒤 port(3000) 을 보고 hostname(ip) 를 선별하고, 비동기적으로 작용.
app.listen(3000, '0.0.0.0', function () {
    console.log('서버 실행 중...');
});
//db 연결
var connection = mysql.createConnection({
    port: 3306 //default
});
// '/'  루트 페이지 
//~/user/data(routing 대상) 에 접속해서 함수 실행 
//주소를 받아서 특정 주소에 해당하는 요청이 왔을 때 미들웨어 동작
app.get('/user/data',function(req,res){
    //req : Request Object 요청객체, 클라이언트에서 보낸 여러 정보 포함
    //res : response Object 응답객체 , 클라이언트에게 응답할 수 있게하는 객체
  
});

app.post('/user/join', function (req, res) {
    // 쿼리 작성, 값 넣고 , 실행 결과 
    connection.query(sql, params, function (err, result) {
        if (err) {
            console.log(err);
        } else {
            resultCode = 200;
            message = '회원가입에 성공했습니다.';
        }
        res.json{
            //JSON 응답 전송
        }
    });
});

```

- Annotation : 인터페이스 기반 문법, 특별한 의미 부여 or 기능 주입 가능. 
- retrofit : http(클과 서버 요청 프로토콜) rest api 구현을 위한 라이브러리 (위에 36강) 
- rest api : rest 기반 api 
- rest : 웹에서 사용하는 Architecture의 한 형식. 네트워크 상 클라이언트와 서버간의 통신 방식, 분산환경에서 클라우드 서비스에 연결 및 상호작용 도와줌. URI를 통해 자원(Resouce)을 명시하고 HTTP Method(GET(데이터 얻기), POST(제출), PUT(업데이트), PATCH, DELETE 등)를 통해  해당 자원에 대한 CRUD를 적용하는 것을 의미 

```kotlin
// gson은 json을 편리하게 사용할 수 있도록 google 에서 만든 json관련 라이브러리
import com.google.gson.annotations.SerializedName
// retrofit : api를 java 인터페이스로 변환 가능, 쉽게 연결, json에서 java 객체로 변환해 주는 json 변환기가 없음 그래서 gson 사용함. 
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class JoinData(
    // 직렬화할 대상들 설정.  String 으로 변경 
    @field:SerializedName("userName") private val userName: String
}

class JoinResponse {
    // Json에서 key 이름과 동일 (gson)
    // gson이 json 키를 필드에 매핑하기위해서 필요. 
    @SerializedName("code")
}
    
    // HTTP request 처리
interface ServiceApi {
    // HTTP annotation 존재
    // ex. @Path("동적으로 변하는 부분 넣기 ")
    @POST("/user/join")
    // q반환값 call  인터페이스에의 API 메서드에서 반환되는 실제 객체
    fun userJoin(@Body data: JoinData?): Call<JoinResponse?>?

}
    // baseurl ==endpoint
val retrofit = Retrofit.Builder().baseUrl(URL.url) .addConverterFactory(GsonConverterFactory.create()).build();
    
```

- **Serialization** :메모리 내에 존재하는 정보를 보다 쉽게 전송 및 전달하기 위해 byte 코드 형태로 나열  
- **Deserialization** : byte로 변환된 Data를 원래대로 Object나 Data로 변환하는 기술 

```kotlin
// Gson이 json 과 java 객체 사이를 변환해주는 라이브러리 
private fun startJoin(data: JoinData) {
      service!!.userJoin(data)!!.enqueue(object : Callback<JoinResponse?> {
            override fun onResponse(
                call: Call<JoinResponse?>,
                response: Response<JoinResponse?>
            ) {
                // Retrofit 에서 GSON 을 JoinResponse 로 변환된 결과 받아옴
                val result = response.body()
                if (result.code == 200) {
                    finish()
                }
            }

          // 응답 실패
            override fun onFailure(
                call: Call<JoinResponse?>,
                t: Throwable
            ) {
            }
        })
    }
```



### 210209 스터디

- OkHttpClient : http 통신 편하게 해주는 라이브러리

```kotlin
val client =OkHttpClient()
        var url ="https://openapi.naver.com/v1/papago/n2mt"
        var json =JSONObject()// JSON 으로 값 넘겨줌
        json.put("source","ko") // 받을 언어
        json.put("target","en")// 번역해줄 언어
        json.put("text","안녕") //번역할 
		//서버에 값 전달할 것
        val body=RequestBody.create(JSON,json.toString())
        val request = Request.Builder().header("X-Naver-Client-Id","")
            .addHeader("X-Naver-Client-Secret","")
            .url(url).post(body).build()

        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                var result=Gson().fromJson<Papago>(response.body()?.string(),Papago::class.java) //Gson 사용해서 json으로 변경
                Log.d("번역 : ",result.message.result.translatedText!!)
            }

        })
```

