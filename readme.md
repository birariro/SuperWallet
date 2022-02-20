## 📣Topic 
바코드,QR 관리 지갑 앱

## 📒Summary
바코드,QR등 다양한 데이터를 저장할수있게한다.

대표적으로 카카오톡 선물 같은경우 스크릿샷으로 보내주는경우가있는데

이를 관리하기 편하게한다.

## 💡Key Function
- 카드의 바코드 정보를 저장하여 보관할수있다.
- 필요에따라 바코드,QR 을 화면에 노출한다.

## 🥋Tack Stack

| | |
| --- | --- |
| 언어 | kotlin |
| 라이브러리 | zxing, Hilt, card view, AAC, firebase  |
| 아키텍쳐 | MVVM + LiveData + Repository |

##  👀Learned

- `Hilt` 를 통해 DI를 적용시켜보는것으로 의존성 주입의 편리함을 알게되었음
- `Room` 로컬 데이터 베이스를 통해 CRUD에있어 빠르게 적용할수있었음
- `callbackFlow` 를 통해 firebase 의 콜백값을 코루틴으로 얻어 올수있었음
- `Android Clean Architecture` 를 통해 처음에 분리하는게 쉽지않았지만 코드를 추가할때 명확한 위치를 파악하기 용이했고 코드를 수정할때 다른곳까지 변경하는 경우가 많이 적어짐

## 😔아쉬운점

## ViewModel 의 역활 증가

비지니스 로직은 UseCase에서 처리하고 ViewModel 에서는 단순히 연결 역활만 하려하였지만

잘 지키지못했다고 느낌

## Activity에서 Domain 계층의 model 에 접근

의도한 구조는 viewmodel에서 domain 계층의 model을 사용하고

activity에서는 단순한 역활만 수행하려했으나 점점 기능을 추가하면서

activity에서 domain 의 model 을 사용하는 경우가 발생

## 외부 라이브러리의 콜백을 어떻게 처리할것인가

firebase 를 통해 로그인을 진행하면서 로그인의 결과를 콜백으로 반환을하는데

해당 로그인 과정은 data source에서 진행하고

그 결과를 view model 에서 필요로 하는데

만약 콜백 그대로 받으려고하면 usecase, repository, datasoucre 모두 콜백을 리턴해야하는상황.

만약 나중에 firebase 가 아닌 자체 서버로 로그인을 처리하려고할때 모두 변경(콜백 모두 제거)해야것같은데

콜백을 그대로 처리하지않고 어떻게 반환할수있을까에서 CallBackFlow로 해결하였지만

이 방법말고 더 자연스럽게 작성할수있는 방법이있을꺼라 생각하지만 해결하지못함

## 📸ScreenShot
<img width="436" alt="스크린샷 2022-02-20 오후 3 53 28" src="https://user-images.githubusercontent.com/52993842/154832615-5f64fd48-7148-4474-a2db-11efd7b8d2b1.png">

